package ar.edu.utn.frba.dds.eventnotificationsystem;

import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.managementsystem.ManagementSystem;

public class Subscription {
  ManagementSystem subscriber;
  NotifiableEvent event;

  public static Subscription composedOf(ManagementSystem subscriber, NotifiableEvent event) {
    return new Subscription(subscriber, event);
  }

  public Subscription(ManagementSystem subscriber, NotifiableEvent event) {
    this.subscriber = subscriber;
    this.event = event;
  }

  public NotifiableEvent event() {
    return this.event;
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    this.subscriber.receiveFrom(event, publisher);
  }

}
