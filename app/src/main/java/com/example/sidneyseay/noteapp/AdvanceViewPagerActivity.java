package com.example.sidneyseay.noteapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sidneyseay.noteapp.adapter.ViewFragmentStateAdapter;
import com.example.sidneyseay.noteapp.fragment.SadFragment;
import com.example.sidneyseay.noteapp.fragment.GoodFragment;
import com.example.sidneyseay.noteapp.fragment.BadFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdvanceViewPagerActivity extends AppCompatActivity {

    private ArrayList<Pair<String, Fragment>> list = new ArrayList<Pair<String, Fragment>>();

    @BindView(R.id.activity_advance_view_pager_tablayout)
    TabLayout tabLayout;

    @BindView(R.id.activity_advance_view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_view_pager);
        ButterKnife.bind(this);
        list.add(new Pair<String, Fragment>("Bad",new BadFragment()));
        list.add(new Pair<String, Fragment>("Good",new GoodFragment()));
        list.add(new Pair<String, Fragment>("Sad",new SadFragment()));
        ViewFragmentStateAdapter adapter =
                new ViewFragmentStateAdapter(this.getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}
