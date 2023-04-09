package ar.edu.utn.frba.dds.user;
import ar.edu.utn.frba.dds.userDetails.UserDetails;

public class User {
  String username;
  String password;
  UserDetails details;

  public User(String aUsername,String aPassword,UserDetails aUserDetails){
    this.username = aUsername;
    this.password = aPassword;
    this.details = aUserDetails;
  }

  public String username(){
    return username;
  }
  public String password(){
    return password;
  }
  private UserDetails details(){
    return details;
  }
  public String name(){
    return this.details().name();
  }
  public String lastname(){
    return this.details().lastname();
  }
  public String email(){
    return this.details().email();
  }

}
