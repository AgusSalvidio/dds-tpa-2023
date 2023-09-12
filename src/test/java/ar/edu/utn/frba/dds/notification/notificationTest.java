package ar.edu.utn.frba.dds.notification;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import ar.edu.utn.frba.dds.addons.usercreationaddon.UserDetailCreationAddOn;
import ar.edu.utn.frba.dds.notification.notificationmean.JakartaAdapter;
import ar.edu.utn.frba.dds.notification.notificationmean.NotificationMean;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByMail;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByWhatsApp;
import ar.edu.utn.frba.dds.notification.notificationmean.TwilioAdapter;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class notificationTest {
  @Test
  @DisplayName("Check mail notification")
  public void checkMailNotification() throws Exception {
    NotificationMean mockMail = mock(NotifyByMail.class);
    UserDetail userDetail = new UserDetailCreationAddOn().basuraIntergalactica();
    userDetail.changeNotificationMean(mockMail);
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail);
    Msg message = new Msg("Test msg", user);

    user.notifyMe(message);
    Mockito.verify(mockMail,times(1)).notify(any());
  }

  @Test
  @DisplayName("Check whatsapp notification")
  public void checkWhatsAppNotification() throws Exception {
    NotificationMean mockWhatsApp = mock(NotifyByWhatsApp.class);
    UserDetail userDetail = new UserDetailCreationAddOn().basuraIntergalactica();
    userDetail.changeNotificationMean(mockWhatsApp);
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail);
    Msg message = new Msg("Test msg", user);

    user.notifyMe(message);
    Mockito.verify(mockWhatsApp,times(1)).notify(any());
  }
}
