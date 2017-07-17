package com.example.sidneyseay.noteapp.adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sidneyseay.noteapp.R;
import com.example.sidneyseay.noteapp.WriteNote;

import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends ArrayAdapter<WriteNote> {

    public static final int WRAP_CONTENT_LENGTH = 50;
    public NoteAdapter(Context context, int resource, ArrayList<WriteNote> notes) {
        super(context, resource, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_note, null);
        }

        WriteNote note = getItem(position);

        if(note != null) {
            TextView title = (TextView) convertView.findViewById(R.id.list_note_title);
            TextView date = (TextView) convertView.findViewById(R.id.list_note_date);
            TextView content = (TextView) convertView.findViewById(R.id.list_note_content);

            title.setText(note.getmTitle());
            date.setText(note.getDateTimeFormatted(getContext()));

            //correctly show preview of the content (not more than 50 char or more than one line!)
            int toWrap = WRAP_CONTENT_LENGTH;
            int lineBreakIndex = note.getmContent().indexOf('\n');
            //not an elegant series of if statements...needs to be cleaned up!
            if(note.getmContent().length() > WRAP_CONTENT_LENGTH || lineBreakIndex < WRAP_CONTENT_LENGTH) {
                if(lineBreakIndex < WRAP_CONTENT_LENGTH) {
                    toWrap = lineBreakIndex;
                }
                if(toWrap > 0) {
                    content.setText(note.getmContent().substring(0, toWrap) + "...");
                } else {
                    content.setText(note.getmContent());
                }
            } else {
                content.setText(note.getmContent());
            }
        }

        return convertView;
    }

}