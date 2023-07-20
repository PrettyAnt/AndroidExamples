package com.example.androidexample.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidexample.BackgroundEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author chenyu
 * My personal blog  https://prettyant.github.io
 * <p>
 * Created on 8:50 上午  15/07/22
 * PackageName : com.example.foregrounddemoo.ui
 * describle :
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("prettyant", "onResume: ");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("prettyant", "onPostResume: ");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void backGroundEvent2(BackgroundEvent backgroundEvent) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
