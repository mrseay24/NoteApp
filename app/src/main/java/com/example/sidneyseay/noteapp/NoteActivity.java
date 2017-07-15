package com.example.sidneyseay.noteapp;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;

public class NoteActivity extends BaseActivity {

    private EditText mEtTitle;
    private EditText mEtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);

        mEtTitle = (EditText) findViewById(R.id.note_et_title);
        mEtContent = (EditText) findViewById(R.id.note_et_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_note_save:
                saveNote();
                break;
        }

        return true;
    }

    private void saveNote(){

        WriteNoteActivity note = new WriteNoteActivity(System.currentTimeMillis(), mEtTitle.getText().toString(),
                mEtContent.getText().toString());
        if(Utilities.saveNote(this, note)){
            Toast.makeText(this, "note is saved", Toast.LENGTH_SHORT).show();

        } else{
            Toast.makeText(this, "cannot save the note", Toast.LENGTH_SHORT).show();
        }
        finish();

    }
}
