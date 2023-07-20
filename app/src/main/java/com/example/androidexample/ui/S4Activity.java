package com.example.androidexample.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidexample.LifecycleTest;
import com.example.androidexample.R;

public class S4Activity extends BaseActivity {

    private LifecycleTest lifecycleTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s4);
        findViewById(R.id.btn_s4).setOnClickListener(view -> {
            Intent intent = new Intent(this, WebTelActivity.class);
            startActivity(intent);
        });
        lifecycleTest = new LifecycleTest();
        getLifecycle().addObserver(lifecycleTest);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(lifecycleTest);
    }
}