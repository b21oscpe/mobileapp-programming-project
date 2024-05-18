package com.example.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=b21oscpe";

    private ArrayList<River> rivers;

    private RecyclerViewAdapter recyclerViewAdapter;

    private RecyclerView recyclerView;

    private Filter filter;

    private Button about_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new JsonTask(this).execute(JSON_URL);

        about_button = findViewById(R.id.about_button);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<River>>(){}.getType();
        rivers = gson.fromJson(json, type);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewAdapter = new RecyclerViewAdapter(rivers);
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_filter_asc:
                filterAscending();
                Log.d("==>","Filters ascending");
                return true;
            case R.id.action_filter_dsc:
                filterDescending();
                Log.d("==>","Filters descending");
                return true;
            case R.id.action_filter_alph:
                filterAlphabetical();
                Log.d("==>","Filters alphabetical");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void filterAscending(){
        filter = new FilterAscending();
        recyclerViewAdapter.filter(filter);
    }

    public void filterDescending(){
        filter = new FilterDescending();
        recyclerViewAdapter.filter(filter);
    }

    public void filterAlphabetical(){
        filter = new FilterAlphabetical();
        recyclerViewAdapter.filter(filter);
    }

}