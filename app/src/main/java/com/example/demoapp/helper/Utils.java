package com.example.demoapp.helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

public class Utils {

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static float pixelTodp(Context c, float pixel) {
        float density = c.getResources().getDisplayMetrics().density;
        float dp = pixel / density;
        return dp;
    }

    public static int dpToPx(Context context, int dp)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }
    public static void findRealSize(Activity activity)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= 17)
        {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        else
        {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }

        int realWidth = displayMetrics.widthPixels;
        int realHeight = displayMetrics.heightPixels;

        Log.i("LOG_TAG", "realWidth: " + realWidth + " realHeight: " + realHeight);
    }
}
