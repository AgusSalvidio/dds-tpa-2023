package ar.edu.utn.frba.dds.eventnotificationsystem;

import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.managementsystem.ManagementSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventNotificationSystem {
  List<Subscription> subscriptions;

  public EventNotificationSystem() {
    this.subscriptions = new ArrayList<>();
  }

  private void addSubscription(Subscription subscription) {
    this.subscriptions.add(subscription);
  }

  public void removeSubscription(Subscription subscription) {
    this.subscriptions.remove(subscription);
  }

  public List<Subscription> subscriptions() {
    return this.subscriptions.stream()
        .collect(Collectors.toList());
  }

  public void subscribeToEventsOfType(
      ManagementSystem subscriber,
      NotifiableEvent eventType) {

    Subscription subscription = Subscription.composedOf(subscriber, eventType);

    this.addSubscription(subscription);

  }

  public void publishFrom(NotifiableEvent event, Object publisher) {

    List<Subscription> subscriptions = this.subscriptions().stream()
        .filter(subscription -> subscription.event()
            .equals(event))
        .toList();

    subscriptions.stream()
        .forEach(subscription -> subscription.receiveFrom(event, publisher)
        );

  }

}
