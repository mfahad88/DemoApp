package com.example.demoapp.service;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.demoapp.MainActivity;
import com.example.demoapp.R;
import com.example.demoapp.adapter.DuaAdapter;
import com.example.demoapp.adapter.OverlayAdapter;
import com.example.demoapp.broadcast.BootReceiver;
import com.example.demoapp.fragment.DuaFragment;
import com.example.demoapp.helper.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

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
    PagerAdapter adapter;
    private LinearLayout linearDua,linearAzkar,linearNamaz,linearIstikhara;
    Context context;
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
                Log.e(TAG, "onReceive: "+screenON);
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    };


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


        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
            Intent notificationIntent = new Intent(this,MainActivity.class);
            PendingIntent conPendingIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText("")
                    .setContentIntent(conPendingIntent)
                    .build();

            startForeground(1, notification);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    PixelFormat.TRANSPARENT);
        }else{
            layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    PixelFormat.TRANSPARENT);
        }

//        layoutParams.height= (int) Utils.dpToPx(getApplicationContext(),300);
//        layoutParams.width= (int) Utils.dpToPx(getApplicationContext(),300);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowManager.removeView(view);
                Intent filter=new Intent("event");
                filter.putExtra("isUpdated",true);
                Log.e(TAG, "onClick: "+filter.getBooleanExtra("isUpdated",false) );
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(filter);
            }
        });

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

            linearDua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "linearDua", Toast.LENGTH_SHORT).show();
                    if(adapter!=null) {
                        viewPager.setAdapter(null);
                    }

                    adapter=new DuaAdapter(getApplicationContext(),getDuas());
                    viewPager.setAdapter(adapter);
                }
            });

            linearAzkar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "linearAzkar", Toast.LENGTH_SHORT).show();
                    if(adapter!=null) {
                        viewPager.setAdapter(null);
                    }

                    adapter=new DuaAdapter(getApplicationContext(),getDuas());
                    viewPager.setAdapter(adapter);
                }
            });

            linearNamaz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "linearNamaz", Toast.LENGTH_SHORT).show();
                    if(adapter!=null) {
                        viewPager.setAdapter(null);
                    }

                    adapter=new DuaAdapter(getApplicationContext(),getDuas());
                    viewPager.setAdapter(adapter);
                }
            });

            linearIstikhara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "linearIstikhara", Toast.LENGTH_SHORT).show();
                    if(adapter!=null) {
                        viewPager.setAdapter(null);
                    }

                    adapter=new DuaAdapter(getApplicationContext(),getDuas());
                    viewPager.setAdapter(adapter);
                }
            });

        final Display display = windowManager.getDefaultDisplay();
        Point outPoint = new Point();
        if (Build.VERSION.SDK_INT >= 19) {
            // include navigation bar
            display.getRealSize(outPoint);
        } else {
            // exclude navigation bar
            display.getSize(outPoint);
        }

        Log.e("Real Size",outPoint.x+"\n"+outPoint.y);
        layoutParams.width= (int) (outPoint.x*0.8);
        linearDua.performClick();
//        layoutParams.height= (int) (outPoint.y*0.9);
    }

    private void initViews() {
        imageViewClose=view.findViewById(R.id.imageViewClose);
        imageViewBack=view.findViewById(R.id.imageViewBack);
        imageViewForward=view.findViewById(R.id.imageViewForward);
        imageViewShare=view.findViewById(R.id.imageViewShare);
        viewPager=view.findViewById(R.id.viewPager);
        imageViewClose=view.findViewById(R.id.imageViewClose);
        linearDua=view.findViewById(R.id.linearDua);
        linearAzkar=view.findViewById(R.id.linearAzkar);
        linearNamaz=view.findViewById(R.id.linearNamaz);
        linearIstikhara=view.findViewById(R.id.linearIstikhara);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
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