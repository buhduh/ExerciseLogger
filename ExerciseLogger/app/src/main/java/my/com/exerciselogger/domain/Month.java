package my.com.exerciselogger.domain;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import my.com.exerciselogger.DayActivity;
import my.com.exerciselogger.R;

/**
 * Created by buhduh on 2/26/15.
 */
public class Month {

  private static final String TAG = "Month";

  private MonthUI mMonthUI;
  private String mMonthString;
  private int mYear;
  private int mMonth;

  public Month(int month, int year) {
    initMonthString(month);
    mYear = year;
    mMonth = month;
  }

  public Month(int month, int year, View view) {
    initMonthString(month);
    mYear = year;
    mMonth = month;
    mMonthUI = new MonthUI(view);
  }

  private void initMonthString(int month) {
    DateFormatSymbols dfs = new DateFormatSymbols();
    String[] months = dfs.getMonths();
    mMonthString = months[month];
  }

  public String getMonthString() {
    return mMonthString;
  }

  public int getYear() {
    return mYear;
  }

  private class MonthUI {

    private View mView;
    private int mFirstDate;
    private int mLastDate;

    public MonthUI(View view) {
      mView = view;
      initView();
    }

    private View[] getWeeks() {
      View weeks[] = {
          (LinearLayout) mView.findViewById(R.id.week1),
          (LinearLayout) mView.findViewById(R.id.week2),
          (LinearLayout) mView.findViewById(R.id.week3),
          (LinearLayout) mView.findViewById(R.id.week4),
          (LinearLayout) mView.findViewById(R.id.week5)
      };
      return weeks;
    }

    private void setWeek(View view, GregorianCalendar cal) {
      TextView day;
      day = (TextView) view.findViewById(R.id.weekSunday);
      day.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
      day.setOnClickListener(new DayClickListener(cal));
      cal.add(Calendar.DATE, 1);
      day = (TextView) view.findViewById(R.id.weekMonday);
      day.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
      day.setOnClickListener(new DayClickListener(cal));
      cal.add(Calendar.DATE, 1);
      day = (TextView) view.findViewById(R.id.weekTuesday);
      day.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
      day.setOnClickListener(new DayClickListener(cal));
      cal.add(Calendar.DATE, 1);
      day = (TextView) view.findViewById(R.id.weekWednesday);
      day.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
      day.setOnClickListener(new DayClickListener(cal));
      cal.add(Calendar.DATE, 1);
      day = (TextView) view.findViewById(R.id.weekThursday);
      day.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
      day.setOnClickListener(new DayClickListener(cal));
      cal.add(Calendar.DATE, 1);
      day = (TextView) view.findViewById(R.id.weekFriday);
      day.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
      day.setOnClickListener(new DayClickListener(cal));
      cal.add(Calendar.DATE, 1);
      day = (TextView) view.findViewById(R.id.weekSaturday);
      day.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
      day.setOnClickListener(new DayClickListener(cal));
      cal.add(Calendar.DATE, 1);
    }

    private void initView() {
      TextView monthTitle = (TextView) mView.findViewById(R.id.monthTitle);
      monthTitle.setText(mMonthString + " " + mYear);
      GregorianCalendar cal = new GregorianCalendar(mYear, mMonth, 1);
      cal.clear(Calendar.MILLISECOND);
      cal.clear(Calendar.SECOND);
      cal.clear(Calendar.MINUTE);
      cal.clear(Calendar.HOUR);
      cal.setFirstDayOfWeek(Calendar.SUNDAY);
      if((int) cal.getFirstDayOfWeek() != cal.get(Calendar.DAY_OF_WEEK)) {
        while((int) cal.getFirstDayOfWeek() != cal.get(Calendar.DAY_OF_WEEK)) {
          cal.add(Calendar.DATE, -1);
        }
      }
      mFirstDate = cal.get(Calendar.DATE);
      View[] weeks = getWeeks();
      setWeek(weeks[0], cal);
      setWeek(weeks[1], cal);
      setWeek(weeks[2], cal);
      setWeek(weeks[3], cal);
      setWeek(weeks[4], cal);
      cal.add(Calendar.DATE, -1);
      mLastDate = cal.get(Calendar.DATE);
    }
    private class DayClickListener implements View.OnClickListener {
      private long mTimeStamp;
      public DayClickListener(GregorianCalendar cal) {
        super();
        mTimeStamp = cal.getTimeInMillis() / 1000;
      }
      @Override
      public void onClick(View v) {
        Intent i = new Intent(mView.getContext(), DayActivity.class);
        Bundle args = new Bundle();
        args.putLong(DayActivity.UNIX_TIME_STAMP_KEY, mTimeStamp);
        i.putExtras(args);
        mView.getContext().startActivity(i);
      }
    }
  }
}