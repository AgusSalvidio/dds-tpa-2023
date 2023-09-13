package ar.edu.utn.frba.dds.notification.notificationmean;

import ar.edu.utn.frba.dds.notification.Msg;
import ar.edu.utn.frba.dds.property.ReadProperties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class JakartaAdapter implements MailNotifierAdapter {
  private final String username;
  private final String password;

  public JakartaAdapter() {
    ReadProperties readProp = ReadProperties.getInstance();
    this.username = readProp.getJakartaUsername();
    this.password = readProp.getJakartaPassword();
  }

  @Override
  public void notify(Msg message) {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(props,
        this.getPasswordAuthentication(this.username, this.password));

    try {
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(this.username));
      msg.setRecipient(Message.RecipientType.TO, new InternetAddress(message.getUser().email()));
      msg.setSubject("Notificacion de Incidente");
      msg.setText(message.getContent());

      Transport.send(msg);

      System.out.println("Message Sent.");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  private Authenticator getPasswordAuthentication(String username, String password) {
    return new jakarta.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    };
  }
}