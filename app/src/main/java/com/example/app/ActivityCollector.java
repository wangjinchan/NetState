package com.example.app;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
/**
 * 作者 ： WangJinchan
 * 邮箱 ： 945750315@qq.com
 * 日期 ： 2021/2/25.
 * 说明 ：
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<Activity>();

    /**
     * 用于向List中添加一个活动。
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 用于从List中移除活动。
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 用于将List中存储的活动全部都销毁掉。
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing())
                activity.finish();
        }
    }

}
