package my.com.exerciselogger.datasource;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import my.com.exerciselogger.domain.Exercise;
import my.com.exerciselogger.domain.Routine;

/**
 * Created by buhduh on 3/1/15.
 */
public class ExerciseDataSource extends BaseDataSource {
  public static final String TABLE_NAME = "exercise";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_ROUTINE_ID = "routine_id";

  protected final String[] mAllColumns = {COLUMN_ID, COLUMN_ROUTINE_ID};

  public ExerciseDataSource(Context context) {
    super(context);
  }

  public ArrayList<Exercise> getExercisesByRoutineID(int routineID) {
    ArrayList<Exercise> exercises = new ArrayList<Exercise>();
    if(routineID == 0) return exercises;
    String[] selectionArgs = {String.valueOf(routineID)};
    Cursor cursor = mDatabase.query(
        TABLE_NAME,
        mAllColumns,
        COLUMN_ROUTINE_ID + "=?",
        selectionArgs,
        null,
        null,
        null
    );
    if(cursor.getCount() == 0) return exercises;
    cursor.moveToFirst();
    while(!cursor.isAfterLast()) {
      Exercise exercise = cursorToExercise(cursor);
      exercises.add(exercise);
    }
    return exercises;
  }

  private Exercise cursorToExercise(Cursor cursor) {
    return new Exercise(cursor.getInt(0), cursor.getInt(1));
  }
}
