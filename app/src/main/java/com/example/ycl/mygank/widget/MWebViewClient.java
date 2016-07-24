package com.example.ycl.mygank.widget;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 自定义实现WebViewClient类
 */
public class MWebViewClient extends WebViewClient {

    /**
     * 在webview加载URL的时候可以截获这个动作, 这里主要说它的返回值的问题：
     * 1、返回: return true;  webview处理url是根据程序来执行的。
     * 2、返回: return false; webview处理url是在webview内部执行。
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    /**
     * 在webview开始加载页面的时候回调该方法
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

    }

    /**
     * 在webview加载页面结束的时候回调该方法
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    /**
     * 加载页面失败的时候回调该方法
     */
    // 该方法为android23中新添加的API，android23中会执行该方法
    @TargetApi(21)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

    }

    /**
     * 加载页面失败的时候回调该方法
     */
    /**
     * 在android23中改方法被onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) 替代
     * 因此在android23中执行替代方法
     * 在android23之前执行该方法
     *
     * @param view
     * @param errorCode
     * @param description
     * @param failingUrl
     */
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

    }

    /**
     * 更新历史记录
     * @param view
     * @param url
     * @param isReload
     */
    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
    }

    /**
     * 应用程序重新请求网页数据
     * @param view
     * @param dontResend
     * @param resend
     */
    @Override
    public void onFormResubmission(WebView view, Message dontResend, Message resend) {
        super.onFormResubmission(view, dontResend, resend);
    }

    /**
     * 获取返回信息授权请求
     * @param view
     * @param handler
     * @param host
     * @param realm
     */
    @Override
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm);
    }

    /**
     * 处理http请求
     * @param view
     * @param request
     * @param errorResponse
     */
    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
    }

    /**
     * 处理https请求
     * @param view
     * @param handler
     * @param error
     */
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
    }

    /**
     * 处理在浏览器中的按键事件
     * @param view
     * @param event
     * @return
     */
    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return super.shouldOverrideKeyEvent(view, event);
    }

    /**
     * 加载页面资源时会调用
     * @param view
     * @param url
     */
    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }
}