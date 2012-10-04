package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class Sentence extends Model {
 
  public String name;
  public String email;
    
  public Sentence(String name, String email) {
    this.name = name;
    this.email = email;
  }
}