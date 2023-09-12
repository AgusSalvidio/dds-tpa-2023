package ar.edu.utn.frba.dds.notification.notificationmean;

import ar.edu.utn.frba.dds.notification.Msg;

public interface MailNotifierAdapter {
  void notify(Msg message);
}