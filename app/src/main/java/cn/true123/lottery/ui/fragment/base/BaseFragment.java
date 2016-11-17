package cn.true123.lottery.ui.fragment.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import cn.true123.lottery.action.IAction;
import cn.true123.lottery.ui.base.presenter.BasePresenter;
import cn.true123.lottery.ui.base.view.BaseView;

/**
 * Created by junbo on 1/11/2016.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    public T presenter;

    View mRoot;
    private Dialog ad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("DDD", "onCreateView");
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null)
                parent.removeView(mRoot);
        } else {
            mRoot = inflater.inflate(getLayoutResId(), container, false);
            ButterKnife.bind(this, mRoot);

            initView();
            initPresenter();
        }
        return mRoot;

    }


    protected void initPresenter() {
        presenter = getPresenter();
        if (presenter != null) {
            presenter.attach(this);
            presenter.start();
        }
    }

    protected abstract void initView();

    protected abstract T getPresenter();

    public abstract int getLayoutResId();

    public abstract String getTitle();

    @Override
    public void update(List list) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("DDD", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("DDD", "onCreate");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("DDD", "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("DDD", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("DDD", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("DDD", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("DDD", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("DDD", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("DDD", "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("DDD", "onDestroyView");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("DDD", "onSaveInstanceState");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("DDD", "onDetach");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("DDD", "onViewStateRestored");
    }


    public void showDialog(String title, String message, final IAction okAction, final IAction noAction) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (okAction != null) okAction.doAction(getContext());
            }
        });

        if (noAction != null) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (noAction != null) noAction.doAction(getContext());
                }
            });
        }
        ad = builder.create();
        ad.show();
    }

    @Override
    public void dismissDialog() {
        if (ad != null && ad.isShowing()) ad.dismiss();
    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
