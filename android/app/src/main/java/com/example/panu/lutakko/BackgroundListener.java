package com.example.panu.lutakko;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import io.proximi.proximiiolibrary.ProximiioAPI;
import io.proximi.proximiiolibrary.ProximiioGeofence;

import static android.content.Context.NOTIFICATION_SERVICE;

public class BackgroundListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ProximiioGeofence geofence;

        switch (intent.getAction()) {
            case ProximiioAPI.ACTION_GEOFENCE_ENTER:
                String pageurl = "http://walkonen.fi/apps/dynamoapp/";
                geofence = intent.getParcelableExtra(ProximiioAPI.EXTRA_GEOFENCE);
                Intent resultIntent = new Intent(context, WebViewActivity.class);
                resultIntent.putExtra("URL", pageurl);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder mBuilder =
                         new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.notification)
                                .setContentTitle("You Entered " + geofence.getName())
                                .setContentText("Tap here to view menus and more!");
                NotificationManager notifyManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder.setContentIntent(resultPendingIntent);
                notifyManager.notify(1, mBuilder.build());
                break;
            case ProximiioAPI.ACTION_GEOFENCE_EXIT:
                long dwellTime = intent.getLongExtra(ProximiioAPI.EXTRA_DWELL_TIME, 0);
                geofence = intent.getParcelableExtra(ProximiioAPI.EXTRA_GEOFENCE);
                String dwellminutes = "";
                if (dwellTime != 0) {
                    double dwell = dwellTime / 60;
                    dwellminutes = String.valueOf(Math.round(dwell));
                }
                String text;
                if (dwellminutes == "") {
                    text = "Tap here to give feedback!";
                }
                else text = "You spent " + dwellminutes + " minutes here! Tap here to give feedback!";
                NotificationCompat.Builder mBuilder2 =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.notification)
                                .setContentTitle("You exited " + geofence.getName())
                                .setContentText(text);
                NotificationManager notifyManager2 =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notifyManager2.notify(1, mBuilder2.build());
                break;
        }
    }



}

