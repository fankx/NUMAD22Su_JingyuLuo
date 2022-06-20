package edu.neu.madcourse.numad22su_jingyuluo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class LocatorPage extends AppCompatActivity {

    TextView tv_latitude;
    TextView tv_longitude;
    TextView tv_distance;
    Button btn_reset;
    Button btn_changePrecision;
    private LocationManager locationManager;
    private String locationProvider = null;

    private double[] startLocation;
    private double[] endLocation;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator_page);

        tv_longitude = findViewById(R.id.tv_longitude);
        tv_latitude = findViewById(R.id.tv_latitude);
        tv_distance = findViewById(R.id.tv_distance);
        btn_reset = findViewById(R.id.btn_distanceReset);
        btn_changePrecision = findViewById(R.id.btn_precision);
        startLocation = new double[2];
        endLocation = new double[2];

        btn_changePrecision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            //request
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }else{
            //location manager
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //provider
            List<String> providers = locationManager.getProviders(true);
            if (providers.contains(LocationManager.GPS_PROVIDER)) {
                locationProvider = LocationManager.GPS_PROVIDER;
            } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                locationProvider = LocationManager.NETWORK_PROVIDER;
            } else {
                Toast.makeText(this, "Can't find the location", Toast.LENGTH_SHORT).show();
                return;
            }
            //get location
            locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
        }
    };

    //callback after request permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //location manager
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    //provider
                    List<String> providers = locationManager.getProviders(true);
                    if (providers.contains(LocationManager.GPS_PROVIDER)) {
                        locationProvider = LocationManager.GPS_PROVIDER;
                    } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                        locationProvider = LocationManager.NETWORK_PROVIDER;
                    } else {
                        Toast.makeText(this, "Can't find the location", Toast.LENGTH_SHORT).show();
                    }

                    //get location
                    try {
                        locationManager.requestLocationUpdates(locationProvider, 1000, 0, locationListener);

                    } catch (SecurityException e) {
                    }
                }
                break;
            }
        }
    };

    public LocationListener locationListener = new LocationListener() {
        //status change
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        // enable
        @Override
        public void onProviderEnabled(String provider) {
        }
        // disable
        @Override
        public void onProviderDisabled(String provider) {
        }
        //location change
        @Override
        public void onLocationChanged(Location location) {
            if (!flag) {
                startLocation[0] = location.getLatitude();
                startLocation[1] = location.getLongitude();
                flag = true;
            }
            if (location != null) {
                endLocation[0] = location.getLatitude();
                endLocation[1] = location.getLongitude();

                Location loc1 = new Location("");
                loc1.setLatitude(startLocation[0]);
                loc1.setLongitude(startLocation[1]);

                Location loc2 = new Location("");
                loc2.setLatitude(endLocation[0]);
                loc2.setLongitude(endLocation[1]);

                float distanceInMeters = loc1.distanceTo(loc2);
//                Location.distanceBetween(startLocation[0], startLocation[1], endLocation[0], endLocation[1], distanceResult);
                tv_longitude.setText(String.valueOf("Longitude: " + location.getLongitude()));
                tv_latitude.setText(String.valueOf("Latitude: " + location.getLatitude()));
                tv_distance.setText(String.valueOf("total distance traveled: " + distanceInMeters + " meters"));
            }
        }
    };
}