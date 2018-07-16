package com.example.onewdivideslaptop.minihrm_application;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);

        viewPager = (ViewPager) findViewById(R.id.viewPager_id);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add Fragment
        viewPagerAdapter.addFragment(new ProfileFragment(),"Profile");
        viewPagerAdapter.addFragment(new calendarFragment(),"Calendar");
        viewPagerAdapter.addFragment(new LeaveRequestFragment(),"Leave");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.userforminihrm);
        tabLayout.getTabAt(1).setIcon(R.drawable.calendarforminihrm);
        tabLayout.getTabAt(2).setIcon(R.drawable.mailminihrm);

        tabLayout.setScrollPosition(staticData.tabIndex,0f,true);
        viewPager.setCurrentItem(staticData.tabIndex);

    }
}
