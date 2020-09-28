package com.kim9212.stackswip;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    private EditText et;
    private WebView wv;
    private Button bt;
    private ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();
        wv.loadUrl("https://www.jobplanet.co.kr/contents");
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
        webSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        webSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        webSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        webSettings.setSupportZoom(false); // 화면 줌 허용 여부
        webSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        webSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        et = findViewById(R.id.et);
        wv = findViewById(R.id.wv);
        bt = findViewById(R.id.bt);

        progressDialog = new ProgressDialog(this);


        wv.setWebViewClient(new WebViewClient() {
            @Override //로딩 감지
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                progressDialog.setMessage("개발자 모두 파이팅");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

            }

            @Override //로딩끝날때
            public void onPageFinished(WebView view, String url) {

                progressDialog.dismiss();
            }
        });

        et.setOnKeyListener(new View.OnKeyListener() { //enter먹게하는거
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    String address = et.getText().toString();
                    if (!address.startsWith("https://")) {
                        address = "https://" + address;
                    }
                    wv.loadUrl(address);
                    return true;
                }
                return false;
            }
        });
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    bt.callOnClick();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    public void loadsite(View view) {
        String address = et.getText().toString();
        if (!address.startsWith("https://")) {
            address = "https://" + address;
        }
        wv.loadUrl(address);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this,MainActivity2.class);
        startActivity(intent);
    }

        public void clickbtn (View view){
            wv.loadUrl("https://www.jobkorea.co.kr/");
            WebSettings webSettings = wv.getSettings();
        }
        public void clickbtn2 (View view){
            wv.loadUrl("https://www.saramin.co.kr/zf_user/");
            WebSettings webSettings = wv.getSettings();
        }
        public void clickbtn3 (View view){
            wv.loadUrl("https://www.wanted.co.kr/");
            WebSettings webSettings = wv.getSettings();
        }
    public void clickbtn4(View view) {
        wv.loadUrl("http://m.albamon.com/");
        WebSettings webSettings = wv.getSettings();
    }


}

