package com.example.sportslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.LinkedList;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements SportListAdapter.OnSportListener {
    private LinkedList<Exercise> mExerciseList;
    private RecyclerView mRecyclerView;
    private TextView mQuantityView;
    private SportListAdapter mAdapter;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mQuantityView = findViewById(R.id.exercise_quantity);

        db = new DatabaseHelper(this);

        mExerciseList = new LinkedList<>();
        fetchData();

        /*// Create an adapter and supply the data to be displayed.
        mAdapter = new SportListAdapter(this, mRecipeList, this);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         */
    }

    public void fetchData(){
        db = new DatabaseHelper(this);
        try {
            db.createDataBase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //get all exercise
        //mExerciseList = db.getAllExercise(this);
        //get Man Abs Beginner - gender, type of exercise, complexity
        mExerciseList = db.getManAbsBeginner(this);
        //mExerciseList = db.getWomanFullAdvanced(this);
        mQuantityView.setText("Exercise quantity: " + String.valueOf(mExerciseList.size()));

        //Toast.makeText(MainActivity.this, "Success= "+ mRecipeList.toString(), Toast.LENGTH_SHORT).show();
        mAdapter = new SportListAdapter(this, mExerciseList, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onRecipeClick(int position) {
        Log.d(TAG, "onExerciseClick: clicked.");

    }
}