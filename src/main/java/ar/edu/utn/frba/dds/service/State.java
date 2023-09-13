package ar.edu.utn.frba.dds.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "state")
public class State {
  @Id
  @GeneratedValue
  Integer id;

  @Getter
  @Column(name = "name")
  public String name;

  @Getter
  @Column(name = "description")
  public String description;

  public State() {
    //Do nothing -FedericoFuentesWeber
  }

  public static State composedOf(String name, String description) {
    return new State(name, description);
  }

  public State(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String name() {
    return this.name;
  }

  public String description() {
    return this.description;
  }
}