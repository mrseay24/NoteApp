package com.example.sidneyseay.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TextView tv_enter;
    public Button but1;

    public void init(){
        but1 = (Button)findViewById(R.id.but1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EnterActivity.class);

                startActivity(intent);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ImageView imageView = (ImageView) findViewById(R.id.note_icon);
        imageView.setImageResource(R.drawable.task_notes_icon);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_rotate);
        imageView.setAnimation(animation);

        init();
        intialView();


    }

    public void intialView(){
        tv_enter = (TextView) findViewById(R.id.but1);
    }

    private void setListener(){
        tv_enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"You clicked Enter",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
