package com.example.sidneyseay.noteapp;

import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
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

    private String mNoteFileName;
    private WriteNote mLoadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);

        mEtTitle = (EditText) findViewById(R.id.note_et_title);
        mEtContent = (EditText) findViewById(R.id.note_et_content);

        mNoteFileName = getIntent().getStringExtra("NOTE_FILE");
        if(mNoteFileName != null && !mNoteFileName.isEmpty()){
            mLoadedNote = Utilities.getNoteByName(this, mNoteFileName);

            if(mLoadedNote != null){
                mEtTitle.setText(mLoadedNote.getmTitle());
                mEtContent.setText(mLoadedNote.getmContent());
            }

        }
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

            case R.id.action_note_delete:
                deleteNote();
                break;
        }

        return true;
    }



    private void saveNote(){
        WriteNote note;

        if(mEtTitle.getText().toString().trim().isEmpty() || mEtContent.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter title and content", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mLoadedNote == null){
            //get current time of phone for new note
            note = new WriteNote(System.currentTimeMillis(), mEtTitle.getText().toString(),
                    mEtContent.getText().toString());
        }else{
            //get time of the note itself
            note = new WriteNote(mLoadedNote.getmDateTime(), mEtTitle.getText().toString(),
                    mEtContent.getText().toString());

        }


        if(Utilities.saveNote(this, note)){
            Toast.makeText(this, "note is saved", Toast.LENGTH_SHORT).show();

        } else{
            Toast.makeText(this, "cannot save the note", Toast.LENGTH_SHORT).show();
        }
        finish();

    }

    private void deleteNote() {
        if(mLoadedNote == null){
            finish();
        } else{
            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("You are about to delete" + mEtTitle.getText().toString() + ", are you sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilities.deleteNote(getApplicationContext()
                                    , mLoadedNote.getmDateTime() + Utilities.FILE_EXTENSION);
                            Toast.makeText(getApplicationContext()
                                    , mEtTitle.getText().toString() + "is deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .setCancelable(false);

            dialog.show();


        }

    }
}