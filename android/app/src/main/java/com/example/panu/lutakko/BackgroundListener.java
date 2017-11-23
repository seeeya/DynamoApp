package com.example.panu.lutakko;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.proximi.proximiiolibrary.ProximiioAPI;
import io.proximi.proximiiolibrary.ProximiioGeofence;



public class BackgroundListener extends BroadcastReceiver {
    public String time = "";
    private static final String TAG = "Background";
    @Override
    public void onReceive(Context context, Intent intent) {
        ProximiioGeofence geofence;
        String phoneid = Build.MANUFACTURER + " " + Build.DEVICE + " " + Build.ID;
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
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd_hh:mm:ss");
                time = ft.format(dNow).toString();
                insertMySQL(geofence.getName(), time, phoneid, context);
                break;
            case ProximiioAPI.ACTION_GEOFENCE_EXIT:
                long dwellTime = intent.getLongExtra(ProximiioAPI.EXTRA_DWELL_TIME, 0);
                geofence = intent.getParcelableExtra(ProximiioAPI.EXTRA_GEOFENCE);
                String dwellminutes = "";
                double dwell = dwellTime / 60;
                int dwellint = (int) Math.round(dwell);
                dwellminutes = String.valueOf(Math.round(dwell));
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
                if (time.equals("")) {
                    time = "undefined"
                    updateMySQL(geofence.getName(), time, phoneid, context, dwellint);
                }

                break;

        }
    }
    public void insertMySQL(String place, String time, String phoneid, Context context) {
        String url = "http://walkonen.fi/apps/dynamoapp/mysql/insert.php?place="+place+"&time="+time+"&id="+phoneid+"";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
            }
        });
            queue.add(stringRequest);
        }

    public void updateMySQL(String place, String time, String phoneid, Context context, int duration) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd_hh:mm:ss");
        String exittime = ft.format(dNow).toString();
        String url = "http://walkonen.fi/apps/dynamoapp/mysql/update.php?place="+place+"&time="+time+"&id="+phoneid+"&duration="+duration+"&exit="+exittime+"";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
            }
        });
        queue.add(stringRequest);
    }
    }

}





