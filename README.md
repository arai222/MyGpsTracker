<img src="https://user-images.githubusercontent.com/83033326/115798715-e3fe7400-a3a4-11eb-8db0-8a3bb8be4c6a.jpg" width="450" height="450" />

# MYGPSTRACKER USER GUIDE


<br/>
## Table of contents
* [Introduction](#Introduction)
* [Description](#Description)
* [Features](#Features)
* [Getting started?](#Gettingstarted?)
* [How it works?](#Howitworks?)
* [Dependencies](#Dependencies)
* [Installation](#Installation)
* [App Overview](#AppOverview)
* [Figure Explanation](#FigureExplanation)
* [Troubleshoot](#Troubleshoot)
* [Reference](#Reference)
* [Contact](#Contact)
* [License](#License)
* [Releases](#Releases)
* [Packages](#Packages)
* [Language](#Language)



## Introduction
<br/>
### Description
MyGpsTracker is a simple android app written in JAVA in Android Studio that allows users to track their location in real time through GPS or cell-phone tower or WIFI. The app allows the users to see the latitude, longitude, and the address of their location along with the accuracy, speed, and altitude.
	
  
  
#### Features:
* The app will use FusedLocationProviderClient - a standard Android API - which will be used for reading the GPS and cell phone tower of the location.
* The user has the power of permission whether they want their location to be tracked or not.
* With the help of UpdateTheLocation switch, the user can either start or stop the tracking.
*	Geocode API- It is the process of converting the street addresses into the geographic coordinates like latitude and longitude.
* The user can track the number of places he/she has been to in a list through NoOfLocationsAdded button, and see them in a real time map through ShowMap.


<br/>

## Getting Started?
<br/>

### How it Works?

* The user can install the app on any platform as long as it satisfies the dependencies below.

<br/>

### Dependencies

* The user should have a latest version of Android Studio.
* The user should have a latest version of gradle in order to open this file in an android studio. 
* The android must be supported by Google Play Services.

<br/>

### Installation

* Download the source code of MyGpsTracker.
* Make sure to remember the location of the file.
* Open the android Studio.
* Once the android studio is open, select the Open an Existing Project and browse the location of the file where you have saved.
![android](https://user-images.githubusercontent.com/83033326/115798520-70f4fd80-a3a4-11eb-86be-28b0443a30d6.PNG)




<br/>
<br/>

### App Overview
<img src="https://user-images.githubusercontent.com/83033326/115798554-8b2edb80-a3a4-11eb-8b1c-5be0bb02aac7.jpg" width="350" height="600" />


Right after the app is installed in the device, the users have to allow permission to the app in order for the app to access the device location. If they allow the permission, then he/she will see Figure below or else the app will crash.
<br/>
<br/>

<img src="https://user-images.githubusercontent.com/83033326/115798595-9d107e80-a3a4-11eb-9fab-ce46f6ede036.jpg" width="350" height="600" />

As soon as the permission is provided, the user will see his/her current latitude, longitude, and address. Just to be sure, the switch UpdateTheLocation has to be on in order to track the location.
<br/>
<br/>

<img src="https://user-images.githubusercontent.com/83033326/115798613-a7327d00-a3a4-11eb-9c5c-061a58c554fa.jpg" width="350" height="600" />


If in case the user wants to turn off the tracking, he/she can directly switch off the UpdateTheLocation just like in Figure above. 
* Way Points: It is updated every time a location button is clicked.
* GPS/PowerSaver switch: The switch is responsible for the sensor value. It will show which sensor (GPS/Cell Phone tower/WIFI) is being used at the moment. 


<br/>
<br/>

* NoOfLocationsAdded: When we click on the NoofLocationAdded button, it gives user a list of the locations as shown in figure below.


<img src="https://user-images.githubusercontent.com/83033326/115798633-b0bbe500-a3a4-11eb-9dd3-80ba7bda0682.jpg" width="350" height="550" />
<br/>
<br/>



* ShowMap: When we click on ShowMap button, it shows user the real time location of the users in a map as shown in figure below.


<img src="https://user-images.githubusercontent.com/83033326/115798661-c5987880-a3a4-11eb-9b2b-380ca4bf178e.jpg" width="350" height="550" />


<br/>
<br/>

## TroubleShoot

The users must turn on the location of the device they are using or else the app will crash.



## Reference

1. Google Cloud Platform.
[Google Cloud Platform](https://console.cloud.google.com/flows/enableapi?apiid=maps_android_backend&keyType=CLIENT_SIDE_ANDROID&r=B8:FE:7A:BE:77:D6:45:E0:EF:00:E6:3A:25:0B:6C:27:2E:11:17:60;com.example.mygpstracker)

2. Change Location Setting.
[Change Location Setting](https://developer.android.com/training/location/change-location-settings)

3. Set up the Google Play Services for Fused Location Provider for Android.
[Fused Location Provider for Android](https://developers.google.com/android/guides/setup)

* **com.google.android.gms:play-services-location:18.0.0**



## Contact

Name- Ashma Rai
<br/>

Student Id- 1098283
<br/>

Arai2@lakeheadu.ca
<br/>



## License

This project is licensed under the Android Studio License and Google Play Services.


## Releases

No releases.


## Packages

No packages published.


## Language

Java


