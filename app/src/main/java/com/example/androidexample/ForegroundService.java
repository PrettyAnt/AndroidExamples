package com.example.androidexample;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.androidexample.ui.S2Activity;

/**
 * 前台服务进程
 */
public class ForegroundService extends Service {
    private static final int          SERVICE_ID              = 1;
    private static final String       notificationChannelId   = "spdbzxkf_channel_id_01";
    private static final String       notificationChannelName = "Foreground Service Notification";
    private static final String       TAG                     = "prettyant";
    private static final int          notificationId          = 11;
    private              Notification notification;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: serevice" + Thread.currentThread().getName());
        ZxkfNotifiBuilder
                .with(this)
                .buidForegroundNotification()
                .buidNotificationChannel()
                .shakePhone(true)
                .start(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: " + Thread.currentThread().getName() + "  手机版本  " + Build.VERSION.SDK_INT);
//        //判断版本
        return START_STICKY;
    }

    /**
     * 创建通知渠道
     */
    private void createNotificationChannel() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (manager != null) {
            //修改安卓8.1以上系统报错
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(notificationChannelId, notificationChannelName, NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.setDescription("Channel description");
                //如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
                notificationChannel.enableLights(false);
                notificationChannel.setLightColor(Color.RED);
                //是否显示角标
                notificationChannel.setShowBadge(false);
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
                manager.createNotificationChannel(notificationChannel);
            } else {
                manager.notify(notificationId, notification);
                Log.i(TAG, "createNotificationChannel:   notify" + notificationId + " notifaction:" + notification);
            }
        }
    }

    /**
     * 创建服务通知
     */
    private void createForegroundNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, notificationChannelId);
        //通知小图标
        builder.setSmallIcon(R.drawable.c_close);
        //通知标题
        builder.setContentTitle("浦大喜奔");
        //通知内容
        builder.setContentText("语音通话中,轻击以继续");
        //设置通知显示的时间
        builder.setWhen(System.currentTimeMillis());
        builder.setShowWhen(true);
        //设置启动的内容
        Intent intent = new Intent(this, S2Activity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        //设置通知优先级
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        //设置为进行中的通知
        //设置常驻状态栏
        builder.setOngoing(true);

// builder.setChannelId("1");
        // 获取构建好的 Notification
        notification = builder.build();
        //设置为默认的声音
//        notification.defaults = Notification.DEFAULT_SOUND;
        startForeground(notificationId, notification);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ----------");

    }

}
