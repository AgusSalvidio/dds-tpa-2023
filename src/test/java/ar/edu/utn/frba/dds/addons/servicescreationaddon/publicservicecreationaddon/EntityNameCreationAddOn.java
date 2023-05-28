package ar.edu.utn.frba.dds.addons.servicescreationaddon.publicservicecreationaddon;

import ar.edu.utn.frba.dds.entity.EntityName;

public class EntityNameCreationAddOn {
  public EntityName subwayHLine() {
    EntityName transportLine = new EntityName();
    transportLine.setName("SUBTE H");
    return transportLine;
  }
}
