[![Build Status](https://travis-ci.org/mplacona/selfie.svg?branch=master)](https://travis-ci.org/mplacona/selfie) [![Download](https://api.bintray.com/packages/mplacona/Selfie/selfie/images/download.svg)](https://bintray.com/mplacona/Selfie/selfie/_latestVersion) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Selfie-green.svg?style=plastic)](http://android-arsenal.com/details/1/4708)

selfie
===================
A simple library to make taking screenshots of your apps a breeze.

With the Marshmallow release and the [new permissions model](https://developer.android.com/training/permissions/requesting.html), taking screenshots of your applications has become just slightly more complicated as you need to deal with permissions on the run.

This library does it all for you regardless of the device you're running it on.

----------

Usage
-------------
Two easy steps:

1. From the activity you want to take screenshots or the application class initialise the `Selfie`.

2. Obtain `Selfie`'s instance and use it throughout your app.

Initialising Selfie
---------------
### Using default configuration: ###
```java
Selfie.initWithDefaults();
```
### Using builder: ###
```java
Selfie.initWithBuilder(new Selfie.Builder()
                .fileFormat("yyyy-MM-dd_hh:mm:ss")
                .path(Environment.getExternalStorageDirectory())
                .quality(100));
```
> - **fileFormat**: Any valid date format according to [this](https://developer.android.com/reference/java/text/SimpleDateFormat.html).
> - **path**: The place where you want to store your image in the device. Defaults to [Environment.getExternalStorageDirectory()](https://developer.android.com/reference/android/os/Environment.html#getExternalStorageDirectory())
> - **quality**: The quality you want your screenshot to be created. Must be an int between 0 and 100. See [Bitmap Compress](https://developer.android.com/reference/android/graphics/Bitmap.html#compress(android.graphics.Bitmap.CompressFormat, int, java.io.OutputStream))

Taking a screenshot
--------------
```java
Selfie.getInstance().snap(MainActivity.this);
```

Downloading
-----------
```groovy
compile 'uk.co.placona.selfie:selfie:1.0.0'
```
