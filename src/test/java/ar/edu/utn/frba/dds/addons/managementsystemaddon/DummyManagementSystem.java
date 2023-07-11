package ar.edu.utn.frba.dds.addons.managementsystemaddon;

import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.managementsystem.ManagementSystem;

public class DummyManagementSystem implements ManagementSystem {

  Boolean wasNotified = false;
  EventNotificationSystem eventNotificationSystem;

  //Added externally ONLY for tests -asalvidio
  public DummyManagementSystem(EventNotificationSystem eventNotificationSystem) {
    this.eventNotificationSystem = eventNotificationSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Pruebas";
  }


  public void startManaging(Object incident) {
    //
  }

  public void stopManaging(Object incident) {
    //
  }

  public void updateWith(Object currentObject, Object updatedObject) {
    //
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    this.wasNotified = true;
  }

  public boolean wasNotified() {
    return this.wasNotified;
  }

}
