package my.com.exerciselogger.datasource;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import my.com.exerciselogger.domain.Exercise;
import my.com.exerciselogger.domain.Set;

/**
 * Created by buhduh on 3/1/15.
 */
public class SetDataSource extends BaseDataSource {
  public static final String TABLE_NAME = "set";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_EXERCISE_ID = "exercise_id";
  public static final String COLUMN_REPS = "reps";
  public static final String COLUMN_WEIGHT = "weight";
  //time will likely be changed
  public static final String COLUMN_TIME = "time";

  protected String[] mAllColumns = {
      COLUMN_ID,
      COLUMN_EXERCISE_ID,
      COLUMN_REPS,
      COLUMN_WEIGHT,
      COLUMN_TIME
  };

  public SetDataSource(Context context) {
    super(context);
  }

  public ArrayList<Set> getSetsByExerciseID(int exerciseID) {
    ArrayList<Set> sets = new ArrayList<Set>();
    if(exerciseID == 0) return sets;
    String[] selectionArgs = {String.valueOf(exerciseID)};
    Cursor cursor = mDatabase.query(
        TABLE_NAME,
        mAllColumns,
        COLUMN_EXERCISE_ID + "=?",
        selectionArgs,
        null,
        null,
        null
    );
    if(cursor.getCount() == 0) return sets;
    cursor.moveToFirst();
    while(!cursor.isAfterLast()) {
      Set set = cursorToSet(cursor);
      sets.add(set);
    }
    return sets;
  }

  private Set cursorToSet(Cursor cursor) {
    //ignoring time for now
    return new Set(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getFloat(3));
  }
}
