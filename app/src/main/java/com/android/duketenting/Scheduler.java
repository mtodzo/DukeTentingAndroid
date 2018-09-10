package com.android.duketenting;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Scheduler {

    private List<Tenter> myTenters;
    private PriorityQueue<Tenter> myTenterPQ;
    private int numberOfDays;
    private int peoplePerInterval = 1;

    private static final String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};


    public Scheduler(List<Tenter> tenters, int numDays) {
        myTenters = tenters;
        myTenterPQ = new PriorityQueue<>(new TenterComparator());
        myTenterPQ.addAll(tenters);
        numberOfDays = numDays;
    }

    /**
     * Generates the overall schedule for the tenting group
     * TODO add number of ppl/interval
     */
    public void generateSchedule() {
        Tenter current;
        List<Tenter> tentersToAddBack = new ArrayList<>();
        for (int x=0; x<numberOfDays; x++) {
            Tenter.setCurrentDay(x);
            for (Tenter t: myTenters) {
                t.initializeAssignmentMap();
            }
            for (int i=0; i<ObligationManager.INTERVALS_PER_DAY; i++) {
                Tenter.setInterval(i);
                tentersToAddBack.clear();
                for (int j=0; j<peoplePerInterval; j++) {
                    current = myTenterPQ.poll();
                    if (current.isAvailable()){
                        current.assignInterval(x, new Interval(i, i+1));
                    }
                    else {
                        if (myTenterPQ.isEmpty()) {
                            System.out.println("NEED MORE PPL AT INTERVAL "+ i + " " + (i+1));
                        }
                        else {
                            j--;
                        }
                    }
                    tentersToAddBack.add(current);
                }
                myTenterPQ.addAll(tentersToAddBack); //done after num ppl/interval
            }
        }
    }

}

