package ar.edu.utn.frba.dds.service;

import java.util.ArrayList;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("toilet")
public class Toilet extends Service {


  public static Toilet composedOf(String name, String description, State state) {
    return new Toilet(name, description, state);
  }

  public Toilet() {
  }

  public Toilet(String name, String description, State state) {
    this.name = name;
    this.description = description;
    this.state = state;
    this.sections = new ArrayList<>();
  }
}