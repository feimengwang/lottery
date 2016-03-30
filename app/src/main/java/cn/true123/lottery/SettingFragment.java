package cn.true123.lottery;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;

import cn.true123.lottery.utils.Setting;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private static final String TAG ="SettingFragment" ;
    SwitchPreference wifi;
    SwitchPreference mobile;
    Preference checkVersion;
    Preference about;

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
        Log.i(TAG,""+preference.getKey());
        SharedPreferences pre= PreferenceManager.getDefaultSharedPreferences(getActivity());

        Log.i(TAG,"WIFI_KEY="+pre.getBoolean(Setting.WIFI_KEY,false));
        switch (preference.getKey()) {
            case Setting.WIFI_KEY:
                show("wifi");
                break;
            case Setting.MOBILE_KEY:
                show("mobile");
                break;
            case Setting.CHECK_VERSION_KEY:
                show("check_version");
                break;
            case Setting.ABOUT_KEY:
                show("about");
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                getActivity().startActivity(intent);
                break;
        }
        return true;
    }
}
