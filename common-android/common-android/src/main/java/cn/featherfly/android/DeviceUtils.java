package cn.featherfly.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Locale;

/**
 * The type Device utils.
 */
public class DeviceUtils {

    private static final String CPU_SN;

    static {
        String str = "", strCPU = "", cpuAddress = "0000000000000000";
        try {
            //读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            //查找CPU序列号
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    //查找到序列号所在行
                    if (str.contains("Serial")) {
                        //提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1,
                                str.length());
                        //去空格
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    //文件结尾
                    break;
                }
            }
        } catch (Exception ex) {
            //赋予默认值
            ex.printStackTrace();
        }
        CPU_SN = cpuAddress;
    }

    /**
     * Gets android id.
     *
     * @param context the context
     * @return the android id
     */
    @SuppressLint("HardwareAndroidId")
    public static String getAndroidId(Context context) {
//        return Build.SERIAL;
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

//    @SuppressLint("HardwareDeviceId")
//    public static String getDeviceId(Context context) {
////        return Build.SERIAL;
//        TelephonyManager tm =
//                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        return tm.getDeviceId();
//    }

    /**
     * Gets device imei.
     *
     * @param context the context
     * @return the device imei
     */
    @SuppressLint("HardwareImei")
    public static String getDeviceImei(Context context) {
//        return Build.SERIAL;
        TelephonyManager tm =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getImei();
    }

    /**
     * Gets device sn.
     *
     * @param context the context
     * @return the device sn
     */
    @SuppressLint("HardwareImei")
    public static String getDeviceSn(Context context) {
//        return Build.SERIAL;
        TelephonyManager tm =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimSerialNumber();
    }

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言 。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表 locale [ ]
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号 system version
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号 system model
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商 device brand
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }


    /**
     * Gets cpu sn.
     *
     * @return the cpu sn
     */
    public static String getCpuSn() {
//        return "1";
//        if (BuildConfig.DEBUG) {
//            return "1";
//        } else {
//
//        }
        return CPU_SN;
    }
}
