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

    private EditText et, et1;
    private WebView wv;
    private Button bt;
    private ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();
        wv.loadUrl("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EB%8D%B0%EC%9D%B4%ED%8A%B8+%EB%8F%99%EB%84%A4+%EC%B6%94%EC%B2%9C&oquery=%EB%8D%B0%EC%9D%B4%ED%8A%B8+%EB%8F%99%EB%84%A4&tqi=UxEr4lp0J14ssg%2FccbNssssstgR-399963");
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
        et1 = findViewById(R.id.et1);
        et = findViewById(R.id.et);
        wv = findViewById(R.id.wv);
        bt = findViewById(R.id.bt);

        progressDialog = new ProgressDialog(this);


        wv.setWebViewClient(new WebViewClient() {
            @Override //로딩 감지
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                progressDialog.setMessage("행복해주세요");
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

    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }
        public void clickbtn (View view){

            try {
                String s = et1.getText().toString();

                Uri uri = Uri.parse("kakaomap://search?q=" + s + "맛집" + "&p=37.537229,127.005515");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } catch (Exception e) {
                throw e;
            }
        }


        public void clickbtn2 (View view){


        }

        public void clickbtn3 (View view){
            try {
                String s = et1.getText().toString();

                Uri uri = Uri.parse("kakaomap://search?q=" + s + "영화관" + "&p=37.537229,127.005515");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } catch (Exception e) {
                throw e;
            }

        }

        public void clickbtn4 (View view){


        }


    }

