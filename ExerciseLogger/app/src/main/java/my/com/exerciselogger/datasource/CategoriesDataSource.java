package my.com.exerciselogger.datasource;

import android.content.Context;

/**
 * Created by buhduh on 3/1/15.
 */
public class CategoriesDataSource extends BaseDataSource {

  public static final String TABLE_NAME = "categories";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_CATEGORY = "category";

  public CategoriesDataSource(Context context) {
    super(context);
  }

}
