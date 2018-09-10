package com.android.duketenting;

import java.util.Comparator;

public class TenterComparator implements Comparator<Tenter>{

    @Override
    public int compare(Tenter t1, Tenter t2) {
        return t1.getNumFilledIntervals() - t2.getNumFilledIntervals();
    }

}
