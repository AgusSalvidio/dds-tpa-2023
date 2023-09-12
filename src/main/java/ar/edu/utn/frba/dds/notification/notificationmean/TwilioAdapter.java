package ar.edu.utn.frba.dds.notification.notificationmean;

import ar.edu.utn.frba.dds.notification.Msg;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class TwilioAdapter implements WhatsAppNotifierAdapter {
  private final String accountSid;
  private final String authToken;
  private final String twilioPhoneNumber;

  public TwilioAdapter() {
    this.accountSid  = "AC90c882c5f8db0470e963f6b930f073cb";
    this.authToken  = "3189e11fa722f934750f8d3893c9322a";
    this.twilioPhoneNumber  = "+14155238886";
  }

  @Override
  public void notify(Msg message) {
    Twilio.init(this.accountSid, this.authToken);
    try {
      Message msg = Message.creator(
              new PhoneNumber("whatsapp:" + message.getUser().getDetails().getTelephone()),
              new PhoneNumber("whatsapp:" + this.twilioPhoneNumber),
              message.getContent())
          .create();
      System.out.println("Message Sent. SID: " + msg.getSid());
    } catch (ApiException e) {
      System.out.println("Error sending the message: " + e.getMessage());
    }
  }
}