package com.example.demoapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoapp.R;
import com.example.demoapp.adapter.DuaAdapter;

import java.util.ArrayList;
import java.util.List;


public class DuaFragment extends Fragment {
    private View view;
    private DuaAdapter adapter;
    private int i=0;
    public DuaFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_dua, container, false);
        ViewPager viewPager=view.findViewById(R.id.viewPager);
        ImageView imageViewBack=view.findViewById(R.id.imageViewBack);
        ImageView imageViewForward=view.findViewById(R.id.imageViewForward);
        TextView textViewCount=view.findViewById(R.id.textViewCount);

        adapter=new DuaAdapter(getContext(),getDuas());
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
                textViewCount.setText(String.valueOf(viewPager.getCurrentItem()));
            }
        });

        imageViewForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
                textViewCount.setText(String.valueOf(viewPager.getCurrentItem()));
            }
        });
      /*  imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i>0){
                    i--;
                    textViewCount.setText(String.valueOf(i));
                    viewPager.setCurrentItem(i,true);
                }
            }
        });

        imageViewForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i<adapter.getCount()){
                    i++;
                    textViewCount.setText(String.valueOf(i));
                    viewPager.setCurrentItem(i,true);
                }
            }
        });*/
        return view;
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