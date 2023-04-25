package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import java.util.ArrayList;
import java.util.List;

/*public class CommunityManagementSystem implements ManagementSystem {

  List<Object> systems = new ArrayList<>();

  public CommunityManagementSystem(PersistenceSystem persistenceSystem) {
    this.systems.add(persistenceSystem);
    this.persistenceSystem().addObjectTypeToStore(Community.class.getName());
  }

  public String typeDescription() {
    return "Sistema de Administración de Comunidades";
  }

  /*private PersistenceSystem persistenceSystem() {
    return (PersistenceSystem) this.systems.get(0);
  }

  public static CommunityManagementSystem workingWith(PersistenceSystem persistenceSystem) {
    return new CommunityManagementSystem(persistenceSystem);
  }

  public List<Object> members() {
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
  }*/

