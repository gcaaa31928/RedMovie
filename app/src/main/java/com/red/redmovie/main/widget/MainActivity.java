package com.red.redmovie.main.widget;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.red.redmovie.R;
import com.red.redmovie.main.presenter.MainPresenter;
import com.red.redmovie.main.presenter.MainPresenterImpl;
import com.red.redmovie.main.view.MainView;
import com.red.redmovie.movie.widget.MoviesFragment;

public class MainActivity extends AppCompatActivity implements MainView {

    private Toolbar mToolBar;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        mMainPresenter = new MainPresenterImpl(this);

//        initRecyclerView();
        switchToMovie();
    }

    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("This is a pen");
    }

    @Override
    public void switchToMovie() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MoviesFragment()).commit();
        mToolBar.setTitle(R.string.movie);
    }

    //
//    private void initRecyclerView() {
//        initializeData();
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(llm);
//        MoviesAdapter rvAdapter = new MoviesAdapter(persons);
//        recyclerView.setAdapter(rvAdapter);
//
//    }
//
//    private List<Person> persons;
//
//    private void initializeData(){
//        persons = new ArrayList<>();
//        persons.add(new Person("Emma Wilson", "23 years old", 0));
//        persons.add(new Person("Lavery Maiss", "25 years old", 0));
//        persons.add(new Person("Lillie Watts", "35 years old", 0));
//        persons.add(new Person("Lillie Watts", "35 years old", 0));
//        persons.add(new Person("Lillie Watts", "35 years old", 0));
//        persons.add(new Person("Lillie Watts", "35 years old", 0));
//        persons.add(new Person("Lillie Watts", "35 years old", 0));
//    }
}
