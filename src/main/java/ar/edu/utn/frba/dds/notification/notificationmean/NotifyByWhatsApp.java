package ar.edu.utn.frba.dds.notification.notificationmean;

import ar.edu.utn.frba.dds.notification.Msg;

public class NotifyByWhatsApp implements NotificationMean {
  public WhatsAppNotifierAdapter adapter;

  public NotifyByWhatsApp(WhatsAppNotifierAdapter adapter) {
    this.adapter = adapter;
  }

  @Override
  public void notify(Msg message) {
    this.adapter.notify(message);
  }
}