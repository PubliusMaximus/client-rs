package com.interview.client_rs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.interview.client_rs.adapters.ViewPagerAdapter;
import com.interview.client_rs.fragments.InsertFragment;
import com.interview.client_rs.fragments.SelectFragment;
import com.interview.client_rs.fragments.TableFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new InsertFragment(), "Insert");
        adapter.addFragment(new TableFragment(), "Table");
        adapter.addFragment(new SelectFragment(), "Select");
        viewPager.setAdapter(adapter);
    }

    public void onBackPressed() {
        Log.i("MainActivity", "was closed");
        finish();
    }
}
