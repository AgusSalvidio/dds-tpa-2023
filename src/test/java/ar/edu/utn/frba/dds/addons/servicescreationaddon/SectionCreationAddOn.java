package ar.edu.utn.frba.dds.addons.servicescreationaddon;

import ar.edu.utn.frba.dds.service.Section;

public class SectionCreationAddOn {

  public Section sectionA() {
    return Section.named("Acceso Principal a Molinetes");
  }

  public Section sectionB() {
    return Section.named("Acceso a Plataforma");
  }


}
