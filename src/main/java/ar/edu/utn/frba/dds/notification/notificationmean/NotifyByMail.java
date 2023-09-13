package ar.edu.utn.frba.dds.notification.notificationmean;

import ar.edu.utn.frba.dds.notification.Msg;

public class NotifyByMail implements NotificationMean {
  public MailNotifierAdapter adapter;

  public NotifyByMail(MailNotifierAdapter adapter) {
    this.adapter = adapter;
  }

  @Override
  public void notify(Msg message) {
    this.adapter.notify(message);
  }
}