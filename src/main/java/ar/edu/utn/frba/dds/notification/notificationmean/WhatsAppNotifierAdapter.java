package ar.edu.utn.frba.dds.notification.notificationmean;

import ar.edu.utn.frba.dds.notification.Msg;

public interface WhatsAppNotifierAdapter {
  void notify(Msg message);
}