package com.example.panu.lutakko;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
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
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        Intent intent = getIntent();
        String url = "";
        if (intent.hasExtra("URL")) {
            url = intent.getStringExtra("URL");
        }
        else {
            Uri data = intent.getData();
            String query = data.getQuery();
            switch (query) {
                case "place=fiilu":
                    url = "https://walkonen.fi/apps/dynamoapp/?page=fiilu";
                    break;
                case "place=bittipannu":
                    url = "https://walkonen.fi/apps/dynamoapp/?page=bittipannu";
                    break;
                case "place=rajakatu":
                    url = "https://walkonen.fi/apps/dynamoapp/?page=rajakatu";
                    break;
                case "place=sodexo":
                    url = "https://walkonen.fi/apps/dynamoapp/?page=rajakatu";
                    break;
            }
        }
        webView.loadUrl(url);
    }
}

