package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.fragment.DuaFragment;
import com.example.demoapp.helper.DBHelper;
import com.example.demoapp.service.MyService;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private TextView txt_since;
    DBHelper dbHelper;
    LocalBroadcastManager localBroadcastManager;
    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getBooleanExtra("isUpdated",false)){
                fetchData();
            }
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        localBroadcastManager.registerReceiver(broadcastReceiver,new IntentFilter("event"));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = findViewById(R.id.adView);
        txt_since = findViewById(R.id.txt_since);
        dbHelper=new DBHelper(getApplicationContext());
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        fetchData();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.canDrawOverlays(MainActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1234);
            }else{

                Intent intent = new Intent(getApplicationContext(), MyService.class);
                startService(intent);
            }
        } else {
            Intent intent = new Intent(getApplicationContext(), MyService.class);

            startService(intent);
        }

    }

    public void fetchData() {
        Toast.makeText(this, ""+dbHelper.numberOfRows(), Toast.LENGTH_SHORT).show();
        txt_since.setText(String.valueOf(dbHelper.numberOfRows()));
    }

    @Override
    protected void onDestroy() {
//        unregisterReceiver(broadcastReceiver);
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }



}