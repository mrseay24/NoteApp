package com.example.sidneyseay.noteapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sidneyseay.noteapp.adapter.NoteAdapter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import butterknife.ButterKnife;

public class WriteNoteActivity extends BaseActivity implements Serializable {

    private ListView mListViewNotes;

    public WriteNoteActivity(){

    }

    public WriteNoteActivity(long l, String s, String s1) {
        super();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        ButterKnife.bind(this);

        mListViewNotes = (ListView) findViewById(R.id.listview_notes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListViewNotes.setAdapter(null);

        ArrayList<WriteNoteActivity> notes = Utilities.getAllSavedNotes(this);

        if(notes == null || notes.size()==0){
            Toast.makeText(this, "You have no saved notes!", Toast.LENGTH_SHORT).show();
            return;
        } else{
            NoteAdapter na = new NoteAdapter(this, R.layout.item_note, notes);
            mListViewNotes.setAdapter(na);
            mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String fileName = ((WriteNoteActivity) mListViewNotes.getItemAtPosition(position)).getmDateTime()
                            + Utilities.FILE_EXTENSION;

                    Intent viewNoteIntent = new Intent(getApplicationContext(), NoteActivity.class);
                    viewNoteIntent.putExtra("NOTE_FILE", fileName);
                    startActivity(viewNoteIntent);

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_main_new_note:
                //Start NoteActivity
                startActivity(new Intent(this, NoteActivity.class));
                break;
        }
        return true;
    }

    private long mDateTime;
    private String mTitle;
    private String mContent;

    public WriteNoteActivity(ListView mListViewNotes, long dateTime, String title, String content) {
        this.mListViewNotes = mListViewNotes;
        this.mDateTime = dateTime;
        this.mTitle = title;
        this.mContent = content;
    }

    public void setmDateTime(long dateTime) {
        this.mDateTime = dateTime;
    }

    public void setmTitle(String title) {
        this.mTitle = title;
    }

    public void setmContent(String content) {
        this.mContent = content;
    }

    public long getmDateTime() {
        return mDateTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public String getDateTimeFormatted(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",
                context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDateTime));
    }
}
