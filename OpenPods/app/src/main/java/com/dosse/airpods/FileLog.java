package com.dosse.airpods;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileLog {

    public static synchronized void log(Context context, String tag, String message) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

            File downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String LOG_FILE = "log_file.txt";

            try (BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(new File(downloadsDirectory, LOG_FILE), true))) {
                outputStream.write(String.format("%s %s\n", tag, message).getBytes());
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
