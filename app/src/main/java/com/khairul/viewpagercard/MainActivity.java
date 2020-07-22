package com.khairul.viewpagercard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    ViewPager viewPager;
    Adapter adapter;
    Integer[] colors = null;
    List<Model> models;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TabLayout tabIndicator;

    ImageButton logout, progres;
    ImageView btn_next, btn_prev;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.menu_1, "Guess Sound"));
        models.add(new Model(R.drawable.menu_2, "Drawing\neveriting"));
        models.add(new Model(R.drawable.menu_3, "Story &\nQuestion"));

        tabIndicator = findViewById(R.id.tabLayout);

        logout = findViewById(R.id.logout);
        progres = findViewById(R.id.progress);
        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(15, 20, 15, 0);

        //setup tab layout with pager view
        tabIndicator.setupWithViewPager(viewPager);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3)};

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount()-1) && position < (colors.length -1)){
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position+1]));

                }else{
                    viewPager.setBackgroundColor(colors[colors.length -1 ]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void progress(View view){
        Toast.makeText(this, "progress", Toast.LENGTH_SHORT).show();
    }

    public void logout(View view){
        Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
    }

    public void next(View view){
        position = viewPager.getCurrentItem();
        if (position < models.size()){
            position++;
            viewPager.setCurrentItem(position);
        }
    }

    public void prev(View view){
        position = viewPager.getCurrentItem();
        if (position >= 0){
            position--;
            viewPager.setCurrentItem(position);
        }
    }

}
