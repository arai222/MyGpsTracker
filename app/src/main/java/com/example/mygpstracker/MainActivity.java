package com.example.mygpstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int SET_INTERVAL = 10;
    public static final int FASTEST_INTERVAL = 5;
    public static final int REQUEST_CODE = 1;
    //textView initialization
    TextView wayPointsBox, longitudeVal, latitudeVal, altitudeVal, accuracyVal, speedVal, addressVal, updatesVal, sensorVal;
    Button map, noOfLocations, locations;
    SwitchCompat locationUpdates, GPSAndPower;

    LocationCallback locationCallBack;
    /*Location service API from Google for android which includes security recognition and geoFencing*/
    FusedLocationProviderClient fusedLocationProviderClient;

    /* initialising the class and its instance which is a config file named Location Request
     *which has a lots of properties which will influence the way fusedLocationProvider works*/
    LocationRequest locationRequest;

    // location  which will be updated every time a gps is updated
    Location currentLocation;
    //list of saved location
    List<Location> UpdatedLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //giving all the textView and button variable a value
        longitudeVal = findViewById(R.id.longitudeVal);
        latitudeVal = findViewById(R.id.latitudeVal);
        altitudeVal = findViewById(R.id.altitudeVal);
        accuracyVal = findViewById(R.id.accuracyVal);
        speedVal = findViewById(R.id.speedVal);
        updatesVal = findViewById(R.id.updatesVal);
        sensorVal = findViewById(R.id.sensorVal);
        addressVal = findViewById(R.id.addressVal);
        locationUpdates = findViewById(R.id.locationsupdates);
        GPSAndPower = findViewById(R.id.GPSAndPower);
        map = findViewById(R.id.map);

        locations = findViewById(R.id.locationsbtn);
        noOfLocations = findViewById(R.id.noOfLocation);
        wayPointsBox = findViewById(R.id.wayPointsBox);

        //setting all properties of location request
        //setting the intervals for the locationRequest
        locationRequest = LocationRequest.create();

        //setting interval for the location for the default location check
        locationRequest.setInterval(1000 * SET_INTERVAL);

        //setting fastest interval which tells us how often we want to update if we have maximum power and maximum accuracy
        locationRequest.setFastestInterval(1000 * FASTEST_INTERVAL);

        //setting the priority for location request
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //this method is called upon every time our intervals are satisfied
        locationCallBack = new LocationCallback(){
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location location=locationResult.getLastLocation();
                updatingTextViewValues(location);
            }
        };


        /*onClickListener for  the GPS AndPower
         * so that we can know what service is being used when we switch the "switch"*/
        GPSAndPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when the GPSAndPower is checked then we will be using GPS or
                // else cellphone towers or wifi
                if (GPSAndPower.isChecked()) {
                    //setting high accuracy for the GPS
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    sensorVal.setText(getString(R.string.UsingGps));
                } else {
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    sensorVal.setText(getString(R.string.cellPhoneWifi));
                }
            }
        });

        //onClickListener for the locationUpdates
        locationUpdates.setOnClickListener(v -> {
            //when the tracking is on
            if (locationUpdates.isChecked()) {
                startTracking();
            } else {
                stopTracking();
            }
        });

        //every time you switch on/off the gps , it will be updated
        locations.setOnClickListener(v -> {
            //getting the gps location
            MyApplication myApplication = (MyApplication) getApplicationContext();
            //adding to the global list
            UpdatedLocation = myApplication.getMyLocations();
            UpdatedLocation.add(currentLocation);
        });

        //the location will be added every time we have a current location
        noOfLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            //intent so that we can go on new page and see the list of locations
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoOfLocationAdded.class);
                startActivity(intent);
            }
        });

        //for the map
        map.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        });


        updateTheLocation();
    }//end of onCreate

    private void stopTracking() {
        updatesVal.setText(getString(R.string.OffTracker));
        longitudeVal.setText(getString(R.string.OffTracker));
        latitudeVal.setText(getString(R.string.OffTracker));
        altitudeVal.setText(getString(R.string.OffTracker));
        speedVal.setText(getString(R.string.OffTracker));
        sensorVal.setText(getString(R.string.OffTracker));
        accuracyVal.setText(getString(R.string.OffTracker));
        addressVal.setText(getString(R.string.OffTracker));
        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);
    }

    @SuppressLint("MissingPermission")
    private void startTracking() {
        updatesVal.setText(getString(R.string.OnTracker));
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateTheLocation();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {//if permission is granted then updating the location in updateTheLocation
            //else warning the users with a toast message
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateTheLocation();
            } else {
                Toast.makeText(this, "You need to have a permission to use this app.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void updateTheLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    //after getting permission, the location is updated in textViews
                    //MainActivity.this.updatingTextViewValues(location);
                    updatingTextViewValues(location);
                    currentLocation = location;
                }
            });
        } else {
            /*for the permission, we need to have a build version greater than 23 or higher
             * if we have the correct version of OS then we can get permission*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            }
        }
    }

    //updating all the textViews values with a new location
    private void updatingTextViewValues(Location location) {
        //since setText does not automatically convert integer into string, we have to parse them
        latitudeVal.setText(String.valueOf(location.getLatitude()));
        longitudeVal.setText(String.valueOf(location.getLongitude()));
        accuracyVal.setText(String.valueOf(location.getAccuracy()));

        //since some of the android device don't have a feature to get altitude and speed
        //we are using if statement
        //if they have, altitude and speed will be updated or else "not applicable" will be updated
        if (location.hasAltitude()) {
            altitudeVal.setText(String.valueOf(location.getAltitude()));
        } else {
            altitudeVal.setText(getString(R.string.apllicability));
        }

        //for speed
        if (location.hasSpeed()) {
            speedVal.setText(String.valueOf(location.getSpeed()));
        } else {
            speedVal.setText(getString(R.string.apllicability));
        }

        //geocode is a class provided by the google which will give us the most recent address
        //with the help of latitude and longitude
        Geocoder geocoder =new Geocoder(MainActivity.this);
        try{
            List<Address> addresses=geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            addressVal.setText(addresses.get(0).getAddressLine(0));
        }
        catch (Exception e)
        {
            addressVal.setText(getString(R.string.ERROR));
        }

        MyApplication myApplication = (MyApplication) getApplicationContext();
        UpdatedLocation = myApplication.getMyLocations();
        //showing the no of items in the list
        wayPointsBox.setText(Integer.toString(UpdatedLocation.size()));
    }

}