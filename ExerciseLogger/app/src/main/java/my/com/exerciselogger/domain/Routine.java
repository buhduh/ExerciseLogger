package my.com.exerciselogger.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by buhduh on 3/1/15.
 */
public class Routine extends BaseExerciseGroup {

  private ArrayList<Exercise> mExercises;
  private Date mDate;
  //I dont really like saving the column id here, but I need a future reference to save
  private int mID;

  public Routine(Long timeStamp, int id) {
    mDate = new Date(timeStamp);
    mExercises = new ArrayList<Exercise>();
    mID = id;
  }

  public void addExercise(Exercise exercise) {
    mExercises.add(exercise);
  }

  public ArrayList<Exercise> getRoutines() {
    return mExercises;
  }

  public int getID() {return mID;}

  public void clearExercises() {
    mExercises.clear();
  }

  public void addExercises(ArrayList<Exercise> exercises) {
    mExercises.addAll(exercises);
  }
}
