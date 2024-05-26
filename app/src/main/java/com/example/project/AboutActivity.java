package com.example.project;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    /* ================== VARIABLES ================== */
    private WebView aboutView;
    /* =============================================== */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Get ref to WebView and add webclient
        aboutView = findViewById(R.id.webView);
        aboutView.setWebViewClient(new WebViewClient());
        aboutView.getSettings().setJavaScriptEnabled(true);

        aboutView.loadUrl("file:///android_asset/index.html");
    }
}
