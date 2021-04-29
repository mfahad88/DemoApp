package com.example.demoapp.service;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.example.demoapp.MainActivity;
import com.example.demoapp.R;
import com.example.demoapp.adapter.OverlayAdapter;
import com.example.demoapp.broadcast.BootReceiver;
import com.example.demoapp.helper.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    private static final String TAG = "MyService";
    WindowManager windowManager;
    View view;
    WindowManager.LayoutParams layoutParams;
    boolean screenON;
    ImageView imageViewClose,imageViewBack,imageViewForward,imageViewShare;
    ViewPager viewPager;
    OverlayAdapter adapter;
    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(KEYGUARD_SERVICE);
                if (intent.getAction().equals(Intent.ACTION_SCREEN_ON) && keyguardManager!=null && !keyguardManager.isKeyguardLocked()) {
                    screenON=true;
                    if(view.isShown()){
                        windowManager.updateViewLayout(view,layoutParams);
                    }else{
                        windowManager.addView(view,layoutParams);
                    }
                }
                Log.i(TAG, "onReceive: "+screenON);
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    };
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        windowManager=(WindowManager)getSystemService(WINDOW_SERVICE);

        view = inflater.inflate(R.layout.floating_activity,null);
        initViews();
        IntentFilter intentFilter=new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(broadcastReceiver,intentFilter);
        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE /*| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL*/,
                PixelFormat.TRANSLUCENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    PixelFormat.TRANSLUCENT);
        }else{
            layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    PixelFormat.TRANSLUCENT);
        }

        layoutParams.height= (int) Utils.dpToPx(getApplicationContext(),300);
        layoutParams.width= (int) Utils.dpToPx(getApplicationContext(),300);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowManager.removeView(view);
            }
        });
        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1,true);
            }
        });

        imageViewForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);

            }
        });
        imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowManager.removeViewImmediate(view);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }

    private void initViews() {
        imageViewClose=view.findViewById(R.id.imageViewClose);
        imageViewBack=view.findViewById(R.id.imageViewBack);
        imageViewForward=view.findViewById(R.id.imageViewForward);
        imageViewShare=view.findViewById(R.id.imageViewShare);
        viewPager=view.findViewById(R.id.viewPager);
        adapter=new OverlayAdapter(getApplicationContext(),getDuas());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sendBroadcast(new Intent("YouWillNeverKillMe"));
    }

    private List<String> getDuas(){
        List<String> list=new ArrayList<>();
        list.add("Lorem ipsum dolor sit amet، consectetur adipiscing elit. أكبر رعاية سريرية ومُحسّنة مبتكرة. الأطفال الإيكولوجيون ، وهي مركبات مطورة لمرة واحدة تخشى تلطيخ التربة الناعمة في رعاية سريرية مختلفة رائعة. يتم جلب Curabitur fermentum mauris ultricies felis حول البوابة. من أجل أن تكون ضحك. كل تخطيط سلطة تحتاج أي وقت مضى. ترحيل أكبر مرونة. قبل أي صلصة كرتون حية lorem nulla. Suspendisse erat elit ، إمبديت إي يو ماليسوادا إيه سي ، مولستي نيك من. من أجل الحصول على sem ullamcorper ، تمويل ولا حتى الآن ، بعد ذلك ماجنا. Proin egestas accumsan eros ، ولا sollicitudin lectus placerat ac. يتم إنتاج نام الحداد أو أعضاء من البروتين.");
        list.add("Lorem ipsum dolor sit amet، consectetur adipiscing elit. أكبر رعاية سريرية ومُحسّنة مبتكرة. الأطفال الإيكولوجيون ، وهي مركبات مطورة لمرة واحدة تخشى تلطيخ التربة الناعمة في رعاية سريرية مختلفة رائعة. يتم جلب Curabitur fermentum mauris ultricies felis حول البوابة. من أجل أن تكون ضحك. كل تخطيط سلطة تحتاج أي وقت مضى. ترحيل أكبر مرونة. قبل أي صلصة كرتون حية lorem nulla. Suspendisse erat elit ، إمبديت إي يو ماليسوادا إيه سي ، مولستي نيك من. من أجل الحصول على sem ullamcorper ، تمويل ولا حتى الآن ، بعد ذلك ماجنا. Proin egestas accumsan eros ، ولا sollicitudin lectus placerat ac. يتم إنتاج نام الحداد أو أعضاء من البروتين.");
        list.add("Lorem ipsum dolor sit amet، consectetur adipiscing elit. أكبر رعاية سريرية ومُحسّنة مبتكرة. الأطفال الإيكولوجيون ، وهي مركبات مطورة لمرة واحدة تخشى تلطيخ التربة الناعمة في رعاية سريرية مختلفة رائعة. يتم جلب Curabitur fermentum mauris ultricies felis حول البوابة. من أجل أن تكون ضحك. كل تخطيط سلطة تحتاج أي وقت مضى. ترحيل أكبر مرونة. قبل أي صلصة كرتون حية lorem nulla. Suspendisse erat elit ، إمبديت إي يو ماليسوادا إيه سي ، مولستي نيك من. من أجل الحصول على sem ullamcorper ، تمويل ولا حتى الآن ، بعد ذلك ماجنا. Proin egestas accumsan eros ، ولا sollicitudin lectus placerat ac. يتم إنتاج نام الحداد أو أعضاء من البروتين.");
        list.add("Lorem ipsum dolor sit amet، consectetur adipiscing elit. أكبر رعاية سريرية ومُحسّنة مبتكرة. الأطفال الإيكولوجيون ، وهي مركبات مطورة لمرة واحدة تخشى تلطيخ التربة الناعمة في رعاية سريرية مختلفة رائعة. يتم جلب Curabitur fermentum mauris ultricies felis حول البوابة. من أجل أن تكون ضحك. كل تخطيط سلطة تحتاج أي وقت مضى. ترحيل أكبر مرونة. قبل أي صلصة كرتون حية lorem nulla. Suspendisse erat elit ، إمبديت إي يو ماليسوادا إيه سي ، مولستي نيك من. من أجل الحصول على sem ullamcorper ، تمويل ولا حتى الآن ، بعد ذلك ماجنا. Proin egestas accumsan eros ، ولا sollicitudin lectus placerat ac. يتم إنتاج نام الحداد أو أعضاء من البروتين.");
        list.add("Lorem ipsum dolor sit amet، consectetur adipiscing elit. أكبر رعاية سريرية ومُحسّنة مبتكرة. الأطفال الإيكولوجيون ، وهي مركبات مطورة لمرة واحدة تخشى تلطيخ التربة الناعمة في رعاية سريرية مختلفة رائعة. يتم جلب Curabitur fermentum mauris ultricies felis حول البوابة. من أجل أن تكون ضحك. كل تخطيط سلطة تحتاج أي وقت مضى. ترحيل أكبر مرونة. قبل أي صلصة كرتون حية lorem nulla. Suspendisse erat elit ، إمبديت إي يو ماليسوادا إيه سي ، مولستي نيك من. من أجل الحصول على sem ullamcorper ، تمويل ولا حتى الآن ، بعد ذلك ماجنا. Proin egestas accumsan eros ، ولا sollicitudin lectus placerat ac. يتم إنتاج نام الحداد أو أعضاء من البروتين.");
        list.add("Lorem ipsum dolor sit amet، consectetur adipiscing elit. أكبر رعاية سريرية ومُحسّنة مبتكرة. الأطفال الإيكولوجيون ، وهي مركبات مطورة لمرة واحدة تخشى تلطيخ التربة الناعمة في رعاية سريرية مختلفة رائعة. يتم جلب Curabitur fermentum mauris ultricies felis حول البوابة. من أجل أن تكون ضحك. كل تخطيط سلطة تحتاج أي وقت مضى. ترحيل أكبر مرونة. قبل أي صلصة كرتون حية lorem nulla. Suspendisse erat elit ، إمبديت إي يو ماليسوادا إيه سي ، مولستي نيك من. من أجل الحصول على sem ullamcorper ، تمويل ولا حتى الآن ، بعد ذلك ماجنا. Proin egestas accumsan eros ، ولا sollicitudin lectus placerat ac. يتم إنتاج نام الحداد أو أعضاء من البروتين.");
        list.add("Lorem ipsum dolor sit amet، consectetur adipiscing elit. أكبر رعاية سريرية ومُحسّنة مبتكرة. الأطفال الإيكولوجيون ، وهي مركبات مطورة لمرة واحدة تخشى تلطيخ التربة الناعمة في رعاية سريرية مختلفة رائعة. يتم جلب Curabitur fermentum mauris ultricies felis حول البوابة. من أجل أن تكون ضحك. كل تخطيط سلطة تحتاج أي وقت مضى. ترحيل أكبر مرونة. قبل أي صلصة كرتون حية lorem nulla. Suspendisse erat elit ، إمبديت إي يو ماليسوادا إيه سي ، مولستي نيك من. من أجل الحصول على sem ullamcorper ، تمويل ولا حتى الآن ، بعد ذلك ماجنا. Proin egestas accumsan eros ، ولا sollicitudin lectus placerat ac. يتم إنتاج نام الحداد أو أعضاء من البروتين.");
        list.add("Lorem ipsum dolor sit amet، consectetur adipiscing elit. أكبر رعاية سريرية ومُحسّنة مبتكرة. الأطفال الإيكولوجيون ، وهي مركبات مطورة لمرة واحدة تخشى تلطيخ التربة الناعمة في رعاية سريرية مختلفة رائعة. يتم جلب Curabitur fermentum mauris ultricies felis حول البوابة. من أجل أن تكون ضحك. كل تخطيط سلطة تحتاج أي وقت مضى. ترحيل أكبر مرونة. قبل أي صلصة كرتون حية lorem nulla. Suspendisse erat elit ، إمبديت إي يو ماليسوادا إيه سي ، مولستي نيك من. من أجل الحصول على sem ullamcorper ، تمويل ولا حتى الآن ، بعد ذلك ماجنا. Proin egestas accumsan eros ، ولا sollicitudin lectus placerat ac. يتم إنتاج نام الحداد أو أعضاء من البروتين.");

        return list;
    }
}