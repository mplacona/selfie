[ ![Download](https://api.bintray.com/packages/mplacona/Selfie/selfie/images/download.svg) ](https://bintray.com/mplacona/Selfie/selfie/_latestVersion)

selfie
===================
A simple library to make taking screenshots of your apps a breeze.

With the Marshmallow release and the [new permissions model](https://developer.android.com/training/permissions/requesting.html), taking screenshots of your applications has become just slightly more complicated as you need to deal with permissions on the run.

This library does it all for you regardless of the device you're running it on.

----------

Usage
-------------
Two easy steps:

1. From the activity you want to take screenshots or the application class initialise the`Selfie`.

2. Call `Selfie`'s static methods throughout your app.

Initialising Selfie
---------------
```java
new Selfie.Builder()
                .format("yyyy-MM-dd_hh:mm:ss")
                .path(Environment.getExternalStorageDirectory().toString())
                .quality(100)
                .build();
```
> - **format**: Any valid date format according to [this](https://developer.android.com/reference/java/text/SimpleDateFormat.html).
> - **path**: The pace where you want to store your image in the device. Defaults to [Environment.getExternalStorageDirectory()](https://developer.android.com/reference/android/os/Environment.html#getExternalStorageDirectory())
> - **quality**: The quality you want your screenshot to be created. Must be an int between 0 and 100. See [Bitmap Compress](https://developer.android.com/reference/android/graphics/Bitmap.html#compress(android.graphics.Bitmap.CompressFormat, int, java.io.OutputStream))

Taking a screenshot
--------------
```java
Selfie.snap(MainActivity.this);
```

Downloading
-----------
```groovy
compile 'uk.co.placona.selfie:selfie:0.0.1'
```
