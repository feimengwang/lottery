package cn.true123.lottery.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.true123.lottery.R;
import cn.true123.lottery.model.Ball;
import cn.true123.lottery.model.HistoryItem;
import cn.true123.lottery.model.Lottery;
import cn.true123.lottery.model.LotteryHistory;

/**
 * Created by feimeng0530 on 2016/3/18.
 */
public class LotteryUtils {
    static ProgressDialog pd;
    static Map<String, String> cacheNameId = new HashMap();
    static Map<String, String> cacheAvailableNameId = new HashMap();
    public static String[] lotteryNameAndId = {
            "220051|双色球|1",
            "120029|大乐透|1",
            "220028|七乐彩|1",
            "110022|七星彩|1",
            "210053|福彩3D|1",
            "110033|排列三|1",
            "110035|排列五|1",
            "166406|11选5|1",
            "168009|新11选5|0",
            "258001|新时时彩|0",
            "255401|老时时彩|0",
            "258203|新快3|0",
            "130011|胜负彩|0",
            "165707|粤11选5|0",
            "257503|快3|0",
            "255903|老快3|0",
            "255803|好运快3|0",
            "257703|湖北快3|0",
            "223515|15选5|0",
            "166407|快乐扑克|0",
            "166507|幸运11选5|0",
            "165207|上海11选5|0",
            "166907|辽宁11选5|0",
            "265108|快乐8|0",
            "167607|快乐11选5|0",
            "130042|竞彩足球|0",
            "130041|北京单场|0",
            "130043|竞彩篮球|0"};

    static {
        for (String item : lotteryNameAndId) {
            String[] spItems = item.split("\\|");
            if (spItems != null && spItems.length > 2) {

                cacheNameId.put(spItems[1], spItems[0]);
                cacheNameId.put(spItems[0], spItems[1]);
                if ("1".equals(spItems[2])) {
                    cacheAvailableNameId.put(spItems[1], spItems[0]);
                }
            }
        }
    }

    public static Map getAllAvailable() {

        return cacheAvailableNameId;
    }

    //
    public static String getId(String name) {
        return cacheNameId.get(name);
    }

    public static String getId(Lottery.IEntity entity) {

        return getId(entity != null ? entity.getLotName().trim() : null);
    }


    public static String getName(String id) {
        return cacheNameId.get(id);
    }

    public static List<Ball> getBall(Lottery.IEntity entity) {
        Log.i("SS", "entity=" + (entity != null ? entity.getBalls() : "") + (entity != null ? entity.getLotName() : ""));
        List ret = new ArrayList();
        if (entity != null) {
            String ball = entity.getBalls();
            if (ball != null && ball.indexOf("+") >= 0) {
                String[] redBlue = ball.split("\\+");
                String[] reds = trim(redBlue[0]).split(" ");
                String[] blues = trim(redBlue[1]).split(" ");
                if ("210053".equals(getId(entity))) {

                    reds = toStringArray(trim(redBlue[0]));
                    blues = toStringArray(trim(redBlue[1]));
                }
                for (String b : reds) {
                    ret.add(new Ball(trim(b), false, true));
                }
                if ("210053".equals(getId(entity))) {
                    ret.add(new Ball(trim("试机号："), false, false));
                }
                for (String b : blues) {
                    ret.add(new Ball(trim(b), true, false));
                }
            } else {
                String[] reds = trim(entity.getBalls()).split(" ");
                Log.i("SS", "reds.length=" + reds);
                for (String b : reds) {
                    Log.i("SS", "red=" + b);
                }
                if (reds != null && reds.length == 1) {
                    reds = toStringArray(trim(entity.getBalls()));
                }
                if (reds != null) {
                    for (String b : reds) {
                        ret.add(new Ball(trim(b), false, true));
                    }
                }
            }

        }
        return ret;
    }

    public static String[] toStringArray(String str) {
        String[] s = {};
        if (str != null && str.length() > 0) {
            char[] chars = str.toCharArray();
            s = new String[chars.length];
            for (int i = 0; i < chars.length; i++) {
                s[i] = String.valueOf(chars[i]);
            }
        }
        return s;
    }

    public static String trim(String str) {
        if (str == null) return "";
        return str.trim();
    }

    public static void main(String[] args) {
        System.out.print("123".split(" ")[1]);
    }

    public static AlertDialog showNetworkDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("网络异常").setMessage("请检查网络链接！")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }

                });
        AlertDialog dialog = builder.show();
        return dialog;
    }

//    public static AlertDialog showDialog(Context context, String title) {
//        View progressBar = LayoutInflater.from(context).inflate(
//                R.layout.progress_bar_layout, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle(title);
//        builder.setView(progressBar);
//        AlertDialog dialog = builder.show();
//        return dialog;
//    }
//
//    public static AlertDialog showDialog(Context context) {
//        View progressBar = LayoutInflater.from(context).inflate(
//                R.layout.progress_bar_layout, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setView(progressBar);
//        AlertDialog dialog = builder.show();
//        return dialog;
//    }

    public static void dismissDialog(AlertDialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static String getNetworkType(Context context) {
        ConnectivityManager conManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // NetworkInfo info= conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//.getState()
        if (checkNetwork(context)) {
            NetworkInfo wifiType = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiType.isConnected()) {
                return "WIFI";
            }
            NetworkInfo mobileType = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileType != null && mobileType.isConnected()) {
                return "MOBILE";
            }
            return "UnKnown";
        }


        return "No connected!";
    }

    public static boolean checkNetwork(Context context) {
        ConnectivityManager conManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conManager.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return false;
        } else {
//
//           Network[] networks;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                networks = conManager.getAllNetworks();
//                for (Network nw:networks
//                     ) {
//                    NetworkInfo netInfo= conManager.getNetworkInfo(nw);
//                    //netInfo.getState()
//                }
//            }
//

//            NetworkInfo.State state = conManager.getNetworkInfo(
//                    ConnectivityManager.TYPE_WIFI).getState();
//            System.out.println("wifi==" + state.toString());
//            Toast.makeText(context,"wifi==" + state.toString(), Toast.LENGTH_SHORT).show();
//            NetworkInfo.State stateMobile = conManager.getNetworkInfo(
//                    ConnectivityManager.TYPE_MOBILE).getState();
//            Toast.makeText(context,"stateMobile==" + stateMobile.toString(), Toast.LENGTH_SHORT).show();
//            System.out.println("stateMobile==" + stateMobile.toString());
            if (info.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static void showProgresDialog(Context c, String title) {
        pd = new ProgressDialog(c);
        pd.setCancelable(true);
        pd.setTitle(title);
        pd.show();
    }

    public static void dismissDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

    /**
     * @param date
     * @return
     */
    public static String getDate(String date) {
        if (date != null && date.length() > 10) {
            return date.substring(0, 10);
        }
        return date;
    }

    /**
     * 1, split the red and blue number,
     * 2, if the number like 123345, add space between every number, the result will be 1 2 3 4 5
     *
     * @param lotId
     * @param entity
     * @return
     */
    public static HistoryItem convertHistoryItem(String lotId, LotteryHistory.ListEntity entity) {

        HistoryItem item = null;
        if (lotId==null)return item;
        switch (lotId) {
            case "166406":
                item = new HistoryItem(lotId, entity.getEndTime(), entity.getIssue(), entity.getWinNumber(), "");
                break;
            case "220051":
            case "120029":
            case "220028":
                String[] redBlue = entity.getWinNumber() != null ? entity.getWinNumber().split("\\+") : new String[]{};
                if (redBlue != null && redBlue.length > 1) {
                    item = new HistoryItem(lotId, entity.getEndTime(), entity.getIssue(), redBlue[0], redBlue[1]);
                } else {
                    item = new HistoryItem(lotId, entity.getEndTime(), entity.getIssue(), entity.getWinNumber(), "");
                }
                break;
            case "210053":
                String ballNum = entity.getBallNumber();
                if (ballNum != null && ballNum.length() > 3) {
                    ballNum = ballNum.substring(0, 3);
                }
                item = new HistoryItem(lotId, entity.getEndTime(), entity.getIssue(), addSpace(entity.getWinNumber()), "试机号：" + addSpace(ballNum));
                break;
            case "110033":
            case "110022":
            case "110035":
                item = new HistoryItem(lotId, entity.getEndTime(), entity.getIssue(), addSpace(entity.getWinNumber()), "");
                break;
        }


        return item;
    }

    /**
     * input 12345, the output should be 1 2 3 4 5
     *
     * @param text
     * @return
     */
    public static String addSpace(String text) {
        if (text == null || text.length() <= 1) return text;
        char[] cs = text.toCharArray();
        StringBuffer sb = new StringBuffer();
        sb.append(" ");
        for (char c : cs) {
            sb.append(c).append(" ");
        }
        return sb.toString();
    }

    public static boolean getBooleanFromSharePreferences(Context context, String key) {
        return getBooleanFromSharePreferences(context, key, false);
    }

    public static boolean getBooleanFromSharePreferences(Context context, String key, boolean defaultValue) {
        SharedPreferences pref = getSharePreferences(context, "lottery", Context.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }

    public static String getStringFromSharePreferences(Context context, String key) {
        SharedPreferences pref = getSharePreferences(context, "lottery", Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

    public static SharedPreferences getSharePreferences(Context context, String name, int mode) {
        if (mode == -1) return context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return context.getSharedPreferences(name, mode);
    }

    public static void saveStringToSharePreferences(Context context, String key, String value) {
        SharedPreferences preferences = getSharePreferences(context, "lottery", Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).commit();
    }

    public static void saveBooleanToSharePreferences(Context context, String key, boolean value) {
        SharedPreferences preferences = getSharePreferences(context, "lottery", Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, value).commit();
    }

    public static String getStringRes(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    public static String getStringRes(Context context, int resId, Object[] objects) {
        return context.getResources().getString(resId, objects);
    }

    public static String getSetting(Context context, String key) {
        SharedPreferences sharedPreferences = getDefaultSharePreferences(context);
        return sharedPreferences.getString(key, "");
    }

    public static boolean getDefaultBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = getDefaultSharePreferences(context);
        return sharedPreferences.getBoolean(key, false);
    }

    public static SharedPreferences getDefaultSharePreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean checkIfShouldUseNetwork(Context context, boolean wifi, boolean mobile) {
        if (wifi || mobile) {
            return checkNetwork(context);
        }
        return false;
    }

    public static boolean checkIfShouldUseNetwork(Context context) {

        return checkIfShouldUseNetwork(context, getDefaultBoolean(context, "wifi"), getDefaultBoolean(context, "mobile"));
    }


}
