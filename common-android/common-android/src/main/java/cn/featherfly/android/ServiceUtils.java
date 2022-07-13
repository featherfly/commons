package cn.featherfly.android;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;

/**
 * The type Service utils.
 */
public class ServiceUtils {

    private Context context;

    private ServiceUtils(Context context) {
        this.context = context;
    }

    /**
     * Create service utils.
     *
     * @param context the context
     * @return the service utils
     */
    public static ServiceUtils create(Context context) {
        return new ServiceUtils(context);
    }

    /**
     * Start service.
     *
     * @param action      the action
     * @param uri         the uri
     * @param serviceType the service type
     */
    public void startService(String action, Uri uri, Class<? extends Service> serviceType) {
        startService(action, uri, context, serviceType);
    }

    /**
     * Start service.
     *
     * @param serviceType the service type
     */
    public void startService(Class<? extends Service> serviceType) {
        startService(context, serviceType);
    }


    /**
     * Stop service.
     *
     * @param action      the action
     * @param uri         the uri
     * @param serviceType the service type
     */
    public void stopService(String action, Uri uri, Class<? extends Service> serviceType) {
        stopService(action, uri, context, serviceType);
    }

    /**
     * Stop service.
     *
     * @param serviceType the service type
     */
    public void stopService(Class<? extends Service> serviceType) {
        stopService(context, serviceType);
    }

    /**
     * Is service running boolean.
     *
     * @param serviceType the service type
     * @return the boolean
     */
    public boolean isServiceRunning(Class<? extends Service> serviceType) {
        return isServiceRunning(context, serviceType);
    }

    /**
     * Start service if the service is not running.
     *
     * @param action      the action
     * @param uri         the uri
     * @param context     the context
     * @param serviceType the service type
     */
    public static void startService(String action, Uri uri, Context context, Class<? extends Service> serviceType) {
        if (!isServiceRunning(context, serviceType)) {
            context.startService(new Intent(action, uri, context, serviceType));
        }
    }

    /**
     * Start service if the service is not running.
     *
     * @param context     the context
     * @param serviceType the service type
     */
    public static void startService(Context context, Class<? extends Service> serviceType) {
        if (!isServiceRunning(context, serviceType)) {
            context.startService(new Intent(context, serviceType));
        }
    }

    /**
     * Stop service.
     *
     * @param action      the action
     * @param uri         the uri
     * @param context     the context
     * @param serviceType the service type
     */
    public static void stopService(String action, Uri uri, Context context, Class<? extends Service> serviceType) {
//        if (isServiceRunning(context, serviceType)) {
            context.stopService(new Intent(action, uri, context, serviceType));
//        }
    }

    /**
     * Stop service.
     *
     * @param context     the context
     * @param serviceType the service type
     */
    public static void stopService(Context context, Class<? extends Service> serviceType) {
//        if (!isServiceRunning(context, serviceType)) {
            context.stopService(new Intent(context, serviceType));
//        }
    }

    /**
     * Is service running boolean.
     *
     * @param context     the context
     * @param serviceType the service type
     * @return the boolean
     */
    public static boolean isServiceRunning(Context context, Class<? extends Service> serviceType) {
        return isServiceRunning(context, serviceType.getName());
    }

    /**
     * Is service running boolean.
     *
     * @param context     the context
     * @param serviceName the service name
     * @return the boolean
     */
    private static boolean isServiceRunning(Context context, String serviceName) {
        if (TextUtils.isEmpty(serviceName)) {
            return false;
        }
        ActivityManager manager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningServices =
                (ArrayList<ActivityManager.RunningServiceInfo>)
                        manager.getRunningServices(512);
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().toString()
                    .equals(serviceName)) {
//                Log.e("Service", runningServices.get(i).service.getPackageName() + " @ " + runningServices.get(i).service.getClassName()
//                        + " # " + runningServices.get(i).service.getShortClassName()
//                );
//                 com.example.testservices3 @ MqttService # .MqttService
                return true;
            }
        }
        return false;
    }
}
