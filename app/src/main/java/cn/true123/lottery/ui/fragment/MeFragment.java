package cn.true123.lottery.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.true123.lottery.R;
import cn.true123.lottery.action.IAction;
import cn.true123.lottery.ui.activities.AboutActivity;
import cn.true123.lottery.ui.activities.RuleActivity;
import cn.true123.lottery.ui.fragment.base.BaseFragment;
import cn.true123.lottery.ui.fragment.presenter.MePresenter;
import cn.true123.lottery.ui.fragment.presenter.MePresenterImpl;
import cn.true123.lottery.ui.fragment.view.MeView;
import cn.true123.lottery.utils.Constants;
import cn.true123.lottery.utils.LotteryUtils;

/**
 * Created by junbo on 11/11/2016.
 */

public class MeFragment extends BaseFragment<MePresenter> implements MeView {
    private AlertDialog ad;
    @BindView(R.id.switch_wifi)
    Switch wifi;
    @BindView(R.id.checkVersion)
    TextView checkVersion;
    @BindView(R.id.about)
    TextView about;
    @BindView(R.id.lotteryRule)
    TextView rule;


    @Override
    protected void initView() {
        boolean savedWifi = LotteryUtils.getBooleanFromSharePreferences(getActivity(), Constants.Setting.WIFI_KEY, false);
        wifi.setChecked(savedWifi);
    }

    @OnClick(R.id.switch_wifi)
    public void onSwitch() {
        LotteryUtils.saveBooleanToSharePreferences(getActivity(), Constants.Setting.WIFI_KEY, wifi.isChecked());
    }

    @OnClick(R.id.checkVersion)
    public void checkVersion() {
        presenter.checkVersion();
    }

    @OnClick(R.id.about)
    public void about() {
        startActivity(new Intent(getActivity(), AboutActivity.class));
    }

    @OnClick(R.id.lotteryRule)
    public void rule() {
        startActivity(new Intent(getActivity(), RuleActivity.class));
    }

    @Override
    protected MePresenter getPresenter() {
        return new MePresenterImpl();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public String getTitle() {
        return "设置";
    }


    private void showDialog(final String url) {
        showDialog("检查新版本", "检查到新版本，是否更新？", new IAction() {
            @Override
            public void doAction(Context context) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        }, new IAction() {
            @Override
            public void doAction(Context context) {
                dismissDialog();
            }
        });

    }

    @Override
    public void newVersion(String url) {
        if (url == null || "".equals(url)) {
            showToast("没有检测到最新版本！");
        } else {
            showDialog(url);
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void update(List list) {
        super.update(null);
    }

    @Override
    public void fail() {

    }
}
