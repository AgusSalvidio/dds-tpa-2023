package ar.edu.utn.frba.dds.addons.managementsystemaddon;

import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.managementsystem.IncidentManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.ManagementSystem;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;

public class ManagementSystemAddOn {

  private PersistenceSystem persistenceSystem() {
    return this.memoryBasedPersistenceSystem();
  }

  private MemoryBasedPersistenceSystem memoryBasedPersistenceSystem() {
    return new MemoryBasedPersistenceSystem();
  }

  public IncidentManagementSystem incidentManagementSystem() {
    return IncidentManagementSystem.workingWith(this.persistenceSystem());
  }

  public DummyManagementSystem dummyManagementSystemUsing(EventNotificationSystem eventNotificationSystem) {
    return new DummyManagementSystem(eventNotificationSystem);
  }

}

