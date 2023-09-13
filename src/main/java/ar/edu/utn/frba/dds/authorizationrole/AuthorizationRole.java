package ar.edu.utn.frba.dds.authorizationrole;

import ar.edu.utn.frba.dds.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "authorization_role")
public class AuthorizationRole {
  @Id
  @GeneratedValue
  Integer id;

  @Getter
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  User user;
  @Getter
  @Column(name = "role")
  String role;

  public AuthorizationRole(User anUser, String role) {
    this.user = anUser;
    this.role = role;
  }

  public AuthorizationRole() {
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

  public void synchronizeWith(AuthorizationRole updatedAuthorizationRole) {
    this.user = updatedAuthorizationRole.user();
    this.role = updatedAuthorizationRole.role();
  }
}