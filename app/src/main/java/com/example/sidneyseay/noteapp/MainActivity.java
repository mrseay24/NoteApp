package com.example.sidneyseay.noteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TextView tv_write_note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ImageView imageView = (ImageView) findViewById(R.id.note_icon);
        imageView.setImageResource(R.drawable.task_notes_icon);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_rotate);
        imageView.setAnimation(animation);

    }

    public void buttonOnClick(View v){
        Button button = (Button) v;
    }
}
