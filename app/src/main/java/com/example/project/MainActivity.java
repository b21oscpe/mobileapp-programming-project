package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

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
    private Button about_button, reset_button;
    private SearchView search_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("==>", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new JsonTask(this).execute(JSON_URL);

        about_button = findViewById(R.id.about_button);
        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("==>", "Navigated to 'About'");
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        search_field = findViewById(R.id.search);
        search_field.clearFocus();
        search_field.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterSearch(s);
                return true;
            }

        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<River>>(){}.getType();
        rivers = gson.fromJson(json, type);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewAdapter = new RecyclerViewAdapter(this, rivers);
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);

        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        String filter = preferences.getString("filter", "none");
        Log.d("==>", filter);
        switch (filter) {
            case "ascending":
                filterAscending();
            case "descending":
                filterDescending();
            case "alphabetical":
                filterAlphabetical();
            case "none":
                break;
            default:
                filterSearch(filter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_filter_asc) {
            filterAscending();
            return true;
        }
        else if (id == R.id.action_filter_dsc) {
            filterDescending();
            return true;
        }
        else if (id == R.id.action_filter_alph) {
            filterAlphabetical();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void filterAscending(){
        Log.d("==>", "Sorts ascending");
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("filter", "ascending");
        edit.apply();
        filter = new FilterAscending();
        recyclerViewAdapter.filter(filter, "");
    }

    public void filterDescending(){
        Log.d("==>", "Sorts descending");
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("filter", "descending");
        edit.apply();
        filter = new FilterDescending();
        recyclerViewAdapter.filter(filter, "");
    }

    public void filterAlphabetical(){
        Log.d("==>","Sorts alphabetical");
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("filter", "alphabetical");
        edit.apply();
        filter = new FilterAlphabetical();
        recyclerViewAdapter.filter(filter, "");
    }

    public void filterSearch(String query){
        Log.d("==>","Filters by search");
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("filter", query);
        edit.apply();
        filter = new FilterSearch();
        recyclerViewAdapter.filter(filter, query);
        search_field.setQueryHint(query);
    }

}