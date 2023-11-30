package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;

public class EstablishmentTypeManagementSystem extends ManagementSystem {

  public EstablishmentTypeManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    super(persistenceSystem);
    myTypeDescription = "A/E de Tipo de Establecimientos";
  }

  public EstablishmentType establishmentTypeById(Integer id) {
    return this.persistenceSystem.establishmentTypeById(id);
  }

}
