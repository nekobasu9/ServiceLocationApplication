package com.example.shotaro.servicelocationapplication;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.os.Handler;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by shotaro on 2015/09/15.
 */
public class MySerview extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient. OnConnectionFailedListener,LocationListener{

    private String TAG = "MyService";
    GoogleApiClient mGoogleApiClient;
    String str = "";


    Handler handler = null;
    private LocationRequest mLocationRequest;
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");

        //handler = new Handler();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);


        mGoogleApiClient.connect();



    }

    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {
        Log.i("TestService", "onBind");
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        handler = null;

        Log.d(TAG, "onDestroy()");

        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        mGoogleApiClient.disconnect();

    }
    @Override
    public void onConnected(Bundle bundle) {
        Log.d("onConnected", "onConnected");
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

        //setLocationTime();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
//        textLog += "onConnectionFailed()\n";
//        textView.setText(textLog);
//
//        if (mResolvingError) {
//            // Already attempting to resolve an error.
//            Log.d("", "Already attempting to resolve an error");
//
//            return;
//        } else if (connectionResult.hasResolution()) {
//
//        } else {
//            mResolvingError = true;
//        }
    }
    @Override
    public void onLocationChanged(Location location) {

        Log.d("LocationChange", "change");

        double lat = location.getLatitude();
        double lon = location.getLongitude();

        Log.d("lat",""+lat);
        Log.d("long",""+lon);





    }

    @Override
    public void onConnectionSuspended(int i) {
//        textLog += "onConnectionSuspended()\n";
//        textView.setText(textLog);
    }


//    void setLocationChanged(){
//
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                fusedLocationProviderApi.requestLocationUpdates(
////                        mGoogleApiClient, mLocationRequest, );
//                getLocationUpdates();
//            }
//        },1000);
//
//    }
//    void getLocationUpdates(){
//        if(mGoogleApiClient.isConnected()) {
//            LocationServices.FusedLocationApi.requestLocationUpdates(
//                    mGoogleApiClient, mLocationRequest, this);
//        }
//
//    }

}
