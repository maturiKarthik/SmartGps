package com.example.iosdev.smartgps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView textView;
    Button click;

    static  final int MY_PERMISSIONS_REQUEST_READ_CONTACTS =123;
    Gps gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.list_item);
        click = (Button)findViewById(R.id.click);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                gps = new Gps(getApplicationContext(),locationManager);
                textView.setText(gps.getLatLong());

            }
        });


        /**
         * Checking for Permission at Runtime
         * Even after putting the permission in manifest
          */

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {

            Log.d("data","THE PERMISSION IS NOT GRANTED");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                Log.d("data","THE PERMISSION IS NOT ASYN GRANTED");

            } else {

                // No explanation needed; request the permission
                Log.d("data","THE PERMISSION IS NOT REQUESTED PERMISIION ");
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            }
        } else {
            // Permission has already been granted
            Log.d("data","THE PERMISSION IS NOT REQUESTED PERMISIION ACESS_FINE_LOCATION ");

        }

    }


    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     *
     * Requesting the permission to be granted
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("data","THE PERMISSION NOW GRANTED ");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Log.d("data","THE PERMISSION DENIED ");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


}
