package ar.edu.utn.frba.dds.community;

import ar.edu.utn.frba.dds.user.User;

public class Member {
  User user;
  String role;

  public Member(User anUser, String role) {
    this.user = anUser;
    this.role = role;
  }

  public static Member composedBy(User anUser, String role) {
    return new Member(anUser, role);
  }

  public User user() {
    return this.user;
  }

  public String role() {
    return this.role;
  }

}
