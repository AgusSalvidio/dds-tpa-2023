package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.serviceholder.ServiceHolder;
import java.util.List;

public class ServiceHolderManagementSystem {

  RelationalDatabasePersistenceSystem persistenceSystem;

  public ServiceHolderManagementSystem(
      RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }


  public String typeDescription() {
    return "Sistema de Administración de Entidades";
  }

  public List<ServiceHolder> serviceHolders() {
    return this.persistenceSystem.serviceHolders();
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static ServiceHolderManagementSystem workingWith(
      RelationalDatabasePersistenceSystem persistenceSystem) {
    return new ServiceHolderManagementSystem(persistenceSystem);
  }

  public void startManagingServiceHolder(ServiceHolder serviceHolder) {
    this.persistenceSystem.startManagingServiceHolder(serviceHolder);
  }

}
