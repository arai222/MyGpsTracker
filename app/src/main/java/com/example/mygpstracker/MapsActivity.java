package com.example.mygpstracker;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    List<Location> UpdatedLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        MyApplication myApplication=(MyApplication)getApplicationContext();
        UpdatedLocations=myApplication.getMyLocations();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        LatLng lastLocationPlaced=sydney;

        //updating a location in a map
        //foreach
        for(Location location: UpdatedLocations)
        {
            LatLng latLng=new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions=new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("lat:"+location.getLatitude()+" long:"+location.getLongitude());
            googleMap.addMarker(markerOptions);
            lastLocationPlaced=latLng;

        }

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocationPlaced, 12.0f));
        googleMap.setOnMarkerClickListener(marker -> {
            //lets count the number of times the pin is clicked
            Integer clicks= (Integer) marker.getTag();
            if(clicks==null)
            {
                clicks=0;
            }
            clicks++;
            marker.setTag(clicks);
            Toast.makeText(MapsActivity.this, "Marker"+marker.getTitle()+"was clicked"+marker.getTag()+"Times.",Toast.LENGTH_SHORT).show();
            return false;
        });

    }
}