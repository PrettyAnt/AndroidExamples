package com.example.androidexample;

import android.app.Service;

/**
 * @author chenyu
 * My personal blog  https://prettyant.github.io
 * <p>
 * Created on 4:18 下午  27/06/22
 * PackageName : com.example.supendwindow
 * describle :
 */
@Deprecated
public class BackgroundEvent {
    private boolean alive;
    private Service service;

    public BackgroundEvent(boolean alive, Service service) {
        this.alive = alive;
        this.service = service;
    }

    public boolean isAlive() {
        return alive;
    }

    public Service getService() {
        return service;
    }
}
