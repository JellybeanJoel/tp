package seedu.duke.model;

import java.util.ArrayList;

public class ActivityList {
    private final ArrayList<Activity> activities;
    private Trip trip;

    public ActivityList(Trip trip){
        this.trip = trip;
        activities = new ArrayList<>();
    }

    public void add(Activity a) {
        activities.add(a);
    }

    public Activity remove(int index) {
        return activities.remove(index);
    }

    public Activity get(int index) {
        return activities.get(index);
    }

    public int size() {
        return activities.size();
    }

    public boolean isEmpty() {
        return activities.isEmpty();
    }

    public boolean isTripOpen() {
        return trip.isOpen();
    }

    public ArrayList<Activity> findActivities(String keyword) {
        ArrayList<Activity> matchingActivities = new ArrayList<>();
        for (Activity a: activities){
            if (a.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingActivities.add(a);
            }
        }
        return matchingActivities;
    }
}
