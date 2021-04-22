package com.example.mygpstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoOfLocationAdded extends AppCompatActivity {

    ListView lv_noOfLocations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_of_location_added);
        lv_noOfLocations=findViewById(R.id.lv_noOfLocation);


        //we must have  a global access to the MyApplication
        MyApplication myApplication=(MyApplication)getApplicationContext();
        List<Location> UpdatedLocation =myApplication.getMyLocations();

//connecting the list of our updated locations to the list view
        lv_noOfLocations.setAdapter(new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, UpdatedLocation));
    }
}