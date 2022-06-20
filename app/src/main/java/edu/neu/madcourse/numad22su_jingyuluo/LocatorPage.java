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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LocatorPage extends AppCompatActivity implements LocationListener {

    TextView tv_latitude;
    TextView tv_longitude;
    LocationManager locationManager;
    private String locationProvider = null;
    LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator_page);
        tv_latitude = findViewById(R.id.tv_latitude);
        tv_longitude = findViewById(R.id.tv_longitude);
        if (ContextCompat.checkSelfPermission(LocatorPage.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LocatorPage.this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 1);
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            locationListener = new LocationListener() {
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
                    if (location != null) {
                        tv_longitude.setText(String.valueOf(location.getLongitude()));
                        //new Latitude
                        tv_latitude.setText(String.valueOf(location.getLatitude()));
                    }
                }
            };
            getLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
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
                    Toast.makeText(this, "No Available Locator", Toast.LENGTH_SHORT).show();
                }
                //get location
                try {
                    locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);

                } catch (SecurityException ignored) {
                }
            }
        }
    };

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (
                ActivityCompat.checkSelfPermission(LocatorPage.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(LocatorPage.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LocatorPage.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        } else {
            //location manager
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //provider
            List<String> providers = locationManager.getProviders(true);
            if (providers.contains(LocationManager.GPS_PROVIDER)) {
                locationProvider = LocationManager.GPS_PROVIDER;
            } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                locationProvider = LocationManager.NETWORK_PROVIDER;
            } else {
                Toast.makeText(this, "No Available Locator", Toast.LENGTH_SHORT).show();
                return;
            }
            //get location
            locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        tv_longitude.setText(String.valueOf(location.getLongitude()));
        //new Latitude
        tv_latitude.setText(String.valueOf(location.getLatitude()));
    }
}
