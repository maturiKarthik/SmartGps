package com.example.iosdev.smartgps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.List;

/**
 * Created by karthik on 26/03/2018.
 * I have left log to debug..
 */

public class Gps implements LocationListener{

    Context context;
    LocationManager locationManager;
    Location lastKnownLocation ;

    /**
     *
     * @param context
     * @param locationManager
     */

    public Gps(Context context,LocationManager locationManager) {
        this.context = context;
        this.locationManager = locationManager;
    }

    /**
     * This Function Has to be Called From another Class
     * it returns Lat&lon in String
     * U can change it ..
     *
     * NOTE: This function call isLocationEnabled() to
     * check wether the gps is active or not ..
     */
    public  String getLatLong() {

         if (isLocationEnabled() == true) {
            Log.d("data", "GPS_ENABLED");

            lastKnownLocation = getLastKnownLocation(locationManager);
            /**
             * if(lastKnownLocation == null){ dosomething ..}
             */
            Log.d("DATA-Lat",""+lastKnownLocation.getLatitude());
            Log.d("DATA-Lon",""+lastKnownLocation.getLongitude());

        } else {
            Log.d("data", "GPS_NOT_ENABLED");
        }

     return "Latti:"+lastKnownLocation.getLatitude()+"/nLongitude:"+lastKnownLocation.getLongitude();

    }


    /**
     * Check Wether GPS is Enabled or not ..
     * returns Boolean (True or False)
     *
     */
    private boolean isLocationEnabled() {

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

    }


    /**
     *
     * This Function Get's the LastKnownLocation.
     * Using different providers (internet,GPS,Passive)
     * returns the location object
     */
    private Location getLastKnownLocation(LocationManager locationManager) {

        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        if(ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Log.d("msg","YES");


            for (String provider : providers) {
                Log.d("msg","PROVIDER"+provider);
                locationManager.requestLocationUpdates(provider, 0, 0, this);
                Location l = locationManager.getLastKnownLocation(provider);
                Log.d("msg","location"+l);
                if (l == null) {
                    Log.d("msg","continuez");
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
        }else{
            Log.d("msg","NOT");
        }
        Log.d("msg","YES" + bestLocation);
        return bestLocation;
    }

    /**
     * The Following methods are implemented
     * when you implement LocationListener ..
     *
     */

    @Override
        public void onLocationChanged(Location location) {
            Log.d("data_AIS",""+location.getAltitude());
            Log.d("data_LLIS",""+location.getLongitude());
            Log.d("data_TIME",""+location.getTime());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }




}
