package ar.edu.utn.frba.dds.authorizationrole;

import ar.edu.utn.frba.dds.user.User;

public class AuthorizationRole {
  User user;
  String role;

  public AuthorizationRole(User anUser, String role) {
    this.user = anUser;
    this.role = role;
  }

  public static AuthorizationRole composedOf(User anUser, String role) {
    return new AuthorizationRole(anUser, role);
  }

  public User user() {
    return this.user;
  }

  public String role() {
    return this.role;
  }
}
