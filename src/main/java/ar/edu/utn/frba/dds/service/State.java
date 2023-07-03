package ar.edu.utn.frba.dds.service;

public class State {
  public String name;
  public String description;

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