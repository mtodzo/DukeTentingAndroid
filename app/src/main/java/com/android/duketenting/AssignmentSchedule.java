package com.android.duketenting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class AssignmentSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_schedule);


        Integer[] array = {1 , 2 , 3};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, R.layout.activity_grid_view, R.id.textView, array);
        GridView gridView = findViewById(R.id.grid);
        gridView.setAdapter(adapter);
    }
}
