package ar.edu.utn.frba.dds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

public abstract class Service {
  @Setter
  @Getter
  public String name;
  @Setter
  @Getter
  public String description;
  @Setter
  @Getter
  public Boolean inService;
  public List<Section> sections;

  public Service() {
    this.sections = new ArrayList<>();
  }

  public void addNewSection(Section newSection) {
    this.sections.add(newSection);
  }

  public List<Section> getSections() {
    return this.sections.stream().collect(Collectors.toList());
  }
}