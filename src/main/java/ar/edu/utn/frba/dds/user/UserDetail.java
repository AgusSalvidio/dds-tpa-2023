package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.service.Service;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;

@javax.persistence.Entity
@Table(name = "user_detail")
public class UserDetail {
  @Id
  @GeneratedValue
  Integer id;

  @Getter
  @Column(name = "name")
  String name;
  @Getter
  @Column(name = "lastname")
  String lastname;
  @Getter
  @Column(name = "email")
  String email;

  @Transient
  UserPreference userPreference;

  public UserDetail(String name, String lastname, String anEmail) {
    this.name = name;
    this.lastname = lastname;
    this.email = anEmail;
    this.userPreference = new UserPreference();
  }

  public UserDetail() {
    //Do nothing -asalvidio
  }

  public String name() {
    return this.name;
  }

  public String lastname() {
    return this.lastname;
  }

  public String email() {
    return this.email;
  }

  public static UserDetail composedOf(String name, String lastname, String anEmail)
      throws Exception {
    /*
        Implemented this way because its needed an AssertionChecker class that will be implemented
        in another issue later on. Also should be necesary to specify the field thats empty
     */
    if (name.isEmpty() || lastname.isEmpty() || anEmail.isEmpty()) {
      throw new Exception("Los campos no pueden estar en blanco.");
    }
    return new UserDetail(name, lastname, anEmail);
  }

  public List<Service> services() {
    return this.userPreference.services();
  }

  public List<Entity> entities() {
    return this.userPreference.entities();
  }
}
