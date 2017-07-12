package com.example.sidneyseay.noteapp;


import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by sidneyseay on 7/10/17.
 */

public class HandlerActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.bind(this);
    }
}
