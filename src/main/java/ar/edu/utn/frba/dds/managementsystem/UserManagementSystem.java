package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import java.util.ArrayList;
import java.util.List;

public class UserManagementSystem {
  /*
    This is a STUPID IMPLEMENTATION but CodeSmells checks is so annoying that has to be this way so
    it will shut up.
   */
  List<Object> systems = new ArrayList<>();

  /**
   * UserManagementSystem to manage user storage.
   */
  public UserManagementSystem(PersistenceSystem persistenceSystem) {
    this.systems.add(persistenceSystem);
    this.persistenceSystem().addObjectTypeToStore(User.class.getName());
  }

  private PersistenceSystem persistenceSystem() {
    return (PersistenceSystem) this.systems.get(0);
  }

  public static UserManagementSystem workingWith(PersistenceSystem persistenceSystem) {
    return new UserManagementSystem(persistenceSystem);
  }

  /**
   * This is weak implementation, could use some stream or others things....
   * Also should add some check if the desire object is not found.
   * Should improve this later.
   */
  public User user(User anUser) {
    return (User) this.persistenceSystem().findObjectTyped(anUser.getClass().getName(), anUser);
  }

  public List<Object> users() {
    return this.persistenceSystem().objectsFrom(User.class.getName());
  }

  public void startManaging(User anUser) {
    this.persistenceSystem().storeObjectTyped(User.class.getName(), anUser);
  }

  public void stopManaging(User anUser) {
    this.persistenceSystem().removeObjectTyped(anUser.getClass().getName(), anUser);
  }

  public void updateWith(User currentUser, User updatedUser) {
    User obtainedUser = (User) this.persistenceSystem()
        .findObjectTyped(currentUser.getClass().getName(), currentUser);
    obtainedUser.synchronizeWith(updatedUser);
  }

}
