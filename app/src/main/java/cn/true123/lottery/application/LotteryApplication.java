package cn.true123.lottery.application;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LotteryApplication extends Application {
    private static final long cacheSize = 10 * 1024 * 1024;
    private static final String TAG = "LotteryApplication";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Map<Activity, List> cacheActivity = new HashMap<Activity, List>();
    public static List<Activity> runningActivities = new ArrayList<>();
    public void addRunningActivity(Activity activity){
        if(!runningActivities.contains(activity)){
            runningActivities.add(activity);
        }
    }
    public void removeRunningActivity(Activity activity){
        if(runningActivities.contains(activity)){
            runningActivities.remove(activity);
        }
        if(activity!=null)activity.finish();
    }
    public void exit(){
        if(runningActivities!=null && runningActivities.size()>0){
            Iterator<Activity> it = runningActivities.iterator();
            while(it.hasNext()){
                Activity activity = it.next();
                if(activity!=null) activity.finish();

            }
            runningActivities.clear();;
        }
    }
    public static String pop(Activity activity) {
        Log.i(TAG, "pop");
        List list = cacheActivity.get(activity);
        if (list == null || list.size() == 0) return null;
        list.remove(0);
        String tag = null;
        if (list.size()>=1) {
             tag = (String) list.get(0);
        }
        Log.i(TAG, "list.size()="+list.size());
        if (list.size() == 0) return null;
        Log.i(TAG, "pop"+tag);
        return tag;
    }

    public static void add(Activity activity, String fragment) {
        Log.i(TAG, "add"+fragment);
        List list = cacheActivity.get(activity);
        if (list == null) list = new ArrayList();
        int index = list.indexOf(fragment);
        if (index > 0) list.remove(index);
        list.add(0, fragment);
        cacheActivity.put(activity, list);
    }

    public File getCacheDirectory() {
        return getExternalCacheDir();

    }

    public long getCacheSize() {
        return cacheSize;
    }

}
