package cn.featherfly.android.usb;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Usb utils.
 */
public class UsbUtils {

    private static final String TAG = "UsbUtils";

    /**
     * 获取USB存储集合(此方法适用于android7.0以上系统，实测Android7.1.2有效)
     *
     * @param context context
     * @return USB存储集合 usb storages
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<File> getUsbStorages(Context context) {
        List<File> paths = new ArrayList<>();
        StorageManager mStorageManager = (StorageManager) context.getSystemService(Activity.STORAGE_SERVICE);
        Class<?> volumeInfoClazz = null;
        Method getVolumes = null;
        Method isMountedReadable = null;
        Method getType = null;
        Method getPath = null;
        List<?> volumes = null;
        try {
            volumeInfoClazz = Class.forName("android.os.storage.VolumeInfo");
            getVolumes = StorageManager.class.getMethod("getVolumes");
            isMountedReadable = volumeInfoClazz.getMethod("isMountedReadable");
            getType = volumeInfoClazz.getMethod("getType");
            getPath = volumeInfoClazz.getMethod("getPath");
            volumes = (List<?>) getVolumes.invoke(mStorageManager);
            if (volumes.size() == 0) {
                return null;
            }
            for (Object vol : volumes) {
                if (vol != null && (boolean) isMountedReadable.invoke(vol) && (int) getType.invoke(vol) == 0) {
                    File path2 = (File) getPath.invoke(vol);
                    paths.add(path2);
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
        return paths;
    }

    /**
     * 获取第一个USB存储(此方法适用于android7.0以上系统，实测Android7.1.2有效)
     *
     * @param context context
     * @return USB存储集合 file
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static File getUsbStorage(Context context){
        List<File> storages = getUsbStorages(context);
        if (storages.size() > 0) {
            return storages.get(0);
        } else {
            return null;
        }
    }


    /**
     * 根据label获取外部存储路径(此方法适用于android7.0以上系统)
     *
     * @param context the context
     * @param label   内部存储:Internal shared storage    SD卡:SD card    USB:USB drive(USB storage)
     * @return the external path
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getExternalPath(Context context, String label) {
        String path = "";
        StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        //获取所有挂载的设备（内部sd卡、外部sd卡、挂载的U盘）
        List<StorageVolume> volumes = mStorageManager.getStorageVolumes();//此方法是android 7.0以上的
        try {
            Class<?> storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            //通过反射调用系统hide的方法
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
//       Method getUserLabel = storageVolumeClazz.getMethod("getUserLabel");//userLabel和description是一样的
            for (int i = 0; i < volumes.size(); i++) {
                StorageVolume storageVolume = volumes.get(i);//获取每个挂载的StorageVolume
                // 通过反射调用getPath、isRemovable、userLabel
                String storagePath = (String) getPath.invoke(storageVolume); //获取路径
                boolean isRemovableResult = (boolean) isRemovable.invoke(storageVolume);//是否可移除
                String description = storageVolume.getDescription(context);//此方法是android 7.0以上的
                if (label.equals(description)) {
                    path = storagePath;
                    break;
                }
                Log.d(TAG , "getExternalPath i=" + i + " ,storagePath=" + storagePath + " ,description=" + description);
            }
        } catch (Exception e) {
            Log.d(TAG, "getExternalPath e:" + e);
        }
        return path;
    }

//    public static void getAllExternalPath(Context context) {
//        StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
//        try {
//            Class<?> volumeInfoClazz = Class.forName("android.os.storage.VolumeInfo");
//            //这里需要说明一下，还有一个getVolumeList方法获取到的是StorageVolume数组(但是此数组不包含USB外接设备)
//            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumes");
//            //获取所有挂载的设备（内部sd卡、外部sd卡、挂载的U盘）
//            List result = (List) getVolumeList.invoke(mStorageManager);
////       final int size = Array.getLength(result);
//            //通过反射调用系统hide的方法
//            Method getPath = volumeInfoClazz.getMethod("getPath");
//            Method getDescription = volumeInfoClazz.getMethod("getDescription");
//            for (int i = 0; i < result.size(); i++) {
////          Object storageVolume = Array.get(result, i);
//                Object storageVolume = result.get(i);
//                // 通过反射调用getPath
//                File filePath = (File) getPath.invoke(storageVolume); //获取路径
//                String storagePath = filePath.getAbsolutePath();
//                String description = (String) getDescription.invoke(storageVolume);
////                LogUtils.d(TAG, " i=" + i + " ,storagePath=" + storagePath + " ,description=" + description);
//            }
//        } catch (Exception e) {
////            LogUtils.d(TAG, " e:" + e);
//        }
//    }

}
