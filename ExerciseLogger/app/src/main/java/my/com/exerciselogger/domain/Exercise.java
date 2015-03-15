package my.com.exerciselogger.domain;

import java.util.ArrayList;

/**
 * Created by buhduh on 3/1/15.
 */
public class Exercise extends BaseExerciseGroup {

  private ArrayList<Set> mSets;
  private int mID;
  private int mRoutineID;

  public Exercise(int id, int routineID) {
    mID = id;
    mRoutineID = routineID;
    mSets = new ArrayList<Set>();
  }

  public int getID() {return mID;}

  public void clearSets() {mSets.clear();}

  public void addSets(ArrayList<Set> sets) {
    mSets.addAll(sets);
  }
}
