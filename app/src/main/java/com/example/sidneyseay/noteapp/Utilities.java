package com.example.sidneyseay.noteapp;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Utilities {

    public static final String EXTRAS_NOTE_FILENAME = "EXTRAS_NOTE_FILENAME";
    public static final String FILE_EXTENSION = ".bin";

    /**
     * Save a note on private storage of the app
     * @param context Application's context
     * @param note The note to be saved
     */
    public static boolean saveNote(Context context, WriteNote note) {

        String fileName = String.valueOf(note.getmDateTime()) + FILE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream oos;

        try{
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(note);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    /**
     * Read all saved
     * @param context Application's context
     * @return ArrayList of Note
     */
    static ArrayList<WriteNote> getAllSavedNotes(Context context) {
        ArrayList<WriteNote> notes = new ArrayList<>();

        File filesDir = context.getFilesDir();
        ArrayList<String> noteFiles = new ArrayList<>();

        //add .bin files to the noteFiles list
        for(String file : filesDir.list()) {
            if(file.endsWith(FILE_EXTENSION)) {
                noteFiles.add(file);
            }
        }

        //read objects and add to list of notes
        FileInputStream fis;
        ObjectInputStream ois;

        for (int i = 0; i < noteFiles.size(); i++) {
            try{
                fis = context.openFileInput(noteFiles.get(i));
                ois = new ObjectInputStream(fis);

                notes.add((WriteNote) ois.readObject());
                fis.close();
                ois.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        return notes;
    }

    /**
     * Loads a note file by its name
     * @param context Application's context
     * @param fileName Name of the note file
     * @return A Note object, null if something goes wrong!
     */
    public static WriteNote getNoteByName(Context context, String fileName) {

        File file = new File(context.getFilesDir(), fileName);
        WriteNote note;

        if(file.exists()) { //check if file actually exist

            FileInputStream fis;
            ObjectInputStream ois;

            try { //load the file
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);

                note = (WriteNote) ois.readObject();

                fis.close();
                ois.close();


            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            return note;

        }
            return null;

    }


    public static void deleteNote(Context context, String fileName) {
        File dir = context.getFilesDir();
        File file = new File(dir, fileName);

        if(file.exists()){
            file.delete();
        }
    }
}