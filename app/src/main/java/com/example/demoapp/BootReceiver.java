package com.example.demoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "BootReceiver";
    boolean screenOff;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                screenOff = true;
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                screenOff = false;
            }
            Log.i(TAG, "onReceive: "+screenOff);
            Toast.makeText(context, ""+screenOff, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}