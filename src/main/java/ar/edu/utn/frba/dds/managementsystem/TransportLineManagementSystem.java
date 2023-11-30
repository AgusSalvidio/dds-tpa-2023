package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;

public class TransportLineManagementSystem extends ManagementSystem {
  public TransportLineManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    super(persistenceSystem);
    myTypeDescription = "A/E de Lineas de Transporte";
  }

  public Entity entityById(Integer id) {
    return this.persistenceSystem.entityById(id);
  }

}
