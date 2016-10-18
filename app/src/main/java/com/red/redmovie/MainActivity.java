package com.red.redmovie;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
//        initRecyclerView();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("This is a pen");
    }

    private void initRecyclerView() {
        initializeData();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        RVAdapter rvAdapter = new RVAdapter(persons);
        recyclerView.setAdapter(rvAdapter);

    }

    private List<Person> persons;

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", 0));
        persons.add(new Person("Lavery Maiss", "25 years old", 0));
        persons.add(new Person("Lillie Watts", "35 years old", 0));
        persons.add(new Person("Lillie Watts", "35 years old", 0));
        persons.add(new Person("Lillie Watts", "35 years old", 0));
        persons.add(new Person("Lillie Watts", "35 years old", 0));
        persons.add(new Person("Lillie Watts", "35 years old", 0));
    }
}
