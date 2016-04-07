package cn.true123.lottery;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;

import cn.true123.lottery.model.ApiVersion;
import cn.true123.lottery.model.LotteryConstant;
import cn.true123.lottery.retrofit.LotteryServiceManager;
import cn.true123.lottery.utils.Setting;
import rx.Subscriber;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private static final String TAG = "SettingFragment";
    SwitchPreference wifi;
    SwitchPreference mobile;
    Preference checkVersion;
    Preference about;
    AlertDialog ad = null;

    public SettingFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        wifi = (SwitchPreference) findPreference("wifi");
        mobile = (SwitchPreference) findPreference("mobile");
        checkVersion = findPreference("check_version");
        about = findPreference("about");
        wifi.setOnPreferenceClickListener(this);
        mobile.setOnPreferenceClickListener(this);
        checkVersion.setOnPreferenceClickListener(this);
        about.setOnPreferenceClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void show(String content) {
        Snackbar.make(getView(), content, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        Log.i(TAG, "" + preference.getKey());
        SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(getActivity());

        Log.i(TAG, "WIFI_KEY=" + pre.getBoolean(Setting.WIFI_KEY, false));
        switch (preference.getKey()) {
            case Setting.WIFI_KEY:
                show("wifi");
                break;
            case Setting.MOBILE_KEY:
                show("mobile");
                break;
            case Setting.CHECK_VERSION_KEY:
                show("checking version");
                checkLastVersion();
                break;
            case Setting.ABOUT_KEY:
                show("about");
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                getActivity().startActivity(intent);
                break;
        }
        return true;
    }

    private void checkLastVersion() {
        LotteryServiceManager.instance(getActivity()).getLastVersion(new Subscriber<ApiVersion>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ApiVersion o) {
                if (o != null) {
                    checkTheVersion(o);
                }
            }
        }, LotteryConstant.API_TOKEN);
    }

    private void checkTheVersion(ApiVersion o) {
        if (o != null) {
            String version = o.getVersion();
            if (version != null) {
                int intVersion = 0;
                try {
                    intVersion = Integer.parseInt(version);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PackageManager pm = getActivity().getPackageManager();
                try {
                    PackageInfo info = pm.getPackageInfo(getActivity().getPackageName(), 0);
                    int currentVC = info.versionCode;
                    if (intVersion > currentVC) {
                        showDialog(o.getUpdate_url());
                    } else {
                        show("当前是最新版本！");
                    }

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                show("当前是最新版本！");
            }
        }
    }

    private void showDialog(final String url) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("检查新版本");
        builder.setMessage("检查到新版本，是否更新？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                getActivity().startActivity(intent);
            }
        });


        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ad.dismiss();
            }
        });
        ad = builder.create();
        ad.show();
    }
}
