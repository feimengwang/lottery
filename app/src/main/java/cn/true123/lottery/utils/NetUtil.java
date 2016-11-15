package cn.true123.lottery.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cn.true123.lottery.App;

/**
 * Created by junbo on 7/10/2016.
 */

public class NetUtil {

    public static boolean isNetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }

        return false;
    }

    public static String getBaseUrl(int type) {
        switch (type) {
            case Constants.NEWS_TYPE:
                return Constants.NETEAST_HOST;

        }
        return "";
    }
}
