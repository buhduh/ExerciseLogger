package my.com.exerciselogger.datasource;

import android.content.Context;
import android.database.Cursor;

import my.com.exerciselogger.domain.Day;

/**
 * Created by buhduh on 3/1/15.
 */
public class DayDataSource extends BaseDataSource {
  public static final String TABLE_NAME = "day";
  //unix time stamp for ease w/ sqlite time() operations
  public static final String COLUMN_ID = "_id";

  public DayDataSource(Context context) {
    super(context);
  }

  public Long getDayID(Long timeStamp) {
    String[] columns = {COLUMN_ID};
    String[] selectionArgs = {timeStamp.toString()};
    Cursor cursor = mDatabase.query(
        TABLE_NAME,
        columns,
        COLUMN_ID+"=?",
        selectionArgs,
        null,
        null,
        null
    );
    if(cursor.getCount() == 0) return null;
    //I should check to make sure count is one here...
    cursor.moveToFirst();
    return cursor.getLong(0);
  }
}
