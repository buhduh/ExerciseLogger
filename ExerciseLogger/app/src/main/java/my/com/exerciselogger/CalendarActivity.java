package my.com.exerciselogger;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import my.com.exerciselogger.domain.Month;

public class CalendarActivity extends FragmentActivity {

  private static final String TAG = "CalendarActivity";

  private ViewPager mViewPager;
  private MonthPagerAdapter mMonthPagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calendar);
    //TODO savedInstance
    mMonthPagerAdapter = new MonthPagerAdapter(getSupportFragmentManager());
    mViewPager = (ViewPager) findViewById(R.id.monthPager);
    mViewPager.setAdapter(mMonthPagerAdapter);
    mViewPager.setCurrentItem(MonthPagerAdapter.START_INDEX);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_planner, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public class MonthPagerAdapter extends FragmentStatePagerAdapter {

    public static final int NUM_PAGES = 5001;
    public static final int START_INDEX = 2500;

    public MonthPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int i) {
      int offSet = i - START_INDEX;
      Fragment fragment = new CalendarObjectFragment();
      Bundle args = new Bundle();
      args.putInt(CalendarObjectFragment.OFFSET_KEY, offSet);
      fragment.setArguments(args);
      return fragment;
    }

    @Override
    public int getCount() {
      return NUM_PAGES;
    }
  }

  public static class CalendarObjectFragment extends Fragment {

    public static final String OFFSET_KEY = "indexKey";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.month, container, false);
      Bundle args = getArguments();
      int offset = args.getInt(OFFSET_KEY);
      GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
      calendar.add(Calendar.MONTH, offset);
      int monthInt = calendar.get(Calendar.MONTH);
      int year = calendar.get(Calendar.YEAR);
      Month month = MonthFactory.getMonth(monthInt, year, rootView);
      return rootView;
    }
  }
}
