package cn.featherfly.android.app;

import android.app.Application;

public abstract class CrashHandledApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        AppCrashHandler crashHandler = new AppCrashHandler(this, getAppId());
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);

        doOnCreate();
    }

    protected abstract String getAppId();

    protected abstract void doOnCreate();

}
