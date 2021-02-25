package com.example.app;
/**
 * 作者 ： WangJinchan
 * 邮箱 ： 945750315@qq.com
 * 日期 ： 2021/2/25.
 * 说明 ：
 */
public class NetworkChangeEvent {
    public boolean isConnected; //是否存在网络

    public NetworkChangeEvent(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
