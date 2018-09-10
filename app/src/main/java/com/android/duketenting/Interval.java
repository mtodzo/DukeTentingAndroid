package com.android.duketenting;

public class Interval {
    private int startTime;
    private int endTime;

    public Interval(int start, int end) {
        startTime = start;
        endTime = end;
    }

    public boolean checkIfThisIntervalFallsWithin(Interval i){
        if (this.getStart() <= i.getStart() && i.getStart() < this.getFinish() ||
                this.getFinish() <= i.getStart() && i.getFinish() < this.getFinish()) {
            return true;
        }
        return false;
    }

    public int getStart() {
        return startTime;
    }

    public int getFinish() {
        return endTime;
    }

    @Override
    public String toString() {
        return startTime + " " + endTime;
    }

}

