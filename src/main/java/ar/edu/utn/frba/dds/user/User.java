package ar.edu.utn.frba.dds.user;
import ar.edu.utn.frba.dds.userDetails.UserDetails;

public class User {
  String username;
  String password;
  UserDetails details;

  public static User composedBy(String aUsername,String aPassword,UserDetails aUserDetails)throws Exception{
    /*
        Implemented this way because its needed an AssertionChecker class that will be implemented
        in another issue later on. Also should be necesary to specify the field thats empty -asalvidio
      */
    if(aUsername.isEmpty() || aPassword.isEmpty())
      throw new Exception("Los campos no pueden estar en blanco.");
    return new User(aUsername,aPassword,aUserDetails);

  }
  public User(String aUsername,String aPassword,UserDetails aUserDetails){
    this.username = aUsername;
    this.password = aPassword;
    this.details = aUserDetails;
  }

  public String username(){
    return username;
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
