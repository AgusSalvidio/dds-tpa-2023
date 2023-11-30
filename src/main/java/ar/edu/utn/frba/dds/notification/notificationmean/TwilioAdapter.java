package ar.edu.utn.frba.dds.notification.notificationmean;

import ar.edu.utn.frba.dds.notification.Msg;
import ar.edu.utn.frba.dds.property.ReadProperties;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class TwilioAdapter implements WhatsAppNotifierAdapter {
  private final String accountSid;
  private final String authToken;
  private final String twilioPhoneNumber;

  public TwilioAdapter() {
    ReadProperties readProp = ReadProperties.getInstance();
    this.accountSid = readProp.getTwilioAccountSid();
    this.authToken = readProp.getTwilioAuthToken();
    this.twilioPhoneNumber = readProp.getTwilioPhoneNumber();
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