package ar.edu.utn.frba.dds.demo;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.notification.notificationmean.JakartaAdapter;
import ar.edu.utn.frba.dds.notification.notificationmean.NotificationMean;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByMail;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByWhatsApp;
import ar.edu.utn.frba.dds.notification.notificationmean.TwilioAdapter;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import java.util.ArrayList;
import java.util.List;

public class Demo {
  private List<User> users = new ArrayList<>();
  private List<UserDetail> userDetails = new ArrayList<>();

  public Demo() throws Exception {
  }

  public List<User> users() {
    return this.users;
  }

  public List<UserDetail> userDetails() {
    return this.userDetails;
  }

  public NotificationMean wpp() throws Exception {
    return new NotifyByWhatsApp(new TwilioAdapter());
  }

  public NotificationMean mail() throws Exception {
    return new NotifyByMail(new JakartaAdapter());
  }

  public UserDetail ibarra() throws Exception {
    return UserDetail.composedOf("Hugo", "Ibarra", "ibarraneta@gmail.com", "0123456789", this.wpp());
  }

  public UserDetail basuraIntergalactica() throws Exception {
    return UserDetail.composedOf("Basura", "Intergalactica", "basuraintergalactica@gmail.com", "0123456789", this.mail());
  }

  public UserDetail basuraIntergalactica2() throws Exception {
    return UserDetail.composedOf("Basura", "Intergalactica2", "basuraintergalactica2@gmail.com", "0123456789", this.wpp());
  }

  public User hugo() throws Exception {
    return User.composedOf("admin", "admin", this.ibarra(), AuthorizationRole.ADMINISTRADOR);
  }

  public User basura1() throws Exception {
    return User.composedOf("user", "user", this.basuraIntergalactica(), AuthorizationRole.USUARIO);
  }

  public User basura2() throws Exception {
    return User.composedOf("entidad", "entidad", this.basuraIntergalactica2(), AuthorizationRole.ENTIDAD);
  }

  public void initialize() throws Exception {
    this.addUsers();
  }

  private void addUsers() throws Exception {
    this.ibarra().setId(1);
    User hugo = this.hugo();
    hugo.setId(1);
    this.userDetails.add(this.ibarra());
    this.users.add(hugo);

    this.basuraIntergalactica().setId(2);
    User basura1 = this.basura1();
    basura1.setId(2);
    this.userDetails.add(this.basuraIntergalactica());
    this.users.add(basura1);

    this.basuraIntergalactica2().setId(3);
    User basura2 = this.basura2();
    basura1.setId(3);
    this.userDetails.add(this.basuraIntergalactica2());
    this.users.add(basura2);
  }
}
