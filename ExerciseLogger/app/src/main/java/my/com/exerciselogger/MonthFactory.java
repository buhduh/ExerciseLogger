package my.com.exerciselogger;

import android.view.View;

import my.com.exerciselogger.domain.Month;

/**
 * Created by buhduh on 2/27/15.
 */
public class MonthFactory {

  //singleton, disable constructor
  private MonthFactory(){}

  public static Month getMonth(int month, int year) {
    return new Month(month, year);
  }

  public static Month getMonth(int month, int year, View view) {
    return new Month(month, year, view);
  }
}
