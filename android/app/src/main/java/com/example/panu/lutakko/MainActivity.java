package com.example.panu.lutakko;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


import io.proximi.proximiiolibrary.ProximiioAPI;
import io.proximi.proximiiolibrary.ProximiioGeofence;
import io.proximi.proximiiolibrary.ProximiioGoogleMapHelper;
import io.proximi.proximiiolibrary.ProximiioListener;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ProximiioAPI proximiioAPI;
    @Nullable private ProximiioGoogleMapHelper mapHelper;
    private Toolbar toolbar;
    List<Marker> markers = new ArrayList<Marker>();
    private Marker mDynamo;
    private Marker mFiilu;
    private Marker mRadis;
    private int arrayindex = 0;
    private static final LatLng Dynamo = new LatLng(62.241704, 25.759495);
    private static final LatLng Fiilu = new LatLng(62.240799, 25.757682);
    private static final LatLng Radis = new LatLng(62.251267, 25.742995);
    private GoogleMap gMap;
    private String pageurl = "http://walkonen.fi/apps/dynamoapp/";



    private static final String TAG = "LunchApp";
    public static final String AUTH = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImlzcyI6ImRiMDRhNjMyLWM2OTgtNDYwMC04ZDc4LWM0YTczYTFkZGI3MCIsInR5cGUiOiJhcHBsaWNhdGlvbiIsImFwcGxpY2F0aW9uX2lkIjoiMjkwM2JmNmEtMzA5NS00MDcxLTlkODktM2MzOTU0MjMwNDViIn0.2jU0sheug_ge8WiwA10fHIZViaWlAwnBzsRGGHhTSBA"; // TODO: Replace with your own!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proximiioAPI = new ProximiioAPI(TAG, this);
        proximiioAPI.setListener(new ProximiioListener() {
            @Override
            public void geofenceEnter(ProximiioGeofence geofence) {
                sendEnterNotification(geofence);
            }

            @Override
            public void geofenceExit(ProximiioGeofence geofence, @Nullable Long dwellTime) {
                String dwellminutes = "";
                if (dwellTime != null) {
                    double dwell = dwellTime / 60;
                    dwellminutes = String.valueOf(Math.round(dwell));
                }
                sendExitNotification(geofence, dwellminutes);
            }
        });
        proximiioAPI.setAuth(AUTH);
        proximiioAPI.setActivity(this);

        findViewById(R.id.arrowUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveCamera(true);
            }
        });
        findViewById(R.id.arrowDown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveCamera(false);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(TAG);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapHelper != null) {
            mapHelper.destroy();
        }
        proximiioAPI.destroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        proximiioAPI.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        proximiioAPI.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void sendEnterNotification(ProximiioGeofence geofence) {
        Intent resultIntent = new Intent(MainActivity.this, WebViewActivity.class);
        resultIntent.putExtra("URL", pageurl);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle("You entered " + geofence.getName())
                        .setContentText("Click here to view menus and more!");
        NotificationManager notifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder.setContentIntent(resultPendingIntent);
        notifyManager.notify(1, mBuilder.build());
    }

    public void sendExitNotification(ProximiioGeofence geofence, String dwell) {
        Intent resultIntent = new Intent(MainActivity.this, WebViewActivity.class);
        resultIntent.putExtra("URL", pageurl);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        String text;
        if (dwell == "") {
            text = "Click here to give feedback!";
        }
        else text = "You spent " + dwell + " minutes here! Click here to give feedback!";
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle("You exited " + geofence.getName())
                        .setContentText(text);
        NotificationManager notifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder.setContentIntent(resultPendingIntent);
        notifyManager.notify(1, mBuilder.build());
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        mapHelper = new ProximiioGoogleMapHelper.Builder(this, googleMap)
                //.positioning(false)
                .showGeofenceMarkers(false)
                .showInputMarkers(false)
                .listener(new ProximiioGoogleMapHelper.Listener() {

                }).build();
        LatLng lutakko = new LatLng(62.241137, 25.759345);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lutakko, 13));

        googleMap.setOnMyLocationButtonClickListener(mapHelper);
        googleMap.setOnMapClickListener(mapHelper);
        googleMap.setOnCameraIdleListener(mapHelper);
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.setSnippet("Click to get menus, details and more!");
                switch (marker.getTitle()) {
                    case "JAMK Ravintola Bittipannu": arrayindex = 1;
                    break;
                    case "JAMK Ravintola Radis": arrayindex = 2;
                    break;
                    case "Ravintola Fiilu": arrayindex = 0;
                    break;
                }
                return false;
            }
        });
        googleMap.setOnCameraMoveStartedListener(mapHelper);
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                String pageurl;
                switch (marker.getTitle()) {
                    case "Ravintola Fiilu":
                        pageurl = new String("https://walkonen.fi/apps/dynamoapp/?page=fiilu");
                        intent.putExtra("URL", pageurl);
                        break;
                    case "JAMK Ravintola Bittipannu":
                        pageurl = new String("https://walkonen.fi/apps/dynamoapp/?page=bittipannu");
                        intent.putExtra("URL", pageurl);
                        break;
                    case "JAMK Ravintola Radis":
                        pageurl = new String("https://walkonen.fi/apps/dynamoapp/?page=rajakatu");
                        intent.putExtra("URL", pageurl);
                        break;
                }
                startActivity(intent);
            }
        });
        googleMap.setOnInfoWindowLongClickListener(new GoogleMap.OnInfoWindowLongClickListener() {
            @Override
            public void onInfoWindowLongClick(Marker marker) {
                //directions to marker?
            }
        });
        mDynamo = googleMap.addMarker(new MarkerOptions()
                .position(Dynamo)
                .title("JAMK Ravintola Bittipannu"));
        mFiilu = googleMap.addMarker(new MarkerOptions()
                .position(Fiilu)
                .title("Ravintola Fiilu"));
        mRadis = googleMap.addMarker(new MarkerOptions()
                .position(Radis)
                .title("JAMK Ravintola Radis"));
        markers.add(mFiilu);
        markers.add(mDynamo);
        markers.add(mRadis);
    }
     public void moveCamera(boolean direction) {
        if (direction) {
            if (arrayindex == 2) arrayindex = -1;
            arrayindex++;
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers.get(arrayindex).getPosition(), 13));
            markers.get(arrayindex).setSnippet("Click to get menus, details and more!");
            markers.get(arrayindex).showInfoWindow();
        }
        else {
            if (arrayindex == 0) arrayindex = 3;
            arrayindex--;
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers.get(arrayindex).getPosition(), 13));
            markers.get(arrayindex).setSnippet("Click to get menus, details and more!");
            markers.get(arrayindex).showInfoWindow();
        }


     }



}
