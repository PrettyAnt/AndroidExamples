package com.example.androidexample.ui;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.NonNull;

import com.example.androidexample.ForegroundService;
import com.example.androidexample.R;
import com.example.androidexample.ZxkfNotifiBuilder;

public class WebTelActivity extends BaseActivity implements HeadsetPlugReceiver.HeadsetPlugListener {

    private HeadsetPlugReceiver headsetPlugReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtel);
//        initData();
        checkAndShow();
//        Intent intent =new Intent(MediaStore.ACTION_PICK_IMAGES);
//        startActivityForResult(intent, 999);

//        int maxNumPhotosAndVideos = 10;  //该值表示在向用户显示时照片选择器中显示的媒体文件数量上限。
//        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
//        intent.setType("video/*");
//        intent.putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, maxNumPhotosAndVideos);
//        startActivityForResult(intent, 888);
//        Log.w(ZxkfNotifiBuilder.TAG, "create: --------WebTelAcitivity");
        findViewById(R.id.tv_close).setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(ZxkfNotifiBuilder.TAG, "onResume: --------WebTelAcitivity");
    }

    //按下back键监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK://返回键
                Intent intent = new Intent(this, S4Activity.class);
                startActivity(intent);
                return false;
            default:
                return super.onKeyDown(keyCode, event);

        }
    }


    private void initData() {
        //            Intent intent = new Intent();
//            intent.setComponent(new ComponentName("com.example.foregrounddemoo",
//                    "com.example.foregrounddemoo.ForegroundService"));
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                startForegroundService(intent);
//            } else {
//                startService(intent);
//            }


        Intent intent = new Intent(this, ForegroundService.class);
        startService(intent);
        headsetPlugReceiver = new HeadsetPlugReceiver(this);
        headsetPlugReceiver.registRecevier(this);
    }
    private void checkAndShow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            NotificationManager manager = getSystemService(NotificationManager.class);
            boolean             b       = manager.areNotificationsEnabled();
            if (!b) {
                String[] strings = {Manifest.permission.POST_NOTIFICATIONS};
                requestPermissions(strings, 0);
                return;
            }
//            showNotification();
            return;
        } else {//继续执行老版本逻辑}}

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(ZxkfNotifiBuilder.TAG, "destroy: --------WebTelAcitivity");

        Intent intent = new Intent(this, ForegroundService.class);
        stopService(intent);

        headsetPlugReceiver.unRegistRecevier(this);
    }

    @Override
    public void finish() {
        super.finish();
        Log.i(ZxkfNotifiBuilder.TAG, "finish: --------WebTelAcitivity");
    }

    @Override
    public void onHeadsetPlug(boolean isPlug) {
        Log.w(ZxkfNotifiBuilder.TAG, "onHeadsetPlug: " + isPlug);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("prettyant", "----------授权成功-------- ");
        initData();
    }
}