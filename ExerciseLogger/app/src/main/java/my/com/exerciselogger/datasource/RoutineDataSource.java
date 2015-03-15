package my.com.exerciselogger.datasource;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import my.com.exerciselogger.domain.Routine;

/**
 * Created by buhduh on 3/1/15.
 */
public class RoutineDataSource extends BaseDataSource {
  public static final String TABLE_NAME = "routine";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_DAY_ID = "day_id";

  protected String[] mAllColumns = {
      COLUMN_ID, COLUMN_DAY_ID
  };

  public RoutineDataSource(Context context) {
    super(context);
  }

  private Routine cursorToRoutine(Cursor cursor) {
    return new Routine(cursor.getLong(1), cursor.getInt(0));
  }

  public ArrayList<Routine> getRoutinesByDayID(Long dayID) {
    ArrayList<Routine> routines = new ArrayList<Routine>();
    if(dayID == null) return routines;
    String[] selectionArgs = {dayID.toString()};
    Cursor cursor = mDatabase.query(
        TABLE_NAME,
        mAllColumns,
        COLUMN_DAY_ID + "=?",
        selectionArgs,
        null,
        null,
        null
    );
    if(cursor.getCount() == 0) return routines;
    cursor.moveToFirst();
    while(!cursor.isAfterLast()) {
      Routine routine = cursorToRoutine(cursor);
      routines.add(routine);
    }
    return routines;
  }
}
