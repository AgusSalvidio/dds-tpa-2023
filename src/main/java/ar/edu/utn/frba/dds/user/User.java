package ar.edu.utn.frba.dds.user;

import javax.persistence.CascadeType;
import ar.edu.utn.frba.dds.notification.Msg;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue
  Integer id;

  @Getter
  @Column(name = "username")
  String username;

  @Getter
  @Column(name = "password")
  String password;

  @Getter
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_detail_id", referencedColumnName = "id")
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

  public User() {
    //Do nothing -asalvidio
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

  public String telephone() {
    return this.details().telephone();
  }

  public void notifyMe(Msg message) {
    this.details.getNotificationMean().notify(message);
  }
}
