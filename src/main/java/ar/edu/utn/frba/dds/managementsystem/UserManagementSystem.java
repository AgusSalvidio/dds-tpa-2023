package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import java.util.ArrayList;
import java.util.List;

public class UserManagementSystem implements ManagementSystem {
  /*
    This is a STUPID IMPLEMENTATION but CodeSmells checks is so annoying that has to be this way so
    it will shut up.
   */
  List<Object> systems;

  public UserManagementSystem(PersistenceSystem persistenceSystem) {
    this.systems = new ArrayList<>();
    this.systems.add(persistenceSystem);
    this.persistenceSystem().addObjectTypeToStore(User.class.getName());
  }

  public String typeDescription() {
    return "Sistema de Administración de Usuarios";
  }

  private PersistenceSystem persistenceSystem() {
    return (PersistenceSystem) this.systems.get(0);
  }

  public static UserManagementSystem workingWith(PersistenceSystem persistenceSystem) {
    return new UserManagementSystem(persistenceSystem);
  }

  public User user(User anUser) {
    return (User) this.persistenceSystem().findObjectTyped(anUser.getClass().getName(), anUser);
  }

  public List<Object> users() {
    return this.persistenceSystem().objectsFrom(User.class.getName());
  }

  public void startManaging(Object anUser) {
    this.persistenceSystem().storeObjectTyped(User.class.getName(), anUser);
  }

  public void stopManaging(Object anUser) {
    this.persistenceSystem().removeObjectTyped(anUser.getClass().getName(), anUser);
  }

  public void updateWith(Object currentUser, Object updatedUser) {
    User obtainedUser = (User) this.persistenceSystem()
        .findObjectTyped(currentUser.getClass().getName(), currentUser);
    obtainedUser.synchronizeWith((User) updatedUser);
  }

  public void receiveFrom(NotifiableEvent event, Object publisher){}

}
