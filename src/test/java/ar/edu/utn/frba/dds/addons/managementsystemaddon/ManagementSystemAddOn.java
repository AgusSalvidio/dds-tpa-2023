package ar.edu.utn.frba.dds.addons.managementsystemaddon;

import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.managementsystem.IncidentManagementSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;

public class ManagementSystemAddOn {

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.relationalDatabasePersistenceSystem();
  }

  private RelationalDatabasePersistenceSystem relationalDatabasePersistenceSystem() {
    return new RelationalDatabasePersistenceSystem();
  }

  public IncidentManagementSystem incidentManagementSystemUsing(EventNotificationSystem eventNotificationSystem) {
    return IncidentManagementSystem.workingWith(this.persistenceSystem());
  }

  public DummyManagementSystem dummyManagementSystemUsing(EventNotificationSystem eventNotificationSystem) {
    return new DummyManagementSystem(eventNotificationSystem);
  }

}

