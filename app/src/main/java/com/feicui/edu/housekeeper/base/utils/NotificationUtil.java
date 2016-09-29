package com.feicui.edu.housekeeper.base.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.activity.HomeActivity;

public class NotificationUtil {
    private static final int NOTIFICATION_ID = 1;
    private static NotificationManager notificationManager;
    private static Notification notification;

    public static void showAppIconNotification(Context context){
        if (notification == null){
            notification = new Notification();
        }
        notification.icon = R.drawable.home_right;
        notification.when = System.currentTimeMillis();
        notification.flags = PendingIntent.FLAG_UPDATE_CURRENT;
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.view_notification);
        notification.contentView = remoteViews;
        notification.tickerText = "新消息";

        //设置通知点击后事件，创建延迟意图
        Intent intent = new Intent(context, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.
                getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = pendingIntent;
        //系统服务
        if (notificationManager == null){
            notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        //发送通知
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
    public static void closeAppIconNotification(Context context){
        if (notificationManager == null){
            notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        notificationManager.cancel(NOTIFICATION_ID);
    }


}
