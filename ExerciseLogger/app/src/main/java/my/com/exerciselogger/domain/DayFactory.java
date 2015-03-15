package my.com.exerciselogger.domain;

import android.content.Context;
import android.util.LruCache;

import java.util.ArrayList;

import my.com.exerciselogger.datasource.DayDataSource;
import my.com.exerciselogger.datasource.ExerciseDataSource;
import my.com.exerciselogger.datasource.RoutineDataSource;
import my.com.exerciselogger.datasource.SetDataSource;

/**
 * Created by buhduh on 3/10/15.
 */
public final class DayFactory {

  private static DayCache mDayCache;
  private static final int MAX_CACHE_SIZE = 90;

  private DayFactory() {}

  public static Day getDay(Long timeStamp, Context context) {
    if(mDayCache == null) mDayCache = new DayCache(MAX_CACHE_SIZE, context);
    return mDayCache.get(timeStamp);
  }

  private static final class DayCache extends LruCache<Long, Day> {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    private Context mContext;
    private DayDataSource mDayDataSource;
    private RoutineDataSource mRoutineDataSource;
    private ExerciseDataSource mExerciseDataSource;
    private SetDataSource mSetDataSource;

    public DayCache(int maxSize, Context context) {
      super(maxSize);
      mContext = context;
      mDayDataSource = new DayDataSource(mContext);
      mRoutineDataSource = new RoutineDataSource(mContext);
      mExerciseDataSource = new ExerciseDataSource(mContext);
      mSetDataSource = new SetDataSource(mContext);
    }

    @Override
    protected Day create(Long timeStamp) {
      Long dayID = mDayDataSource.getDayID(timeStamp);
      //todo, notes, categories
      ArrayList<Routine> routines;
      routines = mRoutineDataSource.getRoutinesByDayID(dayID);
      for(Routine routine : routines) {
        initExercises(routine);
      }
      Day day = new Day(timeStamp);
      day.clearRoutines();
      day.addRoutines(routines);
      return day;
    }

    private void initExercises(Routine routine) {
      ArrayList<Exercise> exercises;
      exercises = mExerciseDataSource.getExercisesByRoutineID(routine.getID());
      routine.clearExercises();
      routine.addExercises(exercises);
      for(Exercise exercise: exercises) {
        initSets(exercise);
      }
    }

    private void initSets(Exercise exercise) {
      ArrayList<Set> sets;
      sets = mSetDataSource.getSetsByExerciseID(exercise.getID());
      exercise.clearSets();
      exercise.addSets(sets);
    }
  }
}
