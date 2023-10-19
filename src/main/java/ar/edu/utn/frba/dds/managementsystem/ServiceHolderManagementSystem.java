package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.serviceholder.ServiceHolder;
import java.util.List;

public class ServiceHolderManagementSystem {

  MemoryBasedPersistenceSystem persistenceSystem;

  public ServiceHolderManagementSystem(
      MemoryBasedPersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }


  public String typeDescription() {
    return "Sistema de Administración de Entidades";
  }

  public List<ServiceHolder> serviceHolders() {
    return this.persistenceSystem.serviceHolders();
  }

  private MemoryBasedPersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static ServiceHolderManagementSystem workingWith(
      MemoryBasedPersistenceSystem persistenceSystem) {
    return new ServiceHolderManagementSystem(persistenceSystem);
  }

  public void startManagingServiceHolder(ServiceHolder serviceHolder) {
    this.persistenceSystem.startManagingServiceHolder(serviceHolder);
  }

}
