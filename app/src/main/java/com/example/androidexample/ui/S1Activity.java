package com.example.androidexample.ui;

import android.content.Context;
import android.content.Intent;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.androidexample.R;
import com.example.testmodule.WebTelModuleActivity;

public class S1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1);
        TextView tv_show = (TextView) findViewById(R.id.tv_show);
        findViewById(R.id.tv_open).setOnClickListener(view -> {
            Intent intent = new Intent(this, S2Activity.class);
            startActivity(intent);
        });
        findViewById(R.id.tv_stop).setOnClickListener(view -> {
//            Intent intent = new Intent();
//            intent.setComponent(new ComponentName("com.example.foregrounddemoo",
//                    "com.example.foregrounddemoo.ForegroundService"));
//            stopService(intent);
//            boolean b = checkIsWired(this);
//            Log.i(ZxkfNotifiBuilder.TAG, "onCreate: " + b);
//            String model = Build.MODEL;
//            tv_show.setText("当前型号：" + model);
            Intent intent = new Intent(this, WebTelModuleActivity.class);
            startActivity(intent);
        });

    }

    private boolean checkIsWired(Context context) {

        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            AudioDeviceInfo[] devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);

            for (AudioDeviceInfo device : devices) {

                int deviceType = device.getType();

                if (deviceType == AudioDeviceInfo.TYPE_WIRED_HEADSET

                        || deviceType == AudioDeviceInfo.TYPE_WIRED_HEADPHONES

                        || deviceType == AudioDeviceInfo.TYPE_BLUETOOTH_A2DP

                        || deviceType == AudioDeviceInfo.TYPE_BLUETOOTH_SCO) {

                    return true;

                }

            }

        } else {

            return audioManager.isWiredHeadsetOn() || audioManager.isBluetoothScoOn() || audioManager.isBluetoothA2dpOn();

        }

        return false;

    }
}