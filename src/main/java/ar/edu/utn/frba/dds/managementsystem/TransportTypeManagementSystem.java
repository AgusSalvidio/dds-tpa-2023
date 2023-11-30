package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.TransportType;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;

public class TransportTypeManagementSystem extends ManagementSystem {

  public TransportTypeManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    super(persistenceSystem);
    myTypeDescription = "A/E de Tipo de Entidades";
  }

  public TransportType transportTypeById(Integer id) {
    return this.persistenceSystem.transportTypeById(id);
  }
}
