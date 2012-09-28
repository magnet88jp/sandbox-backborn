package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class Account extends Model {
 
  @Email
  @Required
  public String email;

  @Password
  @Required
  public String password;

  public String fullname;
  public boolean isAdmin;
    
  public Account(String email, String password, String fullname) {
    this.email = email;
    this.password = password;
    this.fullname = fullname;
  }

  public static Account connect(String email, String password) {
    return find("byEmailAndPassword", email, password).first();
  }
}