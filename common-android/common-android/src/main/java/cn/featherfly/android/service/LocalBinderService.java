package cn.featherfly.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

public abstract class LocalBinderService extends Service {

    protected String tag;

    private LocalBinder binder = new LocalBinder();

    /**
     * 创建Binder对象，返回给客户端即Activity使用，提供数据交换的接口
     */
    public class LocalBinder extends Binder {
        // 声明一个方法，getService。（提供给客户端调用）
        public <S extends Service> S getService() {
            // 返回当前对象LocalService,这样我们就可在客户端端调用Service的公共方法了
            return (S) LocalBinderService.this;
        }
    }

    protected LocalBinderService() {
        tag = String.format("Service[%s]", this.getClass().getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(tag, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(tag, String.format("onStartCommand(%s, %s, %s)"
                , intent == null ? "null" : intent.toString(), flags, startId));
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Nullable
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(tag, "onDestroy");
    }
}
