package my.com.exerciselogger.domain;

/**
 * Created by buhduh on 3/1/15.
 */
public interface ExerciseGroup {
  public String[] getCategories();
  public String getNotes();
  public String getTitle();

  public void addCategory(String category);
  public void removeCategory(String Category);
  public void setNotes(String notes);
  public void setTitle(String title);
}
