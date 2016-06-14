package com.example.ycl.mygank.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.example.ycl.mygank.R;

public class DetailActivity extends AppCompatActivity {

    public static final String PARAM = "url";

    private String url;

    private WebView webView;

    public static void open(Context context, String url){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(PARAM, url);
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getIntent() != null){
            url = getIntent().getStringExtra(PARAM);
        }

        webView = (WebView) findViewById(R.id.wv);
        webView.goBackOrForward(8);

        webView.loadUrl(url);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(PARAM, url);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        url = savedInstanceState.getString(PARAM);
    }

}
