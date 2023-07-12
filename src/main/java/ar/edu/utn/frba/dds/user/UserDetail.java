package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.service.Service;
import java.util.List;

public class UserDetail {
  String name;
  String lastname;
  String email;

  UserPreferences userPreferences;

  public UserDetail(String name, String lastname, String anEmail) {
    this.name = name;
    this.lastname = lastname;
    this.email = anEmail;
    this.userPreferences = new UserPreferences();
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
    return this.userPreferences.services();
  }

  public List<Entity> entities() {
    return this.userPreferences.entities();
  }
}
