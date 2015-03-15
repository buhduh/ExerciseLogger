package my.com.exerciselogger.domain;

/**
 * Created by buhduh on 3/1/15.
 */
public class Set extends BaseExerciseGroup {

  private int mReps;
  //kg or lbs?
  private float mWeight;
  private int mID;
  private int mExerciseID;

  public Set(int id, int exerciseID, int reps, float weight) {
    mID = id;
    mExerciseID = exerciseID;
    mReps = reps;
    mWeight = weight;
  }
}
