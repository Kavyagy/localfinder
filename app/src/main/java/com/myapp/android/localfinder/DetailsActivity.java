package com.myapp.android.localfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class DetailsActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        WebView webView = (WebView) findViewById(R.id.webview);
        Results result = getIntent().getParcelableExtra("result");
        webView.loadUrl(result.businessURL);

    }
}
