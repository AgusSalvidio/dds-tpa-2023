package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  public void startManagingDetail(UserDetail anUserDetail) {
    this.persistenceSystem().startManagingUserDetail(anUserDetail);
  }


  public List<User> users() {
    return this.persistenceSystem.users();
  }

  public void stopManaging(Object anUser) {
    //this.persistenceSystem().removeObjectTyped(anUser.getClass().getName(), anUser);
  }

  public void updateWith(Object currentUser, Object updatedUser) {
    /*User obtainedUser = (User) this.persistenceSystem()
        .findObjectTyped(currentUser.getClass().getName(), currentUser);
    obtainedUser.synchronizeWith((User) updatedUser);*/
  }

  /*public User user(User anUser) {
    //return (User) this.persistenceSystem().findObjectTyped(anUser.getClass().getName(), anUser);
  }*/

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

    UserDetail userDetail = new UserDetail(name, lastname, email);
    this.startManagingDetail(userDetail);

    this.startManaging(User.composedOf(username, password, userDetail));

  }

}
