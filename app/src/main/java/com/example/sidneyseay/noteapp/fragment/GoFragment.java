package com.example.sidneyseay.noteapp.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sidneyseay on 7/12/17.
 */

public class GoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final Context context;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private final ArrayList<String> contentList;
    private ListView listView;

    public GoFragment(){
        context = getContext();
        contentList = new ArrayList<String>();
        contentList.add("Write Note");
    }


}
