package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserManagementSystemTest {
  private UserDetails userDetails() {
    return new UserDetails("Hugo", "Ibarra", "ibarraneta@gmail.com");
  }

  private PersistenceSystem persistenceSystem() {
    return this.memoryBasedPersistenceSystem();
  }

  private MemoryBasedPersistenceSystem memoryBasedPersistenceSystem() {
    return new MemoryBasedPersistenceSystem();
  }

  @Test
  @DisplayName("Start managing an user")
  public void startManagingAnUserTest() throws Exception {

    UserManagementSystem userManagementSystem = UserManagementSystem.workingWith(this.persistenceSystem());
    User user = User.composedBy("ibarranetaYPF", "theBestPassword", this.userDetails());

    userManagementSystem.startManaging(user);

    Assertions.assertEquals(userManagementSystem.user(user), user);

  }

  @Test
  @DisplayName("Stop managing an user")
  public void stopManagingAnUserTest() throws Exception {

    UserManagementSystem userManagementSystem = UserManagementSystem.workingWith(this.persistenceSystem());
    User user = User.composedBy("ibarranetaYPF", "theBestPassword", this.userDetails());

    userManagementSystem.startManaging(user);

    Assertions.assertEquals(userManagementSystem.user(user), user);

    userManagementSystem.stopManaging(user);

    Assertions.assertTrue(userManagementSystem.users().isEmpty());


  }

  @Test
  @DisplayName("Update an user")
  public void updateAnUserTest() throws Exception {

    UserManagementSystem userManagementSystem = UserManagementSystem.workingWith(this.persistenceSystem());
    User user = User.composedBy("ibarranetaYPF", "theBestPassword", this.userDetails());
    User updatedUser = User.composedBy("almironeta", "theBestPassword", this.userDetails());
    userManagementSystem.startManaging(user);

    Assertions.assertEquals(userManagementSystem.user(user), user);

    userManagementSystem.updateWith(user, updatedUser);

    Assertions.assertTrue(userManagementSystem.users().contains(user));


  }

}
