package cn.true123.lottery.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.true123.lottery.R;
import cn.true123.lottery.model.ILottery;
import cn.true123.lottery.ui.activities.presenter.LotteryDetailPresenter;
import cn.true123.lottery.ui.activities.presenter.LotteryDetailPresenterImpl;
import cn.true123.lottery.ui.activities.view.ILotteryDetailView;
import cn.true123.lottery.model.Lottery;
import cn.true123.lottery.model.LotteryDetail;
import cn.true123.lottery.utils.LotteryUtils;
import cn.true123.lottery.widget.KJLineItemView;
import cn.true123.lottery.widget.LotteryDetailView;


public class LotteryDetailActivity extends BaseActivity<LotteryDetail,LotteryDetailPresenter> implements ILotteryDetailView {

    String lotId;
    String issue;
    @BindView(R.id.text_detail_name)
    TextView tName;
    @BindView(R.id.text_detail_phase)
    TextView tPhase;
    @BindView(R.id.text_detail_timedraw)
    TextView tTime;
    @BindView(R.id.text_detail_pool)
    TextView tPool;
    @BindView(R.id.text_detail_sale)
    TextView tSale;
    @BindView(R.id.detail_detail)
    LotteryDetailView detail;
    @BindView(R.id.view_detail_ball)
    KJLineItemView item;
    LotteryDetailPresenter presenter;

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                issue = bundle.getString("issue");
                lotId = bundle.getString("lotId");
            }
        }
    }

    @Override
    protected void initPresenter() {
        presenter = new LotteryDetailPresenterImpl();
    }

    @Override
    protected void iniView() {
        toolbar.setTitle(getString(R.string.lottery_detail_title));
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LotteryDetailActivity.this.finish();
            }
        });
        presenter.attach(this);
        presenter.setIssueAndName(issue, lotId);
        presenter.start();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_history;
    }

    @Override
    public void update(List<LotteryDetail> list) {
        if(list==null || list.size()==0)
            return;
        final LotteryDetail lotteryDetail = list.get(0);

        tName.setText(lotteryDetail.getLotName());
        tPhase.setText(getResString(R.string.lottery_issue, lotteryDetail.getIssue()));
        tPool.setText(getResString(R.string.lottery_pool, lotteryDetail.getMoney()));
        tSale.setText(getResString(R.string.lottery_sale, lotteryDetail.getSale()));
        tTime.setText(getResString(R.string.lottery_time, lotteryDetail.getDate()));
        List lotteryDetailLevel = lotteryDetail.getLevel();
        if (lotteryDetailLevel != null)
            lotteryDetailLevel.add(0, new LotteryDetail.LevelEntity(getResString(R.string.lottery_item), getResString(R.string.lottery_count), getResString(R.string.lottery_num)));
        DisplayMetrics outMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        detail.setList(lotteryDetailLevel, outMetrics.widthPixels);
        item.setWidthHeight(outMetrics.widthPixels, outMetrics.heightPixels);
        item.addViewList(LotteryUtils.getBall(new Lottery.IEntity() {
            @Override
            public String getLotName() {
                return lotteryDetail.getLotName();
            }

            @Override
            public String getIssue() {
                return null;
            }

            @Override
            public String getBalls() {
                return lotteryDetail.getCode();
            }

            @Override
            public String getDate() {
                return null;
            }
        }));
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }
}
