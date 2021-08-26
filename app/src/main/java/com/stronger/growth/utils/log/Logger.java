package com.stronger.growth.utils.log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
public abstract class Logger {

  final ThreadLocal<String> explicitTag = new ThreadLocal<>();

  protected boolean isLoggable(@Nullable String tag, LEVEL level) {
    return true;
  }

  public Logger tag(String tag) {
    explicitTag.set(tag);
    return this;
  }

  @Nullable
  public String getTag() {
    String tag = explicitTag.get();
    if (tag != null) {
      explicitTag.remove();
    }
    return tag;
  }

  public void v(@NonNull String message, Object... args) {
    prepareLog(LEVEL.VERBOSE, null, message, args);
  }

  public void v(Throwable t, @NonNull String message, Object... args) {
    prepareLog(LEVEL.VERBOSE, t, message, args);
  }

  public void v(Throwable t) {
    prepareLog(LEVEL.VERBOSE, t, null);
  }

  public void d(@NonNull String message, Object... args) {
    prepareLog(LEVEL.DEBUG, null, message, args);
  }

  public void d(Throwable t, @NonNull String message, Object... args) {
    prepareLog(LEVEL.DEBUG, t, message, args);
  }

  public void d(Throwable t) {
    prepareLog(LEVEL.DEBUG, t, null);
  }

  public void i(@NonNull String message, Object... args) {
    prepareLog(LEVEL.INFO, null, message, args);
  }

  public void i(Throwable t, @NonNull String message, Object... args) {
    prepareLog(LEVEL.INFO, t, message, args);
  }

  public void i(Throwable t) {
    prepareLog(LEVEL.INFO, t, null);
  }

  public void w(@NonNull String message, Object... args) {
    prepareLog(LEVEL.WARN, null, message, args);
  }

  public void w(Throwable t, @NonNull String message, Object... args) {
    prepareLog(LEVEL.WARN, t, message, args);
  }

  public void w(Throwable t) {
    prepareLog(LEVEL.WARN, t, null);
  }

  public void e(@NonNull String message, Object... args) {
    prepareLog(LEVEL.ERROR, null, message, args);
  }

  public void e(Throwable t, @NonNull String message, Object... args) {
    prepareLog(LEVEL.ERROR, t, message, args);
  }

  public void e(Throwable t) {
    prepareLog(LEVEL.ERROR, t, null);
  }

  public void wtf(@NonNull String message, Object... args) {
    prepareLog(LEVEL.ASSERT, null, message, args);
  }

  public void wtf(Throwable t, @NonNull String message, Object... args) {
    prepareLog(LEVEL.ASSERT, t, message, args);
  }

  public void wtf(Throwable t) {
    prepareLog(LEVEL.ASSERT, t, null);
  }

  public void log(LEVEL level, @NonNull String message, Object... args) {
    prepareLog(level, null, message, args);
  }

  public void log(LEVEL level, Throwable t, @NonNull String message, Object... args) {
    prepareLog(level, t, message, args);
  }

  public void log(LEVEL level, Throwable t) {
    prepareLog(level, t, null);
  }

  private void prepareLog(LEVEL level, Throwable t, String message, Object... args) {
    String tag = getTag();

    if (!isLoggable(tag, level)) {
      return;
    }
    if (message != null && message.length() == 0) {
      message = null;
    }
    if (message == null) {
      if (t == null) {
        return;
      }
      message = getStackTraceString(t);
    }

    log(level, tag, t, message, args);
  }

  protected String formatMessage(@NonNull String message, @Nullable Object[] args) {
    return args == null || args.length == 0 ? message : String.format(message, args);
  }

  protected String getStackTraceString(Throwable t) {
    StringWriter sw = new StringWriter(256);
    PrintWriter pw = new PrintWriter(sw, false);
    t.printStackTrace(pw);
    pw.flush();
    return sw.toString();
  }

  protected abstract void log(LEVEL level, String tag, Throwable tr, String message,
      Object... args);

  public enum LEVEL {
    VERBOSE(2, "V"), DEBUG(3, "D"), INFO(4, "I"), WARN(5, "W"), ERROR(6, "E"), ASSERT(7, "A");

    final String levelString;
    final int level;

    private LEVEL(int level, String levelString) {
      this.level = level;
      this.levelString = levelString;
    }

    public String getLevelString() {
      return this.levelString;
    }

    public int getLevel() {
      return this.level;
    }
  }
}
