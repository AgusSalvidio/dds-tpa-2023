package ar.edu.utn.frba.dds.service;

import java.util.ArrayList;

public class Toilet extends Service {
  public static Toilet composedOf(String name, String description, State state) {
    return new Toilet(name, description, state);
  }

  public Toilet(String name, String description, State state) {
    this.name = name;
    this.description = description;
    this.sections = new ArrayList<>();
  }
}