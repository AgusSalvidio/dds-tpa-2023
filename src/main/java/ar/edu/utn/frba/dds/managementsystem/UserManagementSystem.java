package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.notification.notificationmean.JakartaAdapter;
import ar.edu.utn.frba.dds.notification.notificationmean.NotificationMean;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByMail;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByWhatsApp;
import ar.edu.utn.frba.dds.notification.notificationmean.TwilioAdapter;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import java.util.List;
import java.util.Map;

public class UserManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public UserManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Usuarios";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static UserManagementSystem workingWith(
      RelationalDatabasePersistenceSystem persistenceSystem) {
    return new UserManagementSystem(persistenceSystem);
  }

  public void startManaging(User anUser) {
    this.persistenceSystem().startManagingUser(anUser);
  }

  public void updateWith(User anUser) {
    this.persistenceSystem().updateManagingUser(anUser);
  }

  public void stopManaging(User anUser) {
    this.persistenceSystem().stopManagingUser(anUser);
  }

  public void startManagingDetail(UserDetail anUserDetail) {
    this.persistenceSystem().startManagingUserDetail(anUserDetail);
  }

  public void updateDetailWith(UserDetail anUserDetail) {
    this.persistenceSystem().updateManagingUserDetail(anUserDetail);
  }

  public void stopManagingDetail(UserDetail anUserDetail) {
    this.persistenceSystem().stopManagingUserDetail(anUserDetail);
  }

  public List<User> users() {
    return this.persistenceSystem.users();
  }

  public List<UserDetail> userDetails() {
    return this.persistenceSystem.userDetails();
  }

  public User userIdentifiedBy(Integer anUserId) {
    return this.persistenceSystem.userIdentifiedBy(anUserId);
  }

  public User userNamed(String anUserName) {
    return this.persistenceSystem.userNamed(anUserName);
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    /* For now, this system should have an implementation. This will be enhanced
     when the extracting the implementation from ManagementSystem -asalvidio*/
  }

  public void startManagingUserFrom(Map model) throws Exception {
    String name = model.get("name").toString();
    String lastname = model.get("lastname").toString();
    String email = model.get("email").toString();
    String username = model.get("username").toString();
    String password = model.get("password").toString();
    String telephone = model.get("telephone").toString();
    NotificationMean notificationMean =
        this.convertToEntity(model.get("notificationMean").toString());
    AuthorizationRole authorizationRole = AuthorizationRole.valueOf(
            model.get("authorizationRole").toString());

    UserDetail userDetail = new UserDetail(name, lastname, email, telephone, notificationMean);
    this.startManagingDetail(userDetail);

    this.startManaging(
        User.composedOf(username, password, userDetail, authorizationRole));
  }

  public void updateUserFrom(User userToUpdate, Map model) throws Exception {
    String name = model.get("name").toString();
    String lastname = model.get("lastname").toString();
    String email = model.get("email").toString();
    String username = model.get("username").toString();
    String password = model.get("password").toString();
    String telephone = model.get("telephone").toString();
    NotificationMean notificationMean =
        this.convertToEntity(model.get("notificationMean").toString());
    AuthorizationRole authorizationRole = AuthorizationRole.valueOf(
            model.get("authorizationRole").toString());

    UserDetail userDetail = new UserDetail(name, lastname, email, telephone, notificationMean);
    userDetail.setId(userToUpdate.getDetails().getId());

    User updatedUser = User.composedOf(username, password, userDetail, authorizationRole);
    updatedUser.setId(userToUpdate.getId());

    this.updateWith(updatedUser);
  }

  private NotificationMean convertToEntity(String str) {
    NotificationMean obj = null;

    if (str.equals("wpp")) {
      obj = new NotifyByWhatsApp(new TwilioAdapter());
    } else if (str.equals("email")) {
      obj = new NotifyByMail(new JakartaAdapter());
    }
    return obj;
  }
}
