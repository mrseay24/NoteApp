package com.example.sidneyseay.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sidneyseay.noteapp.adapter.NoteAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;

public class WriteNoteActivity extends BaseActivity {

    private ListView mListViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        ButterKnife.bind(this);

        mListViewNotes = (ListView) findViewById(R.id.listview_notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_main_new_note:
                startActivity(new Intent(this, NoteActivity.class));
                break;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListViewNotes.setAdapter(null);

        ArrayList<WriteNote> notes = Utilities.getAllSavedNotes(this);

        if(notes == null || notes.size() == 0){
            Toast.makeText(this, "You do not have any notes saved.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            NoteAdapter na = new NoteAdapter(this, R.layout.item_note, notes);
            mListViewNotes.setAdapter(na);

            mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fileName = ((WriteNote)mListViewNotes.getItemAtPosition(position)).getmDateTime()
                            + Utilities.FILE_EXTENSION;

                    Intent viewNoteIntent = new Intent(getApplicationContext(), NoteActivity.class);
                    viewNoteIntent.putExtra("NOTE_FILE", fileName);
                    startActivity(viewNoteIntent);
                }
            });

        }
    }

}