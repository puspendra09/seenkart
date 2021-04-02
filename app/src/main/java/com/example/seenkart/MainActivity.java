package com.example.seenkart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv = findViewById(R.id.w1);
        wv.getSettings().setJavaScriptEnabled(true);

        wv.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!Common.connectivityAvailable(MainActivity.this))
                {
                    Intent in= new Intent(MainActivity.this,ErrorActivity.class);
                    in.putExtra("MSG",getString(R.string.checkinternet));
                    startActivity(in);
                    finish();
                }

                pd=new ProgressDialog(MainActivity.this);
                pd.setMessage(getString(R.string.Loading));
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (pd!=null)
                    pd.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Intent in= new Intent(MainActivity.this,ErrorActivity.class);
                in.putExtra("MSG",getString(R.string.somethingwentwrong)+error.toString());
                startActivity(in);
                finish();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        wv.loadUrl("http://www.seenkart.com");
    }

    @Override
    public void onBackPressed() {
        WebView wv=findViewById(R.id.w1);
        if (wv.canGoBack())
            wv.goBack();
        else
            super.onBackPressed();
    }
}