package ar.edu.utn.frba.dds.service;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public abstract class Service {
  @Setter
  @Getter
  private String name;
  @Setter
  @Getter
  private Boolean inService;
  @Getter
  private List<Section> sections;

  public Service() {
    this.sections = new ArrayList<>();
  }
  public void addNewSection(Section newSection) {
    this.sections.add(newSection);
  }
}