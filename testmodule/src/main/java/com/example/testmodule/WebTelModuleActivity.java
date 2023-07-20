package com.example.testmodule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class WebTelModuleActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_webtel);
        initData();
        findViewById(R.id.tv_close).setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(ZxkfModuleNotifiBuilder.TAG, "onResume: --------WebTelAcitivity");
    }

    //按下back键监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK://返回键
                Intent intent = new Intent(this, ModuleMainActivity.class);
                startActivity(intent);
                return false;
            default:
                return super.onKeyDown(keyCode, event);

        }
    }


    private void initData() {
        Intent intent = new Intent(this, ForegroundModuleService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(ZxkfModuleNotifiBuilder.TAG, "destroy: --------WebTelAcitivity");

        Intent intent = new Intent(this, ForegroundModuleService.class);
        stopService(intent);

    }

    @Override
    public void finish() {
        super.finish();
        Log.i(ZxkfModuleNotifiBuilder.TAG, "finish: --------WebTelAcitivity");
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("prettyant", "----------授权成功-------- ");
        initData();
    }
}