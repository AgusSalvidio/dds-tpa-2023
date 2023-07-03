package ar.edu.utn.frba.dds.service;

public class Section {

  public String name;

  public static Section named(String name) {
    return new Section(name);
  }

  public Section(String name) {
    this.name = name;
  }

  public String name() {
    return this.name;
  }

}
