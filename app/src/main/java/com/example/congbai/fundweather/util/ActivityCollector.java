package com.example.congbai.fundweather.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fundmarkhua on 2017/3/9
 * Email:57525101@qq.com
 * 活动管理器
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
