package ar.edu.utn.frba.dds.addons.entitycreationaddon;

import ar.edu.utn.frba.dds.entity.EntityType;

public class EntityTypeCreationAddOn {
  public EntityType subway() {
    EntityType entityType = new EntityType();
    entityType.setName("SUBWAY");
    return entityType;
  }

  public EntityType bank() {
    EntityType entityType = new EntityType();
    entityType.setName("BANK");
    return entityType;
  }
}
