package com.example.app;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/**
 * 作者 ： WangJinchan
 * 邮箱 ： 945750315@qq.com
 * 日期 ： 2021/2/25.
 * 说明 ：
 */

public class NetUtils {

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return 网络是否连接Boolean（true|false）
     */

    @SuppressWarnings({"ConstantConditions", "ForLoopReplaceableByForEach", "IfCanBeSwitch"})
    public static boolean isConnected(Context context) {

        StringBuilder str = null;

        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        /**
         *  在系统版本小于21之前，使用以下的方式获取当前网络状态：
         * 先利用Context对象获取ConnectivityManager对象，
         * 然后利用ConnectivityManager对象获取NetworkInfo对象，
         * 然后根据NetworkInfo对象的类型来返回不同的网络状态。
         * 有三种，移动，Wi-Fi，无网络连接。
         */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息



            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已连接,移动数据已连接", Toast.LENGTH_SHORT).show();
                Log.e("日志", "WIFI已连接,移动数据已连接");
                return true;
            } else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已连接,移动数据已断开", Toast.LENGTH_SHORT).show();
                Log.e("日志", "WIFI已连接,移动数据已断开");
                return true;
            } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已断开,移动数据已连接", Toast.LENGTH_SHORT).show();
                Log.e("日志", "WIFI已断开,移动数据已连接");
                return true;
            } else {
                Toast.makeText(context, "WIFI已断开,移动数据已断开", Toast.LENGTH_SHORT).show();
                Log.e("日志", "WIFI已断开,移动数据已断开");
                return false;
            }
        } else {
            /**
             *  在系统21及之后，获取网络连接状态的方式：利用ConnectivityManager对象获取
             * 所有的网络连接信息，然后遍历每个网络连接，获取相应的NetworkInfo，
             * 然后根据NetworkInfo对象的类型来返回不同的网络状态。
             */
            if (str != null) {
                str.setLength(0);
            }
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();

            //用于存放网络连接信息
            str = new StringBuilder();
            //通过循环将网络信息逐个取出来
            for (int i = 0; i < networks.length; i++) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                str.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
            }
            Log.e("日志", "sb.toString() : " + str.toString());
            if (str.toString().equals("WIFI connect is true")) {
                Toast.makeText(context, "WIFI已连接", Toast.LENGTH_SHORT).show();
                Log.e("日志", "WIFI已连接");
                return true;
            } else if (str.toString().equals("MOBILE connect is true")) {
                Toast.makeText(context, "移动数据已连接", Toast.LENGTH_SHORT).show();
                Log.e("日志", "移动数据已连接");
                return true;
            } else if (str.toString().equals("MOBILE connect is trueWIFI connect is true")
                    || str.toString().equals("WIFI connect is trueMOBILE connect is true")) {
                Toast.makeText(context, "WIFI已连接,移动数据已连接", Toast.LENGTH_SHORT).show();
                Log.e("日志", "WIFI已连接,移动数据已连接");
                return true;
            } else if (str.toString().equals("MOBILE connect is falseWIFI connect is true")
                    || str.toString().equals("WIFI connect is trueMOBILE connect is false")) {
                Toast.makeText(context, "WIFI已连接,移动数据已断开", Toast.LENGTH_SHORT).show();
                Log.e("日志", "WIFI已连接,移动数据已断开");
                return true;
            } else if (str.toString().equals("MOBILE connect is trueWIFI connect is false")
                    || str.toString().equals("WIFI connect is falseMOBILE connect is true")) {
                Toast.makeText(context, "WIFI已断开,移动数据已连接", Toast.LENGTH_SHORT).show();
                Log.e("日志", "WIFI已断开,移动数据已连接");
                return true;
            } else {
                Toast.makeText(context, "WIFI已断开,移动数据已断开", Toast.LENGTH_SHORT).show();
                Log.e("日志", "WIFI已断开,移动数据已断开");
                return false;
            }
        }

    }

}

