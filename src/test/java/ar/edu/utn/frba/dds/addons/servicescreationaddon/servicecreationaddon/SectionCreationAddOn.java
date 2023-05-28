package ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon;

import ar.edu.utn.frba.dds.service.Section;

public class SectionCreationAddOn {

  public Section sectionA() {
    Section section = new Section();
    section.setName("Acceso Principal a Molinetes");
    return section;
  }
}
