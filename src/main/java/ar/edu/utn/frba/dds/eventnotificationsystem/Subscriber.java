package ar.edu.utn.frba.dds.eventnotificationsystem;

import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;

public interface Subscriber {
  public void receiveFrom(NotifiableEvent event, Object publisher);

}
