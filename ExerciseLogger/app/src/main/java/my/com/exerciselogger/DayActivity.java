package my.com.exerciselogger;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import my.com.exerciselogger.datasource.DayLoader;
import my.com.exerciselogger.domain.Day;

public class DayActivity extends ActionBarActivity implements
    LoaderManager.LoaderCallbacks<Day> {

  public static final String UNIX_TIME_STAMP_KEY = "unixTimeStampKeyForDayActivity";
  private static final int LOADER_ID = 1;
  private Day mDay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_day);
    Intent intent  = getIntent();
    Bundle bundle = intent.getExtras();
    //long timeStamp = bundle.getLong(UNIX_TIME_STAMP_KEY);
    getSupportLoaderManager().initLoader(LOADER_ID, bundle, this).forceLoad();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_day, menu);
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

  @Override
  public Loader<Day> onCreateLoader(int id, Bundle args) {
    Loader<Day> loader = new DayLoader(this, args.getLong(UNIX_TIME_STAMP_KEY));
    return loader;
  }

  @Override
  public void onLoadFinished(Loader<Day> dayLoader, Day day) {

  }

  @Override
  public void onLoaderReset(Loader<Day> dayLoader) {

  }

}
