package ar.edu.utn.frba.dds.service;

import java.util.ArrayList;

public class Elevator extends Service {
  public static Elevator composedOf(String name, String description, State state) {
    return new Elevator(name, description, state);
  }

  public Elevator(String name, String description, State state) {
    this.name = name;
    this.description = description;
    this.state = state;
    this.sections = new ArrayList<>();
  }
}
