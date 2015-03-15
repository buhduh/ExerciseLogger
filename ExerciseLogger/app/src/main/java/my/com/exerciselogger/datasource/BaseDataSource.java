package my.com.exerciselogger.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by buhduh on 3/3/15.
 */
public abstract class BaseDataSource {
  protected SQLiteDatabase mDatabase;
  protected MySQLiteHelper mDBHelper;
  protected CommonDataSource mCommonDataSource;
  protected String[] mAllColumns;

  public BaseDataSource(Context context) {
    mDBHelper = new MySQLiteHelper(context);
    mCommonDataSource = new CommonDataSource();
  }

  public void open() throws SQLException {
    mDatabase = mDBHelper.getWritableDatabase();
  }

  public void close() {
    mDBHelper.getWritableDatabase();
  }
}
