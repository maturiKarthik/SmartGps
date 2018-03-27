# SmartGps
An Simple Application to Get the GPS LAT and LONG

NOTE:
------
PLS, SEE THE MANIFEST FOR THE PERMISSION

CODE FEATURE:
-------------

1.) Runtime Permission check
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
2.) Runtime Permission Request ..
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
        
3.) GPS CLASS ESASY TO inherit :
     a.) Class Calling: 
      Gps gps = new Gps(getApplicationContext(),locationManager);
                textView.setText(gps.getLatLong());
                
      b.) Retrun type of gps.getLatLong() can changed to array or object etc..
      
      
KnownIssues:
-------------
java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String[] android.content.pm.PackageManager.getPackagesForUid(int)' on a null object reference
    at android.os.Parcel.readException(Parcel.java:1605)
    at android.os.Parcel.readException(Parcel.java:1552)
    at android.location.ILocationManager$Stub$Proxy.getLastLocation(ILocationManager.java:717)
    at android.location.LocationManager.getLastKnownLocation(LocationManager.java:1200)
    at [...]
 
 Solution :
 -----------
If your are using LocationManager.GPS_PROVIDER there are chnaces of getting the null pointer exception:
Therefore to overcome this issue . i have written a function to which get the location object by using different providres
