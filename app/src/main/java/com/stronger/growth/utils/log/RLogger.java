package com.stronger.growth.utils.log;

import androidx.annotation.Nullable;
public class RLogger extends LogcatLogger {

  private static final String TAG = "RLogger";

  @Override
  public String getTag() {
    String tag = getSuperTag();
    if (tag == null) {
      return TAG;
    }
    return tag;
  }

  @Override
  public boolean isLoggable(@Nullable String tag, LEVEL level) {
    return true;
  }
}
