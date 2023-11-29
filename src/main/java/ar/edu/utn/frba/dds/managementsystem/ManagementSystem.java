package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.TransportType;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;

import java.util.List;

public abstract class ManagementSystem {

  RelationalDatabasePersistenceSystem persistenceSystem;

  String myTypeDescription;

  public ManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
    myTypeDescription = "";
  }

  public String typeDescripction() {
    return this.myTypeDescription;
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public List<Object> objectList(String className) {
    return this.persistenceSystem.objectList(className);
  }

  public void startManaging(Object object) {
    this.persistenceSystem.startManaging(object);
  }

  public void stopManagingEntityType(Object object) {
    this.persistenceSystem.stopManaging(object);
  }

}
