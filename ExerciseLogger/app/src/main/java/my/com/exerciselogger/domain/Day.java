package my.com.exerciselogger.domain;

import java.util.ArrayList;

/**
 * Created by buhduh on 3/1/15.
 */
public class Day extends BaseExerciseGroup {

  ArrayList<Routine> mRoutines;
  Long mTimeStamp;

  public Day(Long dayTimeStamp) {
    mTimeStamp = dayTimeStamp;
  }

  public void clearRoutines() {mRoutines.clear();}

  public void addRoutines(ArrayList<Routine> routines) {
    mRoutines.addAll(routines);
  }
}
