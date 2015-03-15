package my.com.exerciselogger.datasource;

import android.content.Context;

/**
 * Created by buhduh on 3/1/15.
 */
public class CategoryFactDataSource extends BaseDataSource {
  public static final String TABLE_NAME = "category_fact";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_CATEGORY_ID = "category_id";
  public static final String COLUMN_BASE_ID = "base_id";

  public CategoryFactDataSource(Context context) {
    super(context);
  }
}
