package ar.edu.utn.frba.dds.addons.entitycreationaddon;

import ar.edu.utn.frba.dds.entity.EntityName;

public class EntityNameCreationAddOn {
  public EntityName subwayHLine() {
    EntityName entityName = new EntityName();
    entityName.setName("SUBTE H");
    return entityName;
  }

  public EntityName nationalBank() {
    EntityName entityName = new EntityName();
    entityName.setName("BANCO NACION");
    return entityName;
  }
}
