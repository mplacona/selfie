/*
 * Copyright (C) 2016 mplacona.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.placona.selfie;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Class to simplify taking screenshots of your apps
 */
public final class Selfie {
   // Default values
   private static final String DEFAULT_FILE_FORMAT = "yyyy-MM-dd_hh:mm:ss";
   private static final File DEFAULT_PATH = Environment.getExternalStorageDirectory();
   private static final int DEFAULT_QUALITY = 100;

   // Storage Permissions variables
   private static final int REQUEST_WRITE_STORAGE = 112;

   // Singleton instance
   private static volatile Selfie singleton = null;

   // Configuration
   @VisibleForTesting final String fileFormat;
   @VisibleForTesting final File path;
   @VisibleForTesting final int quality;

   private Selfie() {
      this(DEFAULT_FILE_FORMAT, DEFAULT_PATH, DEFAULT_QUALITY);
   }

   private Selfie(String fileFormat, File path, int quality) {
      this.fileFormat = fileFormat;
      this.path = path;
      this.quality = quality;
   }

   /**
    * Initialize Selfie with default configuration:
    * <ul>
    * <li><b>File fileFormat:</b> yyyy-MM-dd_hh:mm:ss</li>
    * <li><b>Path:</b> {@link Environment#getExternalStorageDirectory()}</li>
    * <li><b>Quality:</b> 100</li>
    * </ul>
    */
   public static void initWithDefaults() {
      init(new Selfie());
   }

   /**
    * Initialize Selfie with specified Builder.
    *
    * @param builder Desired Selfie configuration.
    */
   public static void initWithBuilder(@NonNull Builder builder) {
      if (builder == null) {
         throw new IllegalArgumentException("Builder must not be null.");
      }

      init(builder.build());
   }

   private static void init(@NonNull Selfie selfie) {
      if (selfie == null) {
         throw new IllegalArgumentException("Selfie must not be null.");
      }

      synchronized (Selfie.class) {
         if (singleton != null) {
            throw new IllegalStateException("Singleton instance already exists.");
         }

         singleton = selfie;
      }
   }

   /**
    * @return the singleton instance of the Selfie class.
    */
   public static Selfie getInstance() {
      synchronized (Selfie.class) {
         if (singleton == null) {
            throw new IllegalStateException("Selfie not initialized.");
         }

         return singleton;
      }
   }

   /**
    * Check for permissions and create a snapshot
    *
    * @param activity Activity used by Selfie.
    * @return {@code true} if the screenshot was taken, false otherwise.
    */
   public boolean snap(Activity activity) {
      boolean hasPermission = (ContextCompat.checkSelfPermission(activity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
      if (!hasPermission) {
         ActivityCompat.requestPermissions(activity,
               new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
               REQUEST_WRITE_STORAGE);
         return false;
      } else {
         return takeScreenShot(activity);
      }
   }

   /**
    * This method is responsible for taking the screenshot and creating a file
    *
    * @param activity Activity used by Selfie.
    * @return {@code true} if the screenshot was taken, false otherwise.
    */
   private boolean takeScreenShot(Activity activity) {
      Date now = new Date();
      android.text.format.DateFormat.format(fileFormat, now);

      // create bitmap screen capture
      View v1 = activity.getWindow().getDecorView().getRootView();
      v1.setDrawingCacheEnabled(true);
      Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
      v1.setDrawingCacheEnabled(false);

      // image naming and path to include sd card appending name you choose for file
      File imageFile = new File(path, now + ".jpg");

      try {
         FileOutputStream outputStream = new FileOutputStream(imageFile);
         bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
         outputStream.flush();
         outputStream.close();
      } catch (IOException ex) {
         return false;
      }

      return true;
   }

   /**
    * Builds the class with all the optional parameters
    */
   public static class Builder {
      private String format = DEFAULT_FILE_FORMAT;
      private File path = DEFAULT_PATH;
      private int quality = DEFAULT_QUALITY;

      /**
       * @param fileFormat The date fileFormat which the file will be created with
       * @return Builder
       */
      public Builder fileFormat(@NonNull String fileFormat) {
         if (fileFormat == null)
            throw new IllegalArgumentException("fileFormat == null");

         this.format = fileFormat;
         return this;
      }

      /**
       * Path to which we will save the screenshots
       *
       * @param path The path where to save the file
       * @return Builder
       */
      public Builder path(@NonNull File path) {
         if (path == null)
            throw new IllegalArgumentException("path == null");

         this.path = path;
         return this;
      }

      /**
       * Quality which we should generate the image 1-100
       *
       * @param quality The quality (between 1-100) to save the file
       * @return Builder
       */
      public Builder quality(int quality) {
         if (quality < 1 || quality > 100)
            throw new IllegalArgumentException("quality must be between 1 and 100");

         this.quality = quality;
         return this;
      }

      /**
       * Build a Selfie with all the parameters
       */
      Selfie build() {
         return new Selfie(format, path, quality);
      }
   }
}
