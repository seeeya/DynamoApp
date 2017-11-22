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

import com.loopj.android.http.HttpGet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;


import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HTTP;
import io.proximi.proximiiolibrary.ProximiioAPI;
import io.proximi.proximiiolibrary.ProximiioGeofence;

public class BackgroundListener extends BroadcastReceiver {

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
                SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
                String time = ft.format(dNow).toString();
                try {
                    insertMySQL(geofence.getName(), time, phoneid, context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
    public void insertMySQL(String place, String time, String phoneid, Context context) throws IOException {
        String link = "http://walkonen.fi/apps/dynamoapp/mysql/insert.php";
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        String postdata = URLEncoder.encode("place","UTF-8")+"="+URLEncoder.encode(place, "UTF-8") +"&"+
                URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(time, "UTF-8") +"&"+
                URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(phoneid, "UTF-8");
        bufferedWriter.write(postdata);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
        String result = "";
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(result)
                .setTitle("testi");
        AlertDialog dialog = builder.create();
    }



}

