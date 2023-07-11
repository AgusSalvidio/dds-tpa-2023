package ar.edu.utn.frba.dds.eventnotificationsystem;

import ar.edu.utn.frba.dds.addons.managementsystemaddon.DummyManagementSystem;
import ar.edu.utn.frba.dds.addons.managementsystemaddon.ManagementSystemAddOn;
import ar.edu.utn.frba.dds.addons.notifiableeventaddon.NotifiableEventAddOn;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.managementsystem.IncidentManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.ManagementSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventNotificationSystemTest {

  private DummyManagementSystem dummyManagementSystemUsing(EventNotificationSystem eventNotificationSystem) {
    return new ManagementSystemAddOn().dummyManagementSystemUsing(eventNotificationSystem);
  }

  private IncidentManagementSystem incidentManagementSystem() {
    return new ManagementSystemAddOn().incidentManagementSystem();
  }

  private NotifiableEvent incidentNotifiableEvent() throws Exception {
    return new NotifiableEventAddOn().incidentNotifiableEvent();
  }

  @Test
  @DisplayName("Add subscriber to system")
  public void addSubscriberToSystemTest() throws Exception {

    EventNotificationSystem eventNotificationSystem = new EventNotificationSystem();
    DummyManagementSystem dummyManagementSystem = this.dummyManagementSystemUsing(eventNotificationSystem);
    NotifiableEvent notifiableEvent = this.incidentNotifiableEvent();

    Assertions.assertTrue(eventNotificationSystem.subscriptions().isEmpty());

    eventNotificationSystem.subscribeToEventsOfType(dummyManagementSystem, notifiableEvent);

    Assertions.assertEquals(1, eventNotificationSystem.subscriptions.size());

  }

  @Test
  @DisplayName("Remove subscription from system")
  public void removeSubscriberToSystemTest() throws Exception {

    EventNotificationSystem eventNotificationSystem = new EventNotificationSystem();
    DummyManagementSystem dummyManagementSystem = this.dummyManagementSystemUsing(eventNotificationSystem);
    NotifiableEvent notifiableEvent = this.incidentNotifiableEvent();

    Assertions.assertTrue(eventNotificationSystem.subscriptions().isEmpty());

    eventNotificationSystem.subscribeToEventsOfType(dummyManagementSystem, notifiableEvent);

    Assertions.assertEquals(1, eventNotificationSystem.subscriptions.size());

    Subscription subscription = eventNotificationSystem.subscriptions().get(0);

    eventNotificationSystem.removeSubscription(subscription);

    Assertions.assertTrue(eventNotificationSystem.subscriptions().isEmpty());
  }


  @Test
  @DisplayName("Publish and notify subscribers of an event")
  public void publishAndNotifySubscribersOfAnEventTest() throws Exception {

    EventNotificationSystem eventNotificationSystem = new EventNotificationSystem();
    DummyManagementSystem dummyManagementSystem = this.dummyManagementSystemUsing(eventNotificationSystem);
    NotifiableEvent notifiableEvent = this.incidentNotifiableEvent();
    IncidentManagementSystem incidentManagementSystem = this.incidentManagementSystem();

    Assertions.assertTrue(eventNotificationSystem.subscriptions().isEmpty());

    eventNotificationSystem.subscribeToEventsOfType(dummyManagementSystem, notifiableEvent);

    Assertions.assertFalse(dummyManagementSystem.wasNotified());

    eventNotificationSystem.publishFrom(notifiableEvent, incidentManagementSystem);

    Assertions.assertTrue(dummyManagementSystem.wasNotified());

  }

}
