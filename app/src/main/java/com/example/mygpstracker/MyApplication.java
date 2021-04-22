package com.example.mygpstracker;

import android.app.Application;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;

//the global class so that we can have global access of the list of locations
// from all the other classes and activity
public class MyApplication extends Application {
    private static MyApplication singleton; //only one instance allowed
    private List<Location> myLocations;

    public MyApplication getInstance(){
        return singleton;
    }
    //getter and setter
    public List<Location> getMyLocations() {
        return myLocations;
    }

    public void setMyLocations(List<Location> myLocations) {
        this.myLocations = myLocations;
    }

    public void onCreate()
    {
        super.onCreate();
        singleton=this;
        myLocations=new ArrayList<>(); //creating an empty arrayList
    }
}
