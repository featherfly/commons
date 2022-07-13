package cn.featherfly.android.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.Uri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ExternalStorageMonitor {

    private static final Logger logger = LoggerFactory.getLogger(ExternalStorageMonitor.class);

//    public static final String EXTERNAL_STORAGE_DEFAULT_PATH = "/mnt/media_rw";

    private static ExternalStorageMonitor mInstance;

    private List<ExternalStorageDeviceListener> deviceListeners;

    private Context mContext;

    public static ExternalStorageMonitor getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ExternalStorageMonitor.class) {
                if (mInstance == null) {
                    mInstance = new ExternalStorageMonitor(context);
                }
            }
        }
        return mInstance;
    }

    private ExternalStorageMonitor(Context context) {
        mContext = context.getApplicationContext();
        deviceListeners = new ArrayList<>();
    }

    public boolean init(ExternalStorageDeviceListener listener) {
        addDeviceListener(listener);
        startMonitor();
        // 在系统启动前是否已经有设备插入
        detectDevice();
        return true;
    }

    public void addDeviceListener(ExternalStorageDeviceListener listener) {
        for (ExternalStorageDeviceListener deviceListener : deviceListeners) {
            if (listener == deviceListener) {
                return;
            }
        }
        deviceListeners.add(listener);
    }

    public void removeDeviceListener(ExternalStorageDeviceListener listener) {
        deviceListeners.remove(listener);
    }

    /**
     * 开始监听USB设备的插拔
     */
    private void startMonitor() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);


        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_REMOVED);

        USBBroadCastReceiver usbBroadCastReceiver = new USBBroadCastReceiver();
        mContext.registerReceiver(usbBroadCastReceiver, filter);
    }

    /**
     * 检测是否有USB设备的插入
     */
    private void detectDevice() {
        UsbManager usbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
        if (usbManager != null) {
            HashMap<String, UsbDevice> deviceHashMap = usbManager.getDeviceList();
            if (deviceHashMap.isEmpty()) {
                return;
            }
            for (UsbDevice device : deviceHashMap.values()) {
                logger.debug("device name: " + device.getDeviceName() + "\nvendor id:" + device.getVendorId());

                dispatchAttachedListener();
            }
        }
    }

    private void dispatchAttachedListener() {
        for (ExternalStorageDeviceListener deviceListener : deviceListeners) {
            deviceListener.onAttached();
        }
    }

    private void dispatchDetachedListener(Uri uriPath) {
        for (ExternalStorageDeviceListener deviceListener : deviceListeners) {
            deviceListener.onDetached(uriPath);
        }
    }

    public interface ExternalStorageDeviceListener {
        void onAttached();

        void onDetached(Uri uriPath);
    }

    private class USBBroadCastReceiver extends BroadcastReceiver {

        private final Set<Uri> usbPaths = new HashSet<>(0);

        @Override
        public void onReceive(Context context, Intent intent) {
            logger.debug("USBBroadCastReceiver.onReceive: {}", intent.getAction());
            switch (intent.getAction()) {
                case UsbManager.ACTION_USB_DEVICE_ATTACHED:
                    logger.debug("USB storage attached {}", intent.getData());
                    usbPaths.add(intent.getData());
                    dispatchAttachedListener();
                    break;
                case UsbManager.ACTION_USB_DEVICE_DETACHED:
                    logger.debug("USB storage detached {}", intent.getData());
                    usbPaths.remove(intent.getData());
                    dispatchDetachedListener(intent.getData());
                    break;
                default:
                    break;
            }
        }
    }
}
