package ar.edu.utn.frba.dds.addons.servicescreationaddon.publicservicecreationaddon;

import ar.edu.utn.frba.dds.entity.EntityType;

public class EntityTypeCreationAddOn {
  public EntityType subway() {
    EntityType subway = new EntityType();
    subway.setName("SUBWAY");
    return subway;
  }
}
