package com.example.androidexample.ui;

import android.bluetooth.BluetoothHeadset;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.androidexample.ZxkfNotifiBuilder;


public class HeadsetPlugReceiver extends BroadcastReceiver {

    private HeadsetPlugListener mHeadsetPlugListener;

    public HeadsetPlugReceiver(HeadsetPlugListener headsetPlugListener) {
        this.mHeadsetPlugListener = headsetPlugListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(ZxkfNotifiBuilder.TAG, "onReceive: " + action);
        if (Intent.ACTION_HEADSET_PLUG.equals(action)) {
            if (intent.hasExtra("state")) {
                if (intent.getIntExtra("state", 0) == 0) {
                    // 外放
                    mHeadsetPlugListener.onHeadsetPlug(false);
                } else if (intent.getIntExtra("state", 0) == 1) {
                    // 耳机
                    mHeadsetPlugListener.onHeadsetPlug(true);
                }
            }
        }
    }

    //注册蓝牙连接状态
    public void registRecevier(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        intentFilter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
        context.registerReceiver(this, intentFilter);
    }

    public void unRegistRecevier(Context context) {
        context.unregisterReceiver(this);
    }

    public interface HeadsetPlugListener {
        void onHeadsetPlug(boolean isPlug);// true说明有耳机 false说明没有耳机
    }


}