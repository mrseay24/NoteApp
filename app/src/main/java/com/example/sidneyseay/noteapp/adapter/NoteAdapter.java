package com.example.sidneyseay.noteapp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sidneyseay.noteapp.R;
import com.example.sidneyseay.noteapp.WriteNote;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by sidneyseay on 7/15/17.
 */

public class NoteAdapter extends ArrayAdapter<WriteNote> {
    public NoteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<WriteNote> notes) {
        super(context, resource, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_note, null);
        }
        WriteNote note = getItem(position);

        if(note != null){
            TextView title = (TextView) convertView.findViewById(R.id.list_note_title);
            TextView date = (TextView) convertView.findViewById(R.id.list_note_date);
            TextView content = (TextView) convertView.findViewById(R.id.list_note_content);

            title.setText(note.getmTitle());
            date.setText(note.getDateTimeFormatted(getContext()));

            if(note.getmContent().length() > 50){
                content.setText(note.getmContent().substring(0,50));
            } else{
                content.setText(note.getmContent());
            }
        }
        return convertView;
    }
}