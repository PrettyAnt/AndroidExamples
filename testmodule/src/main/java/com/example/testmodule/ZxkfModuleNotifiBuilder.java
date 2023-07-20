package com.example.testmodule;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;


/**
 * @author chenyu
 * My personal blog  https://prettyant.github.io
 * <p>
 * Created on 4:47 下午  11/07/22
 * PackageName : com.example.foregrounddemoo
 * describle :
 */
public class ZxkfModuleNotifiBuilder {
    public static final  int    SERVICE_ID              = 1;
    public static final  String TAG                     = "prettyant";
    private static final int    notificationId          = 11;
    private static final String notificationChannelId   = "spdbzxkf_channel_id_01";
    private static final String notificationChannelName = "Foreground Service Notification";
    private static final ZxkfModuleNotifiBuilder   ourInstance = new ZxkfModuleNotifiBuilder();
    private static       Context             context;
    private              Notification        notification;
    private              NotificationManager manager;
    private              Vibrator            vibrator;
    private              Handler             handler     = new Handler(Looper.getMainLooper());
    private              Runnable            runnable    = new Runnable() {
        @Override
        public void run() {
            vibrator.vibrate(150);
        }
    };

    private ZxkfModuleNotifiBuilder() {
    }

    public static ZxkfModuleNotifiBuilder with(Context sContext) {
        context = sContext;
        return ourInstance;
    }

    /**
     * 创建服务通知
     */
    public ZxkfModuleNotifiBuilder buidForegroundNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, notificationChannelId);
        //通知小图标
        builder.setSmallIcon(R.drawable.c_module_close);
        //通知标题
        builder.setContentTitle("浦大喜奔");
        //通知内容
        builder.setContentText("语音通话中,轻击以继续");
        //设置通知显示的时间
// builder.setWhen(System.currentTimeMillis());
        //设置启动的内容
        Intent intent = new Intent(context, WebTelModuleActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_IMMUTABLE);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        //设置通知优先级
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        //设置为进行中的通知
        builder.setOngoing(true);
// builder.setChannelId("1");
        // 获取构建好的 Notification
        Notification notification = builder.build();
        this.notification = notification;
        return this;

    }


    /**
     * 创建通知渠道
     */
    public ZxkfModuleNotifiBuilder buidNotificationChannel() {
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //修改安卓8.1以上系统报错
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(notificationChannelId, notificationChannelName, NotificationManager.IMPORTANCE_LOW);
            notificationChannel.setDescription("描述");
            notificationChannel.setShowBadge(false);
            //如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            //是否显示角标
            notificationChannel.setShowBadge(false);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            manager.createNotificationChannel(notificationChannel);
        } else {
            if (notification == null) {
                return this;
            }
            manager.notify(notificationId, notification);
        }
//        manager.notify(notificationId, notification);
        manager.notify(notificationId, notification);
        return this;
    }

    /**
     * 开启震动
     */
    public ZxkfModuleNotifiBuilder shakePhone(boolean isOpen) {
        if (!isOpen) {
            return this;
        }
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(200);
            handler.postDelayed(runnable, 320);
//            vibrator.cancel();
        }
        return this;
    }

    /**
     * notification 绑定服务
     *
     * @param service
     * @return
     */
    public ZxkfModuleNotifiBuilder start(Service service) {
        if (notification == null) {
            return this;
        }
        service.startForeground(notificationId, notification);
        return this;
    }
}
