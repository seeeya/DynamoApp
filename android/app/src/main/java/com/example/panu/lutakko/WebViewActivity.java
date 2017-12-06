package com.example.panu.lutakko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        Intent intent = getIntent();
        String url;
        if (intent.hasExtra("URL")) {
            url = intent.getStringExtra("URL");
        }
        else {
            String location = intent.getStringExtra("location");
            url = "http://student.labranet.jamk.fi/~K2065/test/iot.php?location=" + location;
        }
        webView.loadUrl(url);
    }
}
