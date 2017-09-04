package com.yangxw.example.app.mygank.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.yangxw.example.app.mygank.Config;
import com.yangxw.example.app.mygank.R;
import com.yangxw.example.app.mygank.base.BaseActivity;
import com.yangxw.example.app.mygank.bean.DataResultInfo;
import com.yangxw.example.app.mygank.db.CollectDB;
import com.yangxw.example.app.mygank.util.TextUtil;
import com.yangxw.example.app.mygank.remote.JavascriptInterface;
import com.yangxw.example.app.mygank.widget.MWebChromeClient;
import com.yangxw.example.app.mygank.widget.MWebViewClient;

/**
 *
 */
public class DetailActivity extends BaseActivity {

    public static final String TAG = Config.TAG;

    public static final String LOCAL_WEB_ROOT = "file:///android_asset/www/";
    public static final String LOCAL_WEB_ROOT_ERROR = LOCAL_WEB_ROOT + "error.html";

    public static final String PARAM = "DataResult";

    private DataResultInfo result;
    private String loadUrl;

    private WebView webView;

    private ProgressBar progressBar;

    public static void open(Context context, DataResultInfo result) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(PARAM, JSON.toJSONString(result));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 收藏
                if (!CollectDB.newInstance().isExist(result.getId())){
                    CollectDB.newInstance().save(result);
                    Snackbar.make(view, "收藏成功", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    CollectDB.newInstance().delete(result.getId());
                    Snackbar.make(view, "取消收藏", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        if (getIntent() != null) {
            String s = getIntent().getStringExtra(PARAM);
            result = JSON.parseObject(s, DataResultInfo.class);
            loadUrl = result.getUrl();
        }

        progressBar = (ProgressBar) findViewById(R.id.pb);
        progressBar.setMax(100);

        webView = (WebView) findViewById(R.id.wv);
        initWebView(webView);

        webView.loadUrl(loadUrl);
    }

    private void initWebView(final WebView webView) {
        // setting
        WebSettings settings = webView.getSettings();
        settings.setAppCacheEnabled(true);
//        settings.setAllowContentAccess(true);
//        settings.setAllowFileAccess(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);

        webView.goBackOrForward(20);
        webView.addJavascriptInterface(new JavascriptInterface(), "jsInterface");

        webView.setWebChromeClient(new MWebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.i(TAG, "onProgressChanged: " + newProgress);
                progressBar.setProgress(newProgress);
            }

        });

        webView.setWebViewClient(new MWebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i(TAG, "onPageStarted: " + url);
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);

                if (!LOCAL_WEB_ROOT_ERROR.equals(url)){
                    loadUrl = url;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i(TAG, "onPageFinished: " + url);
                progressBar.setVisibility(View.INVISIBLE);

                // 设置title
                String title = view.getTitle();
                if (!TextUtil.isEmpty(title)){
                    setTitle(title);
                }

//                view.loadUrl("javascript:window.jsInterface.showHTML" +
//                        "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.i(TAG, "onReceivedError: " + description);
//                switch (errorCode) {
//                    case WebViewClient.ERROR_UNKNOWN:
//                        break;
//                }

                if (LOCAL_WEB_ROOT_ERROR.equals(failingUrl)){
                    return;
                }

                view.loadUrl(LOCAL_WEB_ROOT_ERROR);
            }

        });

    }

    @Override
    public void onBackPressed() {
//        if (webView.canGoBack()) {
//            webView.goBack();
//        } else {
//            super.onBackPressed();
//        }

        if (!goBack(webView)){
            super.onBackPressed();
        }

    }

    /**
     * 正确的后退
     * @param webView
     * @return
     */
    private boolean goBack(WebView webView){
        boolean canGoBack = false;
        WebBackForwardList backForwardList = webView.copyBackForwardList();
        int currentIndex = backForwardList.getCurrentIndex();
        int index = -1;
        while (webView.canGoBackOrForward(index)){
            String s = backForwardList.getItemAtIndex(currentIndex + index).getOriginalUrl();
            if (s.equals(loadUrl) || s.equals(LOCAL_WEB_ROOT_ERROR)){
                index--;
            } else {
                canGoBack = true;
                webView.goBackOrForward(index);
                break;
            }

        }
        return canGoBack;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                webView.loadUrl(loadUrl);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(PARAM, JSON.toJSONString(result));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String s = savedInstanceState.getString(PARAM);
        result = JSON.parseObject(s, DataResultInfo.class);
        loadUrl = result.getUrl();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (webView != null){
            webView.destroy();
        }

        CollectDB.newInstance().close();

    }
}
