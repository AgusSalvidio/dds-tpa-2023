package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.userdetails.UserDetails;

public class User {
  String username;
  String password;
  UserDetails details;

  /**
   * Another instance creation method but can make assertions.
   */
  public static User composedBy(String username, String password, UserDetails userDetails)
      throws Exception {
    /*
        Implemented this way because its needed an AssertionChecker that will be implemented
        in another issue later on. Also should be necessary to specify the field thats empty.
      */
    if (username.isEmpty() || password.isEmpty()) {
      throw new Exception("Los campos no pueden estar en blanco.");
    }
    return new User(username, password, userDetails);

  }

  /**
   * User class composed with username,password.
   */
  public User(String username, String password, UserDetails userDetails) {
    this.username = username;
    this.password = password;
    this.details = userDetails;
  }

  public void synchronizeWith(User anUpdatedUser) {
    this.username = anUpdatedUser.username();
    this.password = anUpdatedUser.password();
    this.details = anUpdatedUser.details();
  }

  public String username() {
    return username;
  }

  private String password() {
    return password;
  }

  private UserDetails details() {
    return details;
  }

  public String name() {
    return this.details().name();
  }

  public String lastname() {
    return this.details().lastname();
  }

  public String email() {
    return this.details().email();
  }

}
