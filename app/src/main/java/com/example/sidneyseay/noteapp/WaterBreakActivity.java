package com.example.sidneyseay.noteapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaterBreakActivity extends AppCompatActivity {

    @BindView(R.id.activity_waterbreak_et)
    EditText editText;

    @BindView(R.id.activity_waterbreak_bt)
    Button button;

    private View.OnClickListener startListener;
    private View.OnClickListener stopListener;
    private View.OnClickListener resetListener;
    private final int TIMER = 123;
    Handler handler = new Handler();
    private int time=0;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if(time>=0){
                editText.setText(String.valueOf(time));
                //handler.post(runnable);
                handler.postDelayed(runnable, 1000);
            }
        }
    };
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterbreak);
        ButterKnife.bind(this);
        startListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText("Stop");
                editText.setEnabled(false);
                button.setOnClickListener(stopListener);
                time = Integer.valueOf(editText.getText().toString());
                handler.postDelayed(runnable, 1000);
            }
        };
        stopListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText("Reset");
                button.setOnClickListener(resetListener);
                handler.removeCallbacks(runnable);
            }
        };
        resetListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText("Start");
                editText.setEnabled(true);
                button.setOnClickListener(startListener);
            }
        };
        button.setOnClickListener(startListener);

        startThread();
    }

    private void startThread() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "TimerMessage");
                    msg.setData(bundle);
                    msg.what = TIMER;
                    handler.sendMessage(msg);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();



    }

}