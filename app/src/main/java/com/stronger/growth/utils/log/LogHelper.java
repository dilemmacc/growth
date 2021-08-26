package com.stronger.growth.utils.log;

import android.content.Context;

import androidx.annotation.NonNull;

import com.stronger.growth.GlobalData;

import java.io.File;

public class LogHelper {

    private Logger mLogger;
    // 为了避免Log 实例 资源竞争，采用饿汉式单例
    private static LogHelper sInstance = new LogHelper();
    public static final Logger R_LOGGER = new RLogger();

    private static final String DEFAULT_FILE_LOG = "log.txt";
    private static final String DEFAULT_FILE_LOG_DIR = "log";

    private LogHelper() {
        mLogger = new LogcatLogger();
    }

    static LogHelper get() {
        return sInstance;
    }

    private LogHelper onceTag(String tag) {
        getLogger().tag(tag);
        return this;
    }

    private void setLoggerInner(@NonNull Logger logger) {
        if (logger == null) {
            throw new NullPointerException();
        }
        mLogger = logger;
    }

    @NonNull
    private Logger getLogger() {
        return mLogger;
    }

    public static void setLogger(@NonNull Logger logger) {
        LogHelper helper = get();
        helper.setLoggerInner(logger);
    }

    public static Logger tag(String tag) {
        return get().onceTag(tag).getLogger();
    }

    public static void v(@NonNull String message, Object... args) {
        get().getLogger().v(message, args);
    }

    public static void v(Throwable t, @NonNull String message, Object... args) {
        get().getLogger().v(t, message, args);
    }

    public static void v(Throwable t) {
        get().getLogger().v(t);
    }

    public static void d(@NonNull String message, Object... args) {
        get().getLogger().d(message, args);
    }

    public static void d(Throwable t, @NonNull String message, Object... args) {
        get().getLogger().d(t, message, args);
    }

    public static void d(Throwable t) {
        get().getLogger().d(t);
    }

    public static void i(@NonNull String message, Object... args) {
        get().getLogger().i(message, args);
    }

    public static void i(Throwable t, @NonNull String message, Object... args) {
        get().getLogger().i(t, message, args);
    }

    public static void i(Throwable t) {
        get().getLogger().i(t);
    }

    public static void w(@NonNull String message, Object... args) {
        get().getLogger().w(message, args);
    }

    public static void w(Throwable t, @NonNull String message, Object... args) {
        get().getLogger().w(t, message, args);
    }

    public static void w(Throwable t) {
        get().getLogger().w(t);
    }

    public static void e(@NonNull String message, Object... args) {
        get().getLogger().e(message, args);
    }

    public static void e(Throwable t, @NonNull String message, Object... args) {
        get().getLogger().e(t, message, args);
    }

    public static void e(Throwable t) {
        get().getLogger().e(t);
    }

    public static void wtf(@NonNull String message, Object... args) {
        get().getLogger().wtf(message, args);
    }

    public static void wtf(Throwable t, @NonNull String message, Object... args) {
        get().getLogger().wtf(t, message, args);
    }

    public static void wtf(Throwable t) {
        get().getLogger().wtf(t);
    }

    public static void log(Logger.LEVEL level, @NonNull String message, Object... args) {
        get().getLogger().log(level, message, args);
    }

    public static void log(Logger.LEVEL level, Throwable t, @NonNull String message, Object... args) {
        get().getLogger().log(level, t, message, args);
    }

    public static void log(Logger.LEVEL level, Throwable t) {
        get().getLogger().log(level, t);
    }

    //================== File Log =======================

    private static String getLogFileDir(Context context, String dirName) {

        File externalCache = context.getExternalCacheDir();
        if (externalCache != null) {
            File cacheImg = new File(externalCache, dirName);
            if (!cacheImg.exists()) {
                cacheImg.mkdirs();
            }

            if (cacheImg.canRead() && cacheImg.canWrite()) {
                return cacheImg.getAbsolutePath();
            }
        }

        File innerCache = context.getCacheDir();
        if (innerCache != null) {
            File cacheImg = new File(innerCache, dirName);
            if (!cacheImg.exists()) {
                cacheImg.mkdirs();
            }

            if (cacheImg.canRead() && cacheImg.canWrite()) {
                return cacheImg.getAbsolutePath();
            }
        }

        return null;
    }

    private static String getDefaultLogFile() {
        String logFileDir = getLogFileDir(GlobalData.INSTANCE.getContext(), DEFAULT_FILE_LOG_DIR);
        File logFile = new File(logFileDir, DEFAULT_FILE_LOG);
        return logFile.getAbsolutePath();
    }
}
