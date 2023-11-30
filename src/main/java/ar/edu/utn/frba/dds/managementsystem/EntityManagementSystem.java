package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;

public class EntityManagementSystem extends ManagementSystem {

  public EntityManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    super(persistenceSystem);
    myTypeDescription = "A/E de Entidades";
  }

  public Entity entityById(Integer id) {
    return this.persistenceSystem.entityById(id);
  }

}
