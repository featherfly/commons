package cn.featherfly.android;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;

/**
 * The type Installer.
 */
public class Installer {

    private static final String CMD_INSTALL = "pm install -r ";
    private static final String CMD_INSTALL_PACKAGE = "pm install -i %s -r ";
    private static final String CMD_UNINSTALL = "pm uninstall ";

    private static String getInstallCmd(File file, String packageName) {
        // 7.0以后版本需要额外添加 "-i", "当前应用包名",
        // 两个字段，并且需要应用支持 android.permission.INSTALL_PACKAGES 权限**
        if (packageName == null) {
//                process = new ProcessBuilder("pm", "install", "-r", file.getPath()).start();
            return CMD_INSTALL + file.getAbsolutePath() + "\nexit\n";
        } else {
//                process = new ProcessBuilder("pm", "install", "-i", appPackage, "-r", file.getPath()).start();
//                cmd = "chmod 777 "+ file.getAbsolutePath() + "\n" + String.format(CMD_INSTALL_PACKAGE, packageName) + file.getAbsolutePath() + "\nexit\n";
            return String.format(CMD_INSTALL_PACKAGE, packageName) + file.getAbsolutePath() + "\nexit\n";
        }
    }

    private static String getUnInstallCmd(String packageName) {
        return CMD_UNINSTALL + packageName + "\nexit\n";
    }

    /**
     * Install.
     *
     * @param file the file
     * @return the result
     */
    public static Result install(File file) {
        // 适配android9.0之前的安装方法
        return install(file , null);
    }

    /**
     * Install.
     *
     * @param file        the file
     * @param packageName the package name
     * @return the result
     */
    public static Result install(File file, String packageName) {
        // 适配android9.0之前的安装方法
        if (file == null) {
            return new Result("", "file is null");
        } else if (!file.exists()) {
            return new Result("", String.format("file[%s] not exists", file.getAbsolutePath()));
        } else {
            Process process = null;
            StringBuilder successMsg = new StringBuilder();
            StringBuilder errorMsg = new StringBuilder();
            try {
                // 需要root权限
                process = Runtime.getRuntime().exec("su");

                try (DataOutputStream os = new DataOutputStream(process.getOutputStream());
                     BufferedReader successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                     BufferedReader errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));) {
                    String cmd = getInstallCmd(file, packageName);
                    os.write(cmd.getBytes());
                    os.flush();
                    //执行命令
                    process.waitFor();
                    //获取返回结果
                    String s;
                    while ((s = successResult.readLine()) != null) {
                        successMsg.append(s);
                    }
                    while ((s = errorResult.readLine()) != null) {
                        errorMsg.append(s);
                    }
                }

            } catch (Exception e) {
                errorMsg.append(e.getMessage());
            } finally {
                if (process != null) {
                    process.destroy();
                }
            }
            return new Result(successMsg.toString(), errorMsg.toString());
        }
    }

    /**
     * Uninstall result.
     *
     * @param packageName the package name
     * @return the result
     */
    public static Result uninstall(String packageName) {
        Process process = null;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();
        try {
            //卸载也需要root权限
            process =Runtime.getRuntime().exec("su");
            try (DataOutputStream os = new DataOutputStream(process.getOutputStream());
                 BufferedReader successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                 BufferedReader errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));) {
                String cmd = getUnInstallCmd(packageName);
                os.write(cmd.getBytes());
                os.flush();
                //执行命令
                process.waitFor();
                //获取返回结果
                String s;
                while ((s =successResult.readLine()) != null) {
                    successMsg.append(s);
                }
                while ((s = errorResult.readLine())!= null) {
                    errorMsg.append(s);
                }
            }
        } catch (Exception e) {
            errorMsg.append(e.getMessage());
        } finally {
            try {
                if (process != null) {
                    process.destroy();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Result(successMsg.toString(), errorMsg.toString());
    }

    /**
     * The type Result.
     */
    public static class Result {

        private String result;

        private String error;

        private boolean success;

        /**
         * Instantiates a new Result.
         *
         * @param result the result
         * @param error  the error
         */
        public Result(String result, String error) {
            this.result = result;
            this.error = error;
            success = "success".equalsIgnoreCase(result);
        }

        /**
         * Is success boolean.
         *
         * @return the boolean
         */
        public boolean isSuccess() {
            return success;
        }

        /**
         * Gets error.
         *
         * @return the error
         */
        public String getError() {
            return error;
        }

        /**
         * Gets result.
         *
         * @return the result
         */
        public String getResult() {
            return result;
        }
    }

//    // 适配android9.0的安装方法。
//    public static void install28(Context context, String apkFilePath, Class<CustomBroadcastReceiver> receiver) {
//        Log.d(TAG, "install28 path=" + apkFilePath);
//        File apkFile = new File(apkFilePath);
//        PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
//        PackageInstaller.SessionParams sessionParams
//                = new PackageInstaller.SessionParams(PackageInstaller
//                .SessionParams.MODE_FULL_INSTALL);
//        sessionParams.setSize(apkFile.length());
//
//        int sessionId = createSession(packageInstaller, sessionParams);
//        Log.d(TAG, "install28  sessionId=" + sessionId);
//        if (sessionId != -1) {
//            boolean copySuccess = copyInstallFile(packageInstaller, sessionId, apkFilePath);
//            Log.d(TAG, "install28  copySuccess=" + copySuccess);
//            if (copySuccess) {
//                execInstallCommand(context, packageInstaller, sessionId, receiver);
//            }
//        }
//    }
//
//    private static int createSession(PackageInstaller packageInstaller,
//                              PackageInstaller.SessionParams sessionParams) {
//        int sessionId = -1;
//        try {
//            sessionId = packageInstaller.createSession(sessionParams);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sessionId;
//    }
//
//    private static boolean copyInstallFile(PackageInstaller packageInstaller,
//                                    int sessionId, String apkFilePath) {
//        InputStream in = null;
//        OutputStream out = null;
//        PackageInstaller.Session session = null;
//        boolean success = false;
//        try {
//            File apkFile = new File(apkFilePath);
//            session = packageInstaller.openSession(sessionId);
//            out = session.openWrite("base.apk", 0, apkFile.length());
//            in = new FileInputStream(apkFile);
//            int total = 0, c;
//            byte[] buffer = new byte[65536];
//            while ((c = in.read(buffer)) != -1) {
//                total += c;
//                out.write(buffer, 0, c);
//            }
//            session.fsync(out);
//            Log.i(TAG, "streamed " + total + " bytes");
//            success = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                out.close();
//                in.close();
//                session.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return success;
//    }
//
//    private static void execInstallCommand(Context context, PackageInstaller packageInstaller, int sessionId, Class<CustomBroadcastReceiver> receiver) {
//        PackageInstaller.Session session = null;
//        try {
//            session = packageInstaller.openSession(sessionId);
//            Intent intent = new Intent(context, receiver);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
//                    1, intent,
//                    PendingIntent.FLAG_UPDATE_CURRENT);
//            session.commit(pendingIntent.getIntentSender());
//            Log.i(TAG, "begin session");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
}
