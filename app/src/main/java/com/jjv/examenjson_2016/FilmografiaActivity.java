package com.jjv.examenjson_2016.UI;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;


import com.jjv.examenjson_2016.Adapters.ListAdapter;
import com.jjv.examenjson_2016.Objects.Filmografia;
import com.jjv.examenjson_2016.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FilmografiaActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmografia);
        Intent intent = getIntent();
        ArrayList<Filmografia>  result = intent.getParcelableArrayListExtra(InfoActorActivity.FILMOGRAFIA);
        ListAdapter adapter = new ListAdapter(this,result);
        setListAdapter(adapter);
    }
}
