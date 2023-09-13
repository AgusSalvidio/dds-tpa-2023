package ar.edu.utn.frba.dds.converters;

import ar.edu.utn.frba.dds.notification.notificationmean.JakartaAdapter;
import ar.edu.utn.frba.dds.notification.notificationmean.NotificationMean;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByMail;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByWhatsApp;
import ar.edu.utn.frba.dds.notification.notificationmean.TwilioAdapter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NotificationMeanConverter implements AttributeConverter<NotificationMean, String> {
  @Override
  public String convertToDatabaseColumn(NotificationMean notificationMean) {
    String str = null;

    if (notificationMean.getClass().getSimpleName().equals("NotifyByWhatsApp")) {
      str = "wpp";
    } else if (notificationMean.getClass().getSimpleName().equals("NotifyByMail")) {
      str = "email";
    }
    return str;
  }

  @Override
  public NotificationMean convertToEntityAttribute(String str) {
    NotificationMean obj = null;

    if (str.equals("wpp")) {
      obj = new NotifyByWhatsApp(new TwilioAdapter());
    } else if (str.equals("email")) {
      obj = new NotifyByMail(new JakartaAdapter());
    }
    return obj;
  }
}
