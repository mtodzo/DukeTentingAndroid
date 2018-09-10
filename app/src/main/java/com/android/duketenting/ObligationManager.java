package com.android.duketenting;
import java.util.ArrayList;
import java.util.List;

public class ObligationManager {
    public static final int INTERVALS_PER_DAY = 24;
    public static final int INTERVAL_LENGTH = 1;

    List<Interval> obligations;
    private int[] upcomingConsecIntervals;

    public ObligationManager() {
        obligations = new ArrayList<>();
        upcomingConsecIntervals = new int[INTERVALS_PER_DAY];
        this.calculateUpcomingConsecIntervals();
    }

    private void calculateUpcomingConsecIntervals() {
        for(int x=0; x<INTERVALS_PER_DAY; x++) {
            upcomingConsecIntervals[x] = getConsecAvailability(x);
        }

    }

    public void addObligation(Interval o) {
        obligations.add(o);
    }

    //if the interval doesn't lie between a start and end time return true . if it does false
    //true = free; false = busy
    public boolean getAvailabilityStatus(int interval) {
        for (Interval o: obligations) {
            if (o.getStart() <= interval && interval < o.getFinish()) {
                return false;
            }
            if (interval < o.getStart())
                break;
        }
        return true;
    }

    public int getConsecAvailability(int x) {
        if (!getAvailabilityStatus(x)) {
            return 0;
        }
        return getNextObligationStart(x) - x;
    }

    private int getNextObligationStart(int x) {
        for (Interval o: obligations) {
            if (o.getStart() < x)
                continue;
            if (o.getStart() > x)
                return o.getStart() - x;
        }
        return INTERVALS_PER_DAY - x;
    }

    public int calculateAvailableIntervals() {
        int obligationTime = 0;
        for (Interval o: obligations) {
            obligationTime = obligationTime + (o.getFinish() - o.getStart());
        }
        return INTERVALS_PER_DAY - obligationTime/INTERVAL_LENGTH;
    }
}

