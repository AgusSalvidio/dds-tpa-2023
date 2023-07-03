package ar.edu.utn.frba.dds.service;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Service {

  public String name;

  public String description;

  public State state;
  public List<Section> sections;

  public String name() {
    return this.name;
  }

  public State state() {
    return this.state;
  }

  public String description() {
    return this.description;
  }

  public void addNewSection(Section newSection) {
    this.sections.add(newSection);
  }

  public List<Section> sections() {
    return this.sections.stream().collect(Collectors.toList());
  }
}