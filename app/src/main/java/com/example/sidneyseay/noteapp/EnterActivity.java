package com.example.sidneyseay.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;

public class EnterActivity extends BaseActivity {

    public Button write_note_button;
    public Button mood_button;
    public Button waterbreak_button;
    public Button notegesture_button;
    public Button checkitems_button;

    public void init(){
        write_note_button = (Button)findViewById(R.id.write_note_button);
        write_note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterActivity.this, WriteNoteActivity.class);

                startActivity(intent);
            }
        });
    }

    public void init2(){
        mood_button = (Button)findViewById(R.id.mood_button);
        mood_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterActivity.this, AdvanceViewPagerActivity.class);

                startActivity(intent);
            }
        });
    }

    public void init3(){
        waterbreak_button = (Button)findViewById(R.id.waterbreak_button);
        waterbreak_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterActivity.this, WaterBreakActivity.class);

                startActivity(intent);
            }
        });
    }

    public void init4(){
        notegesture_button = (Button)findViewById(R.id.notegesture_button);
        notegesture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterActivity.this, NoteGestureActivity.class);

                startActivity(intent);
            }
        });
    }

    public void init5(){
        checkitems_button = (Button)findViewById(R.id.checkitems_button);
        checkitems_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterActivity.this, CheckItemsActivity.class);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ButterKnife.bind(this);

        init();
        init2();
        init3();
        init4();
        init5();
    }
}
