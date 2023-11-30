package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.notification.Msg;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue
  Integer id;

  @Column(name = "username")
  String username;

  @Column(name = "password")
  String password;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_detail_id", referencedColumnName = "id")
  UserDetail details;

  @Column(name = "authorization_role")
  @Enumerated(EnumType.STRING)
  AuthorizationRole authorizationRole;

  public static User composedOf(
      String username,
      String password,
      UserDetail userDetail,
      AuthorizationRole authorizationRole)

      throws Exception {
    /*
        Implemented this way because its needed an AssertionChecker that will be implemented
        in another issue later on. Also should be necessary to specify the field thats empty.
      */
    if (username.isEmpty() || password.isEmpty()) {
      throw new Exception("Los campos no pueden estar en blanco.");
    }
    return new User(username, password, userDetail, authorizationRole);

  }

  public User() {
    //Sobrecarga para que no rompa Hibernate
  }

  public User(
      String username,
      String password,
      UserDetail userDetail,
      AuthorizationRole authorizationRole) {

    this.username = username;
    this.password = password;
    this.details = userDetail;
    this.authorizationRole = authorizationRole;
  }

  public void synchronizeWith(User anUpdatedUser) {
    this.username = anUpdatedUser.username();
    this.password = anUpdatedUser.password();
    this.details = anUpdatedUser.details();
    this.authorizationRole = anUpdatedUser.authorizationRole();
  }

  public String username() {
    return username;
  }

  public AuthorizationRole authorizationRole() {
    return authorizationRole;
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

  public String telephone() {
    return this.details().telephone();
  }

  public void notifyMe(Msg message) {
    this.details.getNotificationMean().notify(message);
  }
}
