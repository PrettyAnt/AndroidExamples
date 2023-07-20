package com.example.androidexample;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author chenyu
 * My personal blog  https://prettyant.github.io
 * <p>
 * Created on 5:23 下午  15/07/22
 * PackageName : com.example.foregrounddemoo
 * describle :
 */
public class LifecycleTest implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreated(LifecycleOwner source) {
        Log.i("prettyant", "ON_CREATE: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(LifecycleOwner source) {
        Log.i("prettyant", "ON_RESUME: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause(LifecycleOwner source) {
        Log.i("prettyant", "ON_PAUSE: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner source, Lifecycle.Event event) {
        Log.i("prettyant", "onAny: " + event);
    }
}
