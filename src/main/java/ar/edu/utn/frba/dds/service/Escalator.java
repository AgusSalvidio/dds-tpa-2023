package ar.edu.utn.frba.dds.service;

import java.util.ArrayList;

public class Escalator extends Service {
  public static Escalator composedOf(String name, String description, State state) {
    return new Escalator(name, description, state);
  }

  public Escalator(String name, String description, State state) {
    this.name = name;
    this.description = description;
    this.sections = new ArrayList<>();
  }
}
