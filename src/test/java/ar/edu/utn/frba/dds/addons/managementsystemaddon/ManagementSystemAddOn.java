package ar.edu.utn.frba.dds.addons.managementsystemaddon;

import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.managementsystem.IncidentManagementSystem;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;

public class ManagementSystemAddOn {

  private MemoryBasedPersistenceSystem persistenceSystem() throws Exception {
    return this.relationalDatabasePersistenceSystem();
  }

  private MemoryBasedPersistenceSystem relationalDatabasePersistenceSystem() throws Exception {
    return new MemoryBasedPersistenceSystem();
  }

  public IncidentManagementSystem incidentManagementSystemUsing(EventNotificationSystem eventNotificationSystem) throws Exception {
    return IncidentManagementSystem.workingWith(this.persistenceSystem());
  }

  public DummyManagementSystem dummyManagementSystemUsing(EventNotificationSystem eventNotificationSystem) {
    return new DummyManagementSystem(eventNotificationSystem);
  }

}

