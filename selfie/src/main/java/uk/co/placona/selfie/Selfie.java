/**
 * Copyright (C) 2016 mplacona.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


/**
 * Class to simplify the taking screenshots of your apps
 */
public final class Selfie {

    // Storage Permissions variables
    private static final int REQUEST_WRITE_STORAGE = 112;

    private static final String TAG = "Selfie";
    private static SelfieInstance mInstance;

    public static void initialize(){
        if (mInstance == null) {
            mInstance = new SelfieInstance
                    .SelfieBuilder()
                    .format("yyyy-MM-dd_hh:mm:ss")
                    .path(Environment.getExternalStorageDirectory().toString())
                    .quality(100)
                    .build();
        }
    }

    /**
     * Create a snapshot
     *
     * @param activity Activity used by Selfie.
     */
    public static void snap(Activity activity) {

        boolean hasPermission = (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }else {
            takeScreenShot(activity);
        }
    }

    private static void takeScreenShot(Activity activity) {
        Date now = new Date();
        android.text.format.DateFormat.format(mInstance.getFormat(), now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = mInstance.getPath() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = activity.getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = mInstance.getQuality();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            Log.d(TAG, "snap: " + mPath);


        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }
}
