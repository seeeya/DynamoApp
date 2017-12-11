package com.example.panu.lutakko;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.proximi.proximiiolibrary.ProximiioAPI;
import io.proximi.proximiiolibrary.ProximiioGeofence;



public class BackgroundListener extends BroadcastReceiver {
    private static final String TAG = "Background";
    @Override
    public void onReceive(Context context, Intent intent) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        ProximiioGeofence geofence;
        String phoneid = Build.MANUFACTURER + " " + Build.FINGERPRINT;
        try {
            phoneid = URLEncoder.encode(phoneid, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        switch (intent.getAction()) {
            case ProximiioAPI.ACTION_GEOFENCE_ENTER:
                geofence = intent.getParcelableExtra(ProximiioAPI.EXTRA_GEOFENCE);
                String pageurl = "https://walkonen.fi/apps/dynamoapp/";
                switch (geofence.getName()) {
                    case "Ravintola Fiilu":
                        pageurl = "https://walkonen.fi/apps/dynamoapp/?page=fiilu";
                        intent.putExtra("URL", pageurl);
                        break;
                    case "JAMK Ravintola Bittipannu":
                        pageurl = "https://walkonen.fi/apps/dynamoapp/?page=bittipannu";
                        intent.putExtra("URL", pageurl);
                        break;
                }
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
                String time = ft.format(dNow).toString();
                String url_name = null;
                try {
                    url_name = URLEncoder.encode(geofence.getName(), "UTF-8");
                    time = URLEncoder.encode(time, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String url = "https://walkonen.fi/apps/dynamoapp/mysql/insert.php?place="+url_name+"&time="+time+"&id="+phoneid+"";
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
                Date dexit = new Date();
                String exittime = ft.format(dexit).toString();
                String url_name1 = null;
                try {
                    url_name1 = URLEncoder.encode(geofence.getName(), "UTF-8");
                    exittime = URLEncoder.encode(exittime, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String u_url = "https://walkonen.fi/apps/dynamoapp/mysql/update.php?place="+url_name1+"&id="+phoneid+"&duration="+dwellint+"&exit="+exittime+"";
                RequestQueue queue1 = Volley.newRequestQueue(context);
                StringRequest stringRequest1 = new StringRequest(Request.Method.GET, u_url,
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
                queue1.add(stringRequest1);
                break;
        }
        }

}









