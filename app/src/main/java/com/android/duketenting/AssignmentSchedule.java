package com.android.duketenting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AssignmentSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_schedule);

        ArrayList<Integer> test = new ArrayList<>();
        test.add(10);
        Integer[] array = new Integer[24*5];
        for (int x=0; x<array.length; x++){
            array[x] = x;
        }
        ArrayAdapter<Integer> gridAdapter = new ArrayAdapter<>(this, R.layout.activity_grid_view, R.id.textView, array);
        GridView gridView = findViewById(R.id.grid);
        gridView.setAdapter(gridAdapter);

    }
}
