package my.com.exerciselogger.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by buhduh on 3/1/15.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "exercise_logger.db";
  private static final int DATABASE_VERSION = 1;

  private static final String CREATE_CATEGORIES = "" +
      "create table " + CategoriesDataSource.TABLE_NAME + "(" +
        CategoriesDataSource.COLUMN_ID + " integer primary key autoincrement, " +
        CategoriesDataSource.COLUMN_CATEGORY + " text not null, " +
        "unique (" + CategoriesDataSource.COLUMN_CATEGORY + ");";

  private static final String CREATE_CATEGORY_FACT = "" +
      "create table " + CategoryFactDataSource.TABLE_NAME + "(" +
        CategoryFactDataSource.COLUMN_ID + " integer primary key autoincrement, " +
        CategoryFactDataSource.COLUMN_BASE_ID + " integer, " +
        CategoryFactDataSource.COLUMN_CATEGORY_ID + " integer, " +
        " foreign key (" + CategoryFactDataSource.COLUMN_BASE_ID +") references " +
          CommonDataSource.TABLE_NAME + "(" + CommonDataSource.COLUMN_ID + ")" +
        " foreign key (" + CategoryFactDataSource.COLUMN_CATEGORY_ID + ") references " +
          CategoriesDataSource.TABLE_NAME + "(" + CategoriesDataSource.COLUMN_ID + "));";

  private static final String CREATE_COMMON = "" +
      "create table " + CommonDataSource.TABLE_NAME + "(" +
        CommonDataSource.COLUMN_ID + " integer primary key autoincrement, " +
        CommonDataSource.COLUMN_TYPE + " text not null, " +
        CommonDataSource.COLUMN_TYPE_ID + " integer not null, " +
        CommonDataSource.COLUMN_NOTES + " text, " +
        CommonDataSource.COLUMN_TITLE + " text not null" +
        CommonDataSource.COLUMN_ORDER + " integer default 0);";

  private static final String CREATE_SET = "" +
      "create table " + SetDataSource.TABLE_NAME + "(" +
        SetDataSource.COLUMN_ID + " integer primary key autoincrement, " +
        SetDataSource.COLUMN_EXERCISE_ID + " integer, " +
        SetDataSource.COLUMN_REPS + " integer, " +
        SetDataSource.COLUMN_WEIGHT + " integer, " +
        SetDataSource.COLUMN_TIME + " text, " +
        " foreign key (" + SetDataSource.COLUMN_EXERCISE_ID + ") references " +
          ExerciseDataSource.TABLE_NAME + "(" + ExerciseDataSource.COLUMN_ID +"));";

  private static final String CREATE_EXERCISE = "" +
      "create table " + ExerciseDataSource.TABLE_NAME + "(" +
        ExerciseDataSource.COLUMN_ID + " integer primary key autoincrement, " +
        ExerciseDataSource.COLUMN_ROUTINE_ID + " integer, " +
        " foreign key (" + ExerciseDataSource.COLUMN_ROUTINE_ID + ") references " +
          RoutineDataSource.TABLE_NAME + "(" + RoutineDataSource.COLUMN_ID + "));";

  private static final String CREATE_ROUTINE = "" +
      "create table " + RoutineDataSource.TABLE_NAME + "(" +
        RoutineDataSource.COLUMN_ID + " integer primary key autoincrement, " +
        RoutineDataSource.COLUMN_DAY_ID + " integer, " +
        " foreign key (" + RoutineDataSource.COLUMN_DAY_ID  + ") references " +
          DayDataSource.TABLE_NAME + "(" + DayDataSource.COLUMN_ID + "));";

  private static final String CREATE_DAY = "" +
      "create table " + DayDataSource.TABLE_NAME + "(" +
        DayDataSource.COLUMN_ID + " integer primary key)";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_DAY);
    db.execSQL(CREATE_ROUTINE);
    db.execSQL(CREATE_EXERCISE);
    db.execSQL(CREATE_SET);
    db.execSQL(CREATE_COMMON);
    db.execSQL(CREATE_CATEGORIES);
    db.execSQL(CREATE_CATEGORY_FACT);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //obviously, a more intelligent upgrade will be needed so I don't dump user's data
    db.execSQL("drop table if exists " + CategoryFactDataSource.TABLE_NAME);
    db.execSQL("drop table if exists " + CategoriesDataSource.TABLE_NAME);
    db.execSQL("drop table if exists " + CommonDataSource.TABLE_NAME);
    db.execSQL("drop table if exists " + SetDataSource.TABLE_NAME);
    db.execSQL("drop table if exists " + ExerciseDataSource.TABLE_NAME);
    db.execSQL("drop table if exists " + RoutineDataSource.TABLE_NAME);
    db.execSQL("drop table if exists " + DayDataSource.TABLE_NAME);
  }
}
