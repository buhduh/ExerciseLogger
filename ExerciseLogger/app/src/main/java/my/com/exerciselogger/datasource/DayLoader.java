package my.com.exerciselogger.datasource;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.LruCache;

import my.com.exerciselogger.domain.Day;
import my.com.exerciselogger.domain.DayFactory;

/**
 * Created by buhduh on 3/10/15.
 */
public class DayLoader extends AsyncTaskLoader<Day> {

  Context mContext;
  Long mTimeStamp;

  public DayLoader(Context context, Long timeStamp) {
    super(context);
    mContext = context;
    mTimeStamp = timeStamp;
  }

  @Override
  public Day loadInBackground() {
    return DayFactory.getDay(mTimeStamp, mContext);
  }



}
