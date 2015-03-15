package my.com.exerciselogger.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by buhduh on 3/1/15.
 */
public abstract class BaseExerciseGroup implements ExerciseGroup {

  private String mNotes;
  private String mTitle;
  private HashMap<Integer, String> mCategories;

  public String getNotes() {
    return mNotes;
  }
  public String getTitle() {
    return mTitle;
  }
  public String[] getCategories() {
    List temp = new ArrayList(mCategories.values());
    Collections.sort(temp);
    String[] categories = new String[mCategories.size()];
    categories = (String[]) temp.toArray(categories);
    return categories;
  }

  public void setNotes(String notes) {
    mNotes = notes;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  public void addCategory(String category) {
    mCategories.put(new Integer(category.hashCode()), category);
  }

  public void removeCategory(String category) {
    mCategories.remove(new Integer(category.hashCode()));
  }
}
