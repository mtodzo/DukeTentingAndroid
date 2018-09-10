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

//        ArrayList<Integer> test = new ArrayList<>();
//        test.add(10);
//        Integer[] array = new Integer[24*5];
//        for (int x=0; x<array.length; x++){
//            array[x] = x;
//        }

        Tenter miles = new Tenter("miles");
        ObligationManager milesOM = new ObligationManager();
        milesOM.addObligation(new Interval(14, 24));
        miles.addObligationManager(milesOM);
        ObligationManager milesOM2 = new ObligationManager();
        milesOM2.addObligation(new Interval(0,7));
        miles.addObligationManager(milesOM2);

        Tenter ethan = new Tenter("ethan");
        ObligationManager ethanOM = new ObligationManager();
        ethanOM.addObligation(new Interval(8, 13));
        ethan.addObligationManager(ethanOM);
        ObligationManager ethanOM2 = new ObligationManager();
        ethanOM2.addObligation(new Interval(14,24));
        ethan.addObligationManager(ethanOM2);


        Tenter soph = new Tenter("soph");
        ObligationManager sophOM = new ObligationManager();
        sophOM.addObligation(new Interval(0, 7));
        soph.addObligationManager(sophOM);
        ObligationManager sophOM2 = new ObligationManager();
        sophOM2.addObligation(new Interval(8,13));
        soph.addObligationManager(sophOM2);

        ArrayList<Tenter> tenters = new ArrayList<>();
        tenters.add(miles);
        tenters.add(soph);
        tenters.add(ethan);

        Scheduler sched = new Scheduler(tenters, 2);
        sched.generateSchedule();

        ArrayList<Integer> alist = new ArrayList<>();
        for (int intervalCounter=0; intervalCounter<24; intervalCounter++){
            for (int y=0; y<3; y++){
                if (y == 0){
                    alist.add(intervalCounter);
                }
                else{
                    if (soph.isAssigned(y-1, new Interval(intervalCounter, intervalCounter+1))){
                        alist.add(1);
                    }
                    else{
                        alist.add(0);
                    }
                }
            }
        }


        ArrayAdapter<Integer> gridAdapter = new ArrayAdapter<>(this, R.layout.activity_grid_view, R.id.textView, alist);
        GridView gridView = findViewById(R.id.grid);
        gridView.setAdapter(gridAdapter);

    }
}
