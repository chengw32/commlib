package com.afmoney.commlibrary.bases;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.afmoney.commlibrary.R;
import com.afmoney.commlibrary.Utils.XLogUtil;

import butterknife.BindView;
import me.jessyan.autosize.utils.LogUtils;

/**
 * Created by chenguowu on 2019/6/13.
 */
public class BaseWebViewActivity extends BaseActivity {

    protected WebView mWebView;
    private ProgressBar mProgressBar ;
    protected TopBarView mTopBarView;


    @Override
    public void setContentView() {
        setContentView(R.layout.web_view_layout);
    }

    @Override
    public void initUI() {

        mWebView = findViewById(R.id.mWebView);
        mProgressBar = findViewById(R.id.pb);
        mTopBarView = findViewById(R.id.mTopBarView);

        WebSettings settings = this.mWebView.getSettings();
        settings.setSupportZoom(false);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setDefaultFontSize(16);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(this.mWebView.getContext().getApplicationContext().getCacheDir().getAbsolutePath());
        settings.setDatabaseEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(true);
        settings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        CookieManager.getInstance().setAcceptCookie(true);
        if (Build.VERSION.SDK_INT > 8) {
            settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        }
        String userAgentString = settings.getUserAgentString();
        if (TextUtils.isEmpty(userAgentString)) {
            userAgentString = "Mozilla/5.0 (Linux; U; Android 5.0; zh-cn; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        }
        settings.setUserAgentString(userAgentString);


        this.mWebView.setWebChromeClient(new WebChromeClient() {
            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                XLogUtil.e("onJsAlert: "+message);
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                XLogUtil.e("BaseWebViewActivity -> shouldOverrideUrlLoading "+str);
                    webView.loadUrl(str);
                return true;
            }
        });

    }


    @Override
    public void initData() {
        Intent intent = getIntent();

        if (null != mWebView)
            mWebView.loadUrl(intent.getStringExtra("url"));

        if (null != mTopBarView && intent.hasExtra("title")){
            mTopBarView.setTitle(intent.getStringExtra("title"));
        }
    }
}
