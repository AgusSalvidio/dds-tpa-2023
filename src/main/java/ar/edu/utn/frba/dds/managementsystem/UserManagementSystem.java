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
  List<PersistenceSystem> persistenceSystem = new ArrayList<>();

  /**
   * UserManagementSystem to manage user storage.
   */
  public UserManagementSystem(PersistenceSystem persistenceSystem) {
    this.persistenceSystem.add(persistenceSystem);
    this.persistenceSystem().addObjectTypeToStore(User.class.getName());
  }

  private PersistenceSystem persistenceSystem() {
    return this.persistenceSystem.get(0);
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
    Object obtainedUserList = this.persistenceSystem().objectsFrom(anUser.getClass().getName());
    List<User> castedUserList = (List<User>) obtainedUserList;
    return this.findIn(anUser, castedUserList);
  }

  private User findIn(User anUser, List<User> anUserList) {
    return anUserList.get(anUserList.indexOf(anUser));
  }

  public void startManaging(User anUser) {
    this.persistenceSystem().storeObjectTyped(User.class.getName(), anUser);
  }
  /*public void stopManaging(User anUser){
    this.persistenceSystem.removeObjectTyped(this.getClass().getName(),anUser);
  }
  public void updateWith(User currentUser, User updatedUser){
    this.persistenceSystem.updateObjectTypedWith(this.getClass().getName(),currentUser,updatedUser);
  }
*/
}
