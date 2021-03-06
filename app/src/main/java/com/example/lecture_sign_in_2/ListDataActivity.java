package com.example.lecture_sign_in_2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }


    /*doing a select all from the data*/
    private void populateListView(){
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();

        while (data.moveToNext()) {

           listData.add(data.getString(1) + data.getString(2) + data.getString(3) + data.getString(4));


        }


        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }

}
