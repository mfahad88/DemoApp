package com.example.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.demoapp.MainActivity;
import com.example.demoapp.R;
import com.example.demoapp.helper.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class DuaAdapter extends PagerAdapter {
    Context context;
    List<String> list;
    LayoutInflater mLayoutInflater;
    DBHelper dbHelper;
    public DuaAdapter(Context context, List<String> list) {
        this.context = context;
        this.list=list;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dbHelper=new DBHelper(context);
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ScrollView) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=mLayoutInflater.inflate(R.layout.list_dua,container,false);
        TextView textView=(TextView)view.findViewById(R.id.textView);
        textView.setText(list.get(position));
        Objects.requireNonNull(container).addView(view);

        dbHelper.insertContact();

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((ScrollView) object);
    }
}
