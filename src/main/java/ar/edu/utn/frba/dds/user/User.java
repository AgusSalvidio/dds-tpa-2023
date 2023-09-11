package ar.edu.utn.frba.dds.user;

import lombok.Getter;

public class User {
  @Getter
  String username;
  String password;
  @Getter
  UserDetail details;

  public static User composedOf(String username, String password, UserDetail userDetail)
      throws Exception {
    /*
        Implemented this way because its needed an AssertionChecker that will be implemented
        in another issue later on. Also should be necessary to specify the field thats empty.
      */
    if (username.isEmpty() || password.isEmpty()) {
      throw new Exception("Los campos no pueden estar en blanco.");
    }
    return new User(username, password, userDetail);

  }

  public User(String username, String password, UserDetail userDetail) {
    this.username = username;
    this.password = password;
    this.details = userDetail;
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

  private UserDetail details() {
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
