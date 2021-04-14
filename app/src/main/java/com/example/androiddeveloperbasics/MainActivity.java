package com.example.androiddeveloperbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "Hello World");
//        Log: Logcat 창에 로그 메시지를 보내기위한 클래스
//        d: 디버그 수준, e: 에러 수준, w: 경고 수준, i: 정보 수준
//        첫 번째 인수는 Logcat 창에서 메시지를 필터링하는 데 사용하는 태그
    }
}