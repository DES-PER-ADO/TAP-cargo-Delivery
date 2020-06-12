package com.example.tokoin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

//        getSupportActionBar().setTitle("Tokoin Map");

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
//        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(120000); // two minute interval
        mLocationRequest.setFastestInterval(120000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            mGoogleMap.setMyLocationEnabled(true);
        }

        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                if(arg0 != null && arg0.getTitle().equals(arg0.getTitle().toString()));
                Toast.makeText(MapsActivity.this, arg0.getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MapsActivity.this, TokoinViewActivity.class);
                startActivity(intent);
                return true;
            }
        });

    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                //The last location in the list is the newest
                Location location = locationList.get(locationList.size() - 1);
                Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }

//                //Place current location marker
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                MarkerOptions markerOptions = new MarkerOptions();
//                markerOptions.position(latLng);
//                markerOptions.title("Current Position");
//                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//                mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

                //move map camera
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));

                int height = 50;
                int width = 50;
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.tokoinicon_round);
                Bitmap b=bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                ArrayList<LatLng> latlngs = new ArrayList<>();

//                latlngs.add(new LatLng( 38.898756, -77.046506));//1
//                latlngs.add(new LatLng(38.898881, -77.046678));
//                latlngs.add(new LatLng(38.899023, -77.046652));
//                latlngs.add(new LatLng(38.899160, -77.046645));
//                latlngs.add(new LatLng(38.899450, -77.046648));
//                latlngs.add(new LatLng(38.899578, -77.046718));//5
//                latlngs.add(new LatLng(38.899586, -77.047003));
//                latlngs.add(new LatLng(38.899592, -77.047299));
//                latlngs.add(new LatLng(38.899549, -77.047525));
//                latlngs.add(new LatLng(38.899560, -77.047784));
//
//                latlngs.add(new LatLng(38.899557, -77.048052));
//                latlngs.add(new LatLng(38.899546, -77.048178));
//                latlngs.add(new LatLng(38.899554, -77.048481));
//                latlngs.add(new LatLng(38.899563, -77.048692));
//                latlngs.add(new LatLng(38.899555, -77.048801));
//                latlngs.add(new LatLng(38.899619, -77.048876));
//                latlngs.add(new LatLng(38.899775, -77.048862));
//                latlngs.add(new LatLng(38.899908, -77.048867));
//                latlngs.add(new LatLng(38.900019, -77.049012));


                latlngs.add(new LatLng( 38.905933, -77.070907));//1
                latlngs.add(new LatLng(38.905848, -77.069266));
                latlngs.add(new LatLng(38.905735, -77.067821));
                latlngs.add(new LatLng(38.905410, -77.067814));
                latlngs.add(new LatLng(38.905112, -77.067288));
                latlngs.add(new LatLng(38.905128, -77.066187));//5
                latlngs.add(new LatLng(38.905128, -77.065230));
//                latlngs.add(new LatLng(38.899592, -77.047299));
//                latlngs.add(new LatLng(38.899549, -77.047525));
                latlngs.add(new LatLng(38.905160, -77.064580));

                latlngs.add(new LatLng(38.905192, -77.061773));
                latlngs.add(new LatLng(38.905144, -77.059677));
                latlngs.add(new LatLng(38.905144, -77.057467));
                latlngs.add(new LatLng(38.904811, -77.056550));
                latlngs.add(new LatLng(38.904381, -77.055646));
                latlngs.add(new LatLng(38.903806, -77.053617));
                latlngs.add(new LatLng(38.902986, -77.051651));
                latlngs.add(new LatLng(38.902088, -77.050382));
                latlngs.add(new LatLng(38.900801, -77.050007));
                latlngs.add(new LatLng(38.899815, -77.050041));
                latlngs.add(new LatLng(38.899581, -77.049144));
                latlngs.add(new LatLng(38.899549, -77.048025));

                for (LatLng point : latlngs) {
                    mGoogleMap.addMarker(new MarkerOptions()
                            .position(point)
                            .title("Tokoin")
                            .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                    );
                }

            }
        }
    };

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapsActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
