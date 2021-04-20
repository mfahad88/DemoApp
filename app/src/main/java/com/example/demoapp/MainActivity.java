package com.example.demoapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    /*DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    private AdView mAdView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*drawer=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle.isDrawerIndicatorEnabled()) {
                    drawer.openDrawer(GravityCompat.START);
                } else {
                    onBackPressed();
                }

            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/
    }
}