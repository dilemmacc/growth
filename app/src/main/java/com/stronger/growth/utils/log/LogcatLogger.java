package com.stronger.growth.utils.log;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.stronger.growth.GlobalData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogcatLogger extends Logger {

  private static final int MAX_LOG_LENGTH = 4000;
  private static final int MAX_TAG_LENGTH = 23;
  private static final int CALL_STACK_INDEX = 4;
  private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");
  private static final String DEFAULT_TAG = "LogcatLogger";

  @Override
  public boolean isLoggable(@Nullable String tag, LEVEL level) {
    return GlobalData.INSTANCE.isDeveloperMode();
  }

  @Override
  public String getTag() {
    String tag = super.getTag();
    if (tag != null) {
      return tag;
    }
    if (! GlobalData.INSTANCE.isDeveloperMode()) {
      return DEFAULT_TAG;
    }
    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
    if (stackTrace.length <= CALL_STACK_INDEX) {
      throw new IllegalStateException(
          "Synthetic stacktrace didn't have enough elements: are you using proguard?");
    }
    return createStackElementTag(stackTrace[CALL_STACK_INDEX]);
  }

  @Nullable
  String getSuperTag() {
    return super.getTag();
  }

  @Nullable
  protected String createStackElementTag(@NonNull StackTraceElement element) {
    String tag = element.getClassName();
    Matcher m = ANONYMOUS_CLASS.matcher(tag);
    if (m.find()) {
      tag = m.replaceAll("");
    }
    tag = tag.substring(tag.lastIndexOf('.') + 1);
    if (tag.length() <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      return tag;
    }
    return tag.substring(0, MAX_TAG_LENGTH);
  }

  @SuppressLint("LogNotTimber")
  @Override
  public void log(LEVEL level, String tag, Throwable thr, String message, Object... args) {
    String msg = formatMessage(message, args);
    if (thr != null) {
      msg += "\n" + getStackTraceString(thr);
    }
    if (msg.length() < MAX_LOG_LENGTH) {
      if (level == LEVEL.ASSERT) {
        Log.wtf(tag, msg);
      } else {
        Log.println(level.getLevel(), tag, msg);
      }
      return;
    }

    // Split by line, then ensure each line can fit into Log's maximum length.
    for (int i = 0, length = msg.length(); i < length; i++) {
      int newline = msg.indexOf('\n', i);
      newline = newline != -1 ? newline : length;
      do {
        int end = Math.min(newline, i + MAX_LOG_LENGTH);
        String part = msg.substring(i, end);
        if (level == LEVEL.ASSERT) {
          Log.wtf(tag, part);
        } else {
          Log.println(level.getLevel(), tag, part);
        }
        i = end;
      } while (i < newline);
    }
  }
}
