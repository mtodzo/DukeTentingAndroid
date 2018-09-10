package com.android.duketenting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Tenter {

    private static int currentDayNumber;
    private static int currentIntervalNumber;

    //private List<Day> myDayList;
    private List<ObligationManager> myObligationManagers;
    //private Map<String, ObligationManager> dayToObligationManager;
    private Map<Integer, List<Interval>> myAssignedIntervals;	//integer corresponds to day number
    private int numberOfAvailableIntervals; //how many interval availabilities the user currently has left
    private int currentConsecIntervalCount; //how many consecutive intervals the user has served currently
    private Map<Integer, int[]> dayToUpcomingConsecIntervalArray; //each index holds the number of available intervals that follow it (inclusive). calculated per day
    private String myName;
    private final String[] days = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    //	public Tenter(List<Day> days) {
//		myDayList = days;
//	}
    public Tenter(String name) {
        //dayToObligationManager = new HashMap<>();
        currentDayNumber = 0;
        myName = name;
        currentConsecIntervalCount = 0;
        dayToUpcomingConsecIntervalArray = new HashMap<>();
        myAssignedIntervals = new HashMap<>();
        myAssignedIntervals.put(currentDayNumber, new ArrayList<Interval>());
        myObligationManagers = new ArrayList<>();
        this.calculateValues();
    }

    public static void setCurrentDay(int number) {
        currentDayNumber = number;
    }

    public static void setInterval(int i) {
        currentIntervalNumber = i;
    }

    public void printSchedule() {
        for(Integer date: myAssignedIntervals.keySet()) {
            System.out.println(days[date]);
            for (Interval i: myAssignedIntervals.get(date)) {
                System.out.println(i.toString());
            }
        }

    }

    /**
     * Calculates all of the instance variable values for the tenter
     * Makes calls to helper methods to do so
     * Called once all busy intervals have been added for all Days in myDayList
     */
    public void calculateValues() {
//		for (String day: dayToObligationManager.keySet()) {
//			numberOfAvailableIntervals += dayToObligationManager.get(day).calculateAvailableIntervals();
//			//dayToUpcomingConsecIntervalArray.put(day, dayToObligationManager.get(day).getConsecAvailability());
//		}
        for (ObligationManager om: myObligationManagers) {
            numberOfAvailableIntervals += om.calculateAvailableIntervals();
        }
    }
    /**
     *
     * @return the number of available intervals for the user for the rest of the days in myDayList
     */
    public int getNumAvailIntervals() {
        return numberOfAvailableIntervals;
    }
    /**
     *
     * @return the number of intervals the tenter has filled during this scheduling period
     */
    public int getNumFilledIntervals() {
        return myAssignedIntervals.get(currentDayNumber).size();
    }
    /**
     *
     * @return the number of consecutive intervals the tenter has served
     */
    public int getCurrentConsecIntervalCount() {
        return currentConsecIntervalCount;
    }
    /**
     *
     * @return the number of upcoming consecutive intervals (inclusive of current interval)
     */
    public int getUpcomingConsecutiveIntervals() {
        return dayToUpcomingConsecIntervalArray.get(currentDayNumber)[currentIntervalNumber];
    }

    public boolean isAssigned(int dayNumber, Interval interval){
        for (Interval i: this.myAssignedIntervals.get(dayNumber)){
            if (i.checkIfThisIntervalFallsWithin(interval)){
                return true;
            }
        }
        return false;
    }
    public void assignInterval(int dayNumber, Interval i) {
        if (myAssignedIntervals.containsKey(dayNumber)) {
            myAssignedIntervals.get(dayNumber).add(i);
            return;
        }
        List<Interval> newList = new ArrayList<>();
        newList.add(i);
        myAssignedIntervals.put(dayNumber, newList);
        getNumFilledIntervals();
    }

    public void addObligationManager(ObligationManager obligationManager) {
        myObligationManagers.add(obligationManager);
//		dayToObligationManager.put(day, obligationManager);
    }

    public boolean isAvailable() {
        return myObligationManagers.get(currentDayNumber).getAvailabilityStatus(currentIntervalNumber);
        //return dayToObligationManager.get(currentDayName).getAvailabilityStatus(currentIntervalNumber);
    }
    public boolean isAvailable(int day, int interval) {
        return myObligationManagers.get(day).getAvailabilityStatus(interval);
        //return dayToObligationManager.get(currentDayName).getAvailabilityStatus(currentIntervalNumber);
    }

    public String getName() {
        return myName;
    }

    public void initializeAssignmentMap() {
        myAssignedIntervals.put(currentDayNumber, new ArrayList<Interval>());
    }
}
