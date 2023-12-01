package ar.edu.utn.frba.dds.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_state")
@Getter
@Setter
public class State {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(name = "name")
  public String name;

  @Column(name = "description")
  public String description;

  public State() {
    //Sobrecarga para que no rompa Hibernate
  }

  public static State composedOf(String name, String description) {
    return new State(name, description);
  }

  public State(String name, String description) {
    this.name = name;
    this.description = description;
  }

}