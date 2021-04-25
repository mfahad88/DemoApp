package com.example.demoapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.demoapp.dua.DuaFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BroadcastReceiver broadcastReceiver;
    private AdView mAdView;
    private LinearLayout linearDua,linearAzkar,linearNamaz,linearIstikhara;
    private ImageView imageViewClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent svc = new Intent(this, MyService.class);
        startService(svc);
        imageViewClose=findViewById(R.id.imageViewClose);
        linearDua=findViewById(R.id.linearDua);
        linearAzkar=findViewById(R.id.linearAzkar);
        linearNamaz=findViewById(R.id.linearNamaz);
        linearIstikhara=findViewById(R.id.linearIstikhara);

        imageViewClose.setOnClickListener(this);
        linearDua.setOnClickListener(this);
        linearAzkar.setOnClickListener(this);
        linearNamaz.setOnClickListener(this);
        linearIstikhara.setOnClickListener(this);
        linearDua.performClick();
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(this)){
                // ask for setting
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
            }
        }*/
    }

    @Override
    protected void onDestroy() {
//        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.linearDua){

            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new DuaFragment()).commit();
        }else if(view.getId()==R.id.linearAzkar){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new DuaFragment()).commit();
        }else if(view.getId()==R.id.linearNamaz){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new DuaFragment()).commit();
        }else if(view.getId()==R.id.linearIstikhara){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new DuaFragment()).commit();
        }else if(view.getId()==R.id.imageViewClose){
            finishAffinity();
        }
    }
}