package com.example.demoapp.dua;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoapp.R;

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
        list.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vel maximus tortor. Nulla sapien leo, blandit finibus purus vel, fermentum laoreet massa. Vivamus ut odio vel enim maximus lobortis. Pellentesque eros libero, ullamcorper eget nibh eget, fringilla rutrum eros. Fusce id pulvinar lorem. Donec mollis eu velit ac posuere. Pellentesque vehicula ut ligula vitae semper. Maecenas interdum convallis ipsum, nec dictum enim interdum tristique. Suspendisse quis suscipit lacus.\n" +
                "\n" +
                "In eu elit sit amet nisl commodo posuere. Sed consequat interdum tempor. Curabitur lectus tortor, cursus id tellus vitae, euismod pellentesque lorem. Maecenas scelerisque, quam sagittis sodales eleifend, dolor purus pulvinar augue, eu interdum nisl velit eget nunc. Curabitur maximus, massa et rhoncus vehicula, enim metus varius ex, nec porttitor quam nisi sit amet erat. Duis sed dictum est. Etiam vitae lacus eget magna pharetra laoreet. Proin ligula arcu, faucibus at molestie vel, congue nec neque. Nam eu sodales augue.\n" +
                "\n" +
                "Pellentesque porta ex sit amet fermentum venenatis. Nulla vel ipsum in mauris tempus sollicitudin. Sed id ipsum vel est molestie efficitur. In mauris libero, viverra iaculis sodales vel, porttitor eget nisi. Duis erat urna, ullamcorper sit amet faucibus et, tristique vel mauris. In pretium ipsum malesuada diam iaculis, sit amet lobortis leo semper. Nulla et lacus ut risus laoreet lobortis sit amet quis ipsum. Donec tellus dui, convallis id suscipit nec, pharetra quis magna. Nullam quis lorem mi.\n" +
                "\n" +
                "Cras pulvinar est nisi, nec porttitor sapien dictum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras molestie purus vestibulum commodo elementum. Fusce vestibulum lacinia tortor, eu laoreet metus vulputate ac. Maecenas ornare dui vel neque varius vestibulum ut ut justo. Donec hendrerit, lectus id malesuada tristique, massa diam blandit arcu, quis tempor augue dui nec nulla. Nullam lacus mauris, dapibus id erat ut, blandit lacinia nisl. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur luctus interdum lectus pellentesque interdum. Nulla eu erat velit. Cras blandit, mauris non posuere molestie, sapien ante malesuada magna, in volutpat ante mauris efficitur turpis. Proin pulvinar odio eget leo efficitur, et dapibus enim pellentesque. Donec pellentesque efficitur ante, sed sagittis nisi lacinia sit amet. Quisque vel interdum sem, cursus rhoncus justo. Cras pellentesque aliquam mollis. Morbi sed consectetur mauris.\n" +
                "\n" +
                "In at diam vel libero vulputate volutpat. Nam id nulla vitae arcu convallis posuere nec sit amet quam. Donec ut orci blandit, consequat dolor non, fringilla dui. Vivamus quis venenatis massa. Maecenas tempor viverra nulla, vitae malesuada felis imperdiet et");

        list.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vel maximus tortor. Nulla sapien leo, blandit finibus purus vel, fermentum laoreet massa. Vivamus ut odio vel enim maximus lobortis. Pellentesque eros libero, ullamcorper eget nibh eget, fringilla rutrum eros. Fusce id pulvinar lorem. Donec mollis eu velit ac posuere. Pellentesque vehicula ut ligula vitae semper. Maecenas interdum convallis ipsum, nec dictum enim interdum tristique. Suspendisse quis suscipit lacus.\n" +
                "\n" +
                "In eu elit sit amet nisl commodo posuere. Sed consequat interdum tempor. Curabitur lectus tortor, cursus id tellus vitae, euismod pellentesque lorem. Maecenas scelerisque, quam sagittis sodales eleifend, dolor purus pulvinar augue, eu interdum nisl velit eget nunc. Curabitur maximus, massa et rhoncus vehicula, enim metus varius ex, nec porttitor quam nisi sit amet erat. Duis sed dictum est. Etiam vitae lacus eget magna pharetra laoreet. Proin ligula arcu, faucibus at molestie vel, congue nec neque. Nam eu sodales augue.\n" +
                "\n" +
                "Pellentesque porta ex sit amet fermentum venenatis. Nulla vel ipsum in mauris tempus sollicitudin. Sed id ipsum vel est molestie efficitur. In mauris libero, viverra iaculis sodales vel, porttitor eget nisi. Duis erat urna, ullamcorper sit amet faucibus et, tristique vel mauris. In pretium ipsum malesuada diam iaculis, sit amet lobortis leo semper. Nulla et lacus ut risus laoreet lobortis sit amet quis ipsum. Donec tellus dui, convallis id suscipit nec, pharetra quis magna. Nullam quis lorem mi.\n" +
                "\n" +
                "Cras pulvinar est nisi, nec porttitor sapien dictum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras molestie purus vestibulum commodo elementum. Fusce vestibulum lacinia tortor, eu laoreet metus vulputate ac. Maecenas ornare dui vel neque varius vestibulum ut ut justo. Donec hendrerit, lectus id malesuada tristique, massa diam blandit arcu, quis tempor augue dui nec nulla. Nullam lacus mauris, dapibus id erat ut, blandit lacinia nisl. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur luctus interdum lectus pellentesque interdum. Nulla eu erat velit. Cras blandit, mauris non posuere molestie, sapien ante malesuada magna, in volutpat ante mauris efficitur turpis. Proin pulvinar odio eget leo efficitur, et dapibus enim pellentesque. Donec pellentesque efficitur ante, sed sagittis nisi lacinia sit amet. Quisque vel interdum sem, cursus rhoncus justo. Cras pellentesque aliquam mollis. Morbi sed consectetur mauris.\n" +
                "\n" +
                "In at diam vel libero vulputate volutpat. Nam id nulla vitae arcu convallis posuere nec sit amet quam. Donec ut orci blandit, consequat dolor non, fringilla dui. Vivamus quis venenatis massa. Maecenas tempor viverra nulla, vitae malesuada felis imperdiet et");

        list.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vel maximus tortor. Nulla sapien leo, blandit finibus purus vel, fermentum laoreet massa. Vivamus ut odio vel enim maximus lobortis. Pellentesque eros libero, ullamcorper eget nibh eget, fringilla rutrum eros. Fusce id pulvinar lorem. Donec mollis eu velit ac posuere. Pellentesque vehicula ut ligula vitae semper. Maecenas interdum convallis ipsum, nec dictum enim interdum tristique. Suspendisse quis suscipit lacus.\n" +
                "\n" +
                "In eu elit sit amet nisl commodo posuere. Sed consequat interdum tempor. Curabitur lectus tortor, cursus id tellus vitae, euismod pellentesque lorem. Maecenas scelerisque, quam sagittis sodales eleifend, dolor purus pulvinar augue, eu interdum nisl velit eget nunc. Curabitur maximus, massa et rhoncus vehicula, enim metus varius ex, nec porttitor quam nisi sit amet erat. Duis sed dictum est. Etiam vitae lacus eget magna pharetra laoreet. Proin ligula arcu, faucibus at molestie vel, congue nec neque. Nam eu sodales augue.\n" +
                "\n" +
                "Pellentesque porta ex sit amet fermentum venenatis. Nulla vel ipsum in mauris tempus sollicitudin. Sed id ipsum vel est molestie efficitur. In mauris libero, viverra iaculis sodales vel, porttitor eget nisi. Duis erat urna, ullamcorper sit amet faucibus et, tristique vel mauris. In pretium ipsum malesuada diam iaculis, sit amet lobortis leo semper. Nulla et lacus ut risus laoreet lobortis sit amet quis ipsum. Donec tellus dui, convallis id suscipit nec, pharetra quis magna. Nullam quis lorem mi.\n" +
                "\n" +
                "Cras pulvinar est nisi, nec porttitor sapien dictum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras molestie purus vestibulum commodo elementum. Fusce vestibulum lacinia tortor, eu laoreet metus vulputate ac. Maecenas ornare dui vel neque varius vestibulum ut ut justo. Donec hendrerit, lectus id malesuada tristique, massa diam blandit arcu, quis tempor augue dui nec nulla. Nullam lacus mauris, dapibus id erat ut, blandit lacinia nisl. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur luctus interdum lectus pellentesque interdum. Nulla eu erat velit. Cras blandit, mauris non posuere molestie, sapien ante malesuada magna, in volutpat ante mauris efficitur turpis. Proin pulvinar odio eget leo efficitur, et dapibus enim pellentesque. Donec pellentesque efficitur ante, sed sagittis nisi lacinia sit amet. Quisque vel interdum sem, cursus rhoncus justo. Cras pellentesque aliquam mollis. Morbi sed consectetur mauris.\n" +
                "\n" +
                "In at diam vel libero vulputate volutpat. Nam id nulla vitae arcu convallis posuere nec sit amet quam. Donec ut orci blandit, consequat dolor non, fringilla dui. Vivamus quis venenatis massa. Maecenas tempor viverra nulla, vitae malesuada felis imperdiet et");

        list.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vel maximus tortor. Nulla sapien leo, blandit finibus purus vel, fermentum laoreet massa. Vivamus ut odio vel enim maximus lobortis. Pellentesque eros libero, ullamcorper eget nibh eget, fringilla rutrum eros. Fusce id pulvinar lorem. Donec mollis eu velit ac posuere. Pellentesque vehicula ut ligula vitae semper. Maecenas interdum convallis ipsum, nec dictum enim interdum tristique. Suspendisse quis suscipit lacus.\n" +
                "\n" +
                "In eu elit sit amet nisl commodo posuere. Sed consequat interdum tempor. Curabitur lectus tortor, cursus id tellus vitae, euismod pellentesque lorem. Maecenas scelerisque, quam sagittis sodales eleifend, dolor purus pulvinar augue, eu interdum nisl velit eget nunc. Curabitur maximus, massa et rhoncus vehicula, enim metus varius ex, nec porttitor quam nisi sit amet erat. Duis sed dictum est. Etiam vitae lacus eget magna pharetra laoreet. Proin ligula arcu, faucibus at molestie vel, congue nec neque. Nam eu sodales augue.\n" +
                "\n" +
                "Pellentesque porta ex sit amet fermentum venenatis. Nulla vel ipsum in mauris tempus sollicitudin. Sed id ipsum vel est molestie efficitur. In mauris libero, viverra iaculis sodales vel, porttitor eget nisi. Duis erat urna, ullamcorper sit amet faucibus et, tristique vel mauris. In pretium ipsum malesuada diam iaculis, sit amet lobortis leo semper. Nulla et lacus ut risus laoreet lobortis sit amet quis ipsum. Donec tellus dui, convallis id suscipit nec, pharetra quis magna. Nullam quis lorem mi.\n" +
                "\n" +
                "Cras pulvinar est nisi, nec porttitor sapien dictum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras molestie purus vestibulum commodo elementum. Fusce vestibulum lacinia tortor, eu laoreet metus vulputate ac. Maecenas ornare dui vel neque varius vestibulum ut ut justo. Donec hendrerit, lectus id malesuada tristique, massa diam blandit arcu, quis tempor augue dui nec nulla. Nullam lacus mauris, dapibus id erat ut, blandit lacinia nisl. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur luctus interdum lectus pellentesque interdum. Nulla eu erat velit. Cras blandit, mauris non posuere molestie, sapien ante malesuada magna, in volutpat ante mauris efficitur turpis. Proin pulvinar odio eget leo efficitur, et dapibus enim pellentesque. Donec pellentesque efficitur ante, sed sagittis nisi lacinia sit amet. Quisque vel interdum sem, cursus rhoncus justo. Cras pellentesque aliquam mollis. Morbi sed consectetur mauris.\n" +
                "\n" +
                "In at diam vel libero vulputate volutpat. Nam id nulla vitae arcu convallis posuere nec sit amet quam. Donec ut orci blandit, consequat dolor non, fringilla dui. Vivamus quis venenatis massa. Maecenas tempor viverra nulla, vitae malesuada felis imperdiet et");

        list.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vel maximus tortor. Nulla sapien leo, blandit finibus purus vel, fermentum laoreet massa. Vivamus ut odio vel enim maximus lobortis. Pellentesque eros libero, ullamcorper eget nibh eget, fringilla rutrum eros. Fusce id pulvinar lorem. Donec mollis eu velit ac posuere. Pellentesque vehicula ut ligula vitae semper. Maecenas interdum convallis ipsum, nec dictum enim interdum tristique. Suspendisse quis suscipit lacus.\n" +
                "\n" +
                "In eu elit sit amet nisl commodo posuere. Sed consequat interdum tempor. Curabitur lectus tortor, cursus id tellus vitae, euismod pellentesque lorem. Maecenas scelerisque, quam sagittis sodales eleifend, dolor purus pulvinar augue, eu interdum nisl velit eget nunc. Curabitur maximus, massa et rhoncus vehicula, enim metus varius ex, nec porttitor quam nisi sit amet erat. Duis sed dictum est. Etiam vitae lacus eget magna pharetra laoreet. Proin ligula arcu, faucibus at molestie vel, congue nec neque. Nam eu sodales augue.\n" +
                "\n" +
                "Pellentesque porta ex sit amet fermentum venenatis. Nulla vel ipsum in mauris tempus sollicitudin. Sed id ipsum vel est molestie efficitur. In mauris libero, viverra iaculis sodales vel, porttitor eget nisi. Duis erat urna, ullamcorper sit amet faucibus et, tristique vel mauris. In pretium ipsum malesuada diam iaculis, sit amet lobortis leo semper. Nulla et lacus ut risus laoreet lobortis sit amet quis ipsum. Donec tellus dui, convallis id suscipit nec, pharetra quis magna. Nullam quis lorem mi.\n" +
                "\n" +
                "Cras pulvinar est nisi, nec porttitor sapien dictum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras molestie purus vestibulum commodo elementum. Fusce vestibulum lacinia tortor, eu laoreet metus vulputate ac. Maecenas ornare dui vel neque varius vestibulum ut ut justo. Donec hendrerit, lectus id malesuada tristique, massa diam blandit arcu, quis tempor augue dui nec nulla. Nullam lacus mauris, dapibus id erat ut, blandit lacinia nisl. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur luctus interdum lectus pellentesque interdum. Nulla eu erat velit. Cras blandit, mauris non posuere molestie, sapien ante malesuada magna, in volutpat ante mauris efficitur turpis. Proin pulvinar odio eget leo efficitur, et dapibus enim pellentesque. Donec pellentesque efficitur ante, sed sagittis nisi lacinia sit amet. Quisque vel interdum sem, cursus rhoncus justo. Cras pellentesque aliquam mollis. Morbi sed consectetur mauris.\n" +
                "\n" +
                "In at diam vel libero vulputate volutpat. Nam id nulla vitae arcu convallis posuere nec sit amet quam. Donec ut orci blandit, consequat dolor non, fringilla dui. Vivamus quis venenatis massa. Maecenas tempor viverra nulla, vitae malesuada felis imperdiet et");

        list.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vel maximus tortor. Nulla sapien leo, blandit finibus purus vel, fermentum laoreet massa. Vivamus ut odio vel enim maximus lobortis. Pellentesque eros libero, ullamcorper eget nibh eget, fringilla rutrum eros. Fusce id pulvinar lorem. Donec mollis eu velit ac posuere. Pellentesque vehicula ut ligula vitae semper. Maecenas interdum convallis ipsum, nec dictum enim interdum tristique. Suspendisse quis suscipit lacus.\n" +
                "\n" +
                "In eu elit sit amet nisl commodo posuere. Sed consequat interdum tempor. Curabitur lectus tortor, cursus id tellus vitae, euismod pellentesque lorem. Maecenas scelerisque, quam sagittis sodales eleifend, dolor purus pulvinar augue, eu interdum nisl velit eget nunc. Curabitur maximus, massa et rhoncus vehicula, enim metus varius ex, nec porttitor quam nisi sit amet erat. Duis sed dictum est. Etiam vitae lacus eget magna pharetra laoreet. Proin ligula arcu, faucibus at molestie vel, congue nec neque. Nam eu sodales augue.\n" +
                "\n" +
                "Pellentesque porta ex sit amet fermentum venenatis. Nulla vel ipsum in mauris tempus sollicitudin. Sed id ipsum vel est molestie efficitur. In mauris libero, viverra iaculis sodales vel, porttitor eget nisi. Duis erat urna, ullamcorper sit amet faucibus et, tristique vel mauris. In pretium ipsum malesuada diam iaculis, sit amet lobortis leo semper. Nulla et lacus ut risus laoreet lobortis sit amet quis ipsum. Donec tellus dui, convallis id suscipit nec, pharetra quis magna. Nullam quis lorem mi.\n" +
                "\n" +
                "Cras pulvinar est nisi, nec porttitor sapien dictum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras molestie purus vestibulum commodo elementum. Fusce vestibulum lacinia tortor, eu laoreet metus vulputate ac. Maecenas ornare dui vel neque varius vestibulum ut ut justo. Donec hendrerit, lectus id malesuada tristique, massa diam blandit arcu, quis tempor augue dui nec nulla. Nullam lacus mauris, dapibus id erat ut, blandit lacinia nisl. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur luctus interdum lectus pellentesque interdum. Nulla eu erat velit. Cras blandit, mauris non posuere molestie, sapien ante malesuada magna, in volutpat ante mauris efficitur turpis. Proin pulvinar odio eget leo efficitur, et dapibus enim pellentesque. Donec pellentesque efficitur ante, sed sagittis nisi lacinia sit amet. Quisque vel interdum sem, cursus rhoncus justo. Cras pellentesque aliquam mollis. Morbi sed consectetur mauris.\n" +
                "\n" +
                "In at diam vel libero vulputate volutpat. Nam id nulla vitae arcu convallis posuere nec sit amet quam. Donec ut orci blandit, consequat dolor non, fringilla dui. Vivamus quis venenatis massa. Maecenas tempor viverra nulla, vitae malesuada felis imperdiet et");

        list.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vel maximus tortor. Nulla sapien leo, blandit finibus purus vel, fermentum laoreet massa. Vivamus ut odio vel enim maximus lobortis. Pellentesque eros libero, ullamcorper eget nibh eget, fringilla rutrum eros. Fusce id pulvinar lorem. Donec mollis eu velit ac posuere. Pellentesque vehicula ut ligula vitae semper. Maecenas interdum convallis ipsum, nec dictum enim interdum tristique. Suspendisse quis suscipit lacus.\n" +
                "\n" +
                "In eu elit sit amet nisl commodo posuere. Sed consequat interdum tempor. Curabitur lectus tortor, cursus id tellus vitae, euismod pellentesque lorem. Maecenas scelerisque, quam sagittis sodales eleifend, dolor purus pulvinar augue, eu interdum nisl velit eget nunc. Curabitur maximus, massa et rhoncus vehicula, enim metus varius ex, nec porttitor quam nisi sit amet erat. Duis sed dictum est. Etiam vitae lacus eget magna pharetra laoreet. Proin ligula arcu, faucibus at molestie vel, congue nec neque. Nam eu sodales augue.\n" +
                "\n" +
                "Pellentesque porta ex sit amet fermentum venenatis. Nulla vel ipsum in mauris tempus sollicitudin. Sed id ipsum vel est molestie efficitur. In mauris libero, viverra iaculis sodales vel, porttitor eget nisi. Duis erat urna, ullamcorper sit amet faucibus et, tristique vel mauris. In pretium ipsum malesuada diam iaculis, sit amet lobortis leo semper. Nulla et lacus ut risus laoreet lobortis sit amet quis ipsum. Donec tellus dui, convallis id suscipit nec, pharetra quis magna. Nullam quis lorem mi.\n" +
                "\n" +
                "Cras pulvinar est nisi, nec porttitor sapien dictum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras molestie purus vestibulum commodo elementum. Fusce vestibulum lacinia tortor, eu laoreet metus vulputate ac. Maecenas ornare dui vel neque varius vestibulum ut ut justo. Donec hendrerit, lectus id malesuada tristique, massa diam blandit arcu, quis tempor augue dui nec nulla. Nullam lacus mauris, dapibus id erat ut, blandit lacinia nisl. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur luctus interdum lectus pellentesque interdum. Nulla eu erat velit. Cras blandit, mauris non posuere molestie, sapien ante malesuada magna, in volutpat ante mauris efficitur turpis. Proin pulvinar odio eget leo efficitur, et dapibus enim pellentesque. Donec pellentesque efficitur ante, sed sagittis nisi lacinia sit amet. Quisque vel interdum sem, cursus rhoncus justo. Cras pellentesque aliquam mollis. Morbi sed consectetur mauris.\n" +
                "\n" +
                "In at diam vel libero vulputate volutpat. Nam id nulla vitae arcu convallis posuere nec sit amet quam. Donec ut orci blandit, consequat dolor non, fringilla dui. Vivamus quis venenatis massa. Maecenas tempor viverra nulla, vitae malesuada felis imperdiet et");
        return list;
    }
}