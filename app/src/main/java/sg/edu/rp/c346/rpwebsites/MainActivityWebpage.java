package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivityWebpage extends AppCompatActivity{
    WebView wvPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_webpage);
        wvPage = findViewById(R.id.WebViewPage);
        wvPage.setWebViewClient(new WebViewClient());
        wvPage.getSettings().setJavaScriptEnabled(true);

        Intent intentReceived = getIntent();

        String URL = intentReceived.getStringExtra("URL");
        Log.i("URL", URL + "");
        wvPage.loadUrl(URL);

        wvPage.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
