package com.example.androidexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.example.androidexample.R;

/**
 * @author chenyu
 */
public class S3Activity extends BaseActivity {
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3);
        TextView tv_s3 = (TextView) findViewById(R.id.tv_s3);
        findViewById(R.id.btn_s3).setOnClickListener(view -> {
            tv_s3.setText("我变了");
            handler.postDelayed(() -> {
                Intent intent = new Intent(this, S4Activity.class);
                startActivity(intent);
            }, 500);
        });
    }

}