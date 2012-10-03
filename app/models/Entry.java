package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class Entry extends Model {
 
  public String title;
  public String content;
    
  public Entry(String title, String content) {
    this.title = title;
    this.content = content;
  }
}