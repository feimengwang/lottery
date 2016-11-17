package cn.true123.lottery.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import butterknife.BindView;
import cn.true123.lottery.R;

/**
 * Created by junbo on 13/11/2016.
 */

public class NewsDetailActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    String url;

    @BindView(R.id.content)
    FrameLayout frameLayout;
    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                url = bundle.getString("url");
            }
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void iniView() {
        webView= new WebView(getApplicationContext());
        frameLayout.addView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
       ;
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(url);
        toolbar.setTitle(getString(R.string.news_title));
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailActivity.this.finish();
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if ("http".equalsIgnoreCase(request.getUrl().getScheme()) ||
                        "https".equalsIgnoreCase(request.getUrl().getScheme())) {
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                toolbar.setTitle(view.getTitle());
            }


        });

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && !webView.canGoBack()) {
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        } else if (webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(webView!=null){
            webView.getSettings().setJavaScriptEnabled(false);
            webView = null;
        }
    }
}
