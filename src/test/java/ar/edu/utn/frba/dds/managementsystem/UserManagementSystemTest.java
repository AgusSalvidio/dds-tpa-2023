package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserManagementSystemTest implements WithSimplePersistenceUnit {
  private UserDetail userDetails() {
    return new UserDetail("Hugo", "Ibarra", "ibarraneta@gmail.com");
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.relationalDatabasePersistenceSystem();
  }

  private RelationalDatabasePersistenceSystem relationalDatabasePersistenceSystem() {
    return new RelationalDatabasePersistenceSystem();
  }

  @Test
  @DisplayName("Start managing an user")
  public void startManagingAnUserTest() throws Exception {

    UserManagementSystem userManagementSystem = Mockito.spy(UserManagementSystem.workingWith(this.persistenceSystem()));
    UserDetail userDetail = this.userDetails();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail);

    Mockito.doAnswer(invocation -> {
      EntityTransaction transaction = entityManager().getTransaction();

      User obtainedUser = invocation.getArgument(0);

      UserDetail obtainedUserDetail = obtainedUser.getDetails();

      transaction.begin();
      entityManager().persist(obtainedUserDetail);
      entityManager().persist(obtainedUser);
      transaction.commit();
      return null;
    }).when(userManagementSystem).startManaging(user);

    userManagementSystem.startManaging(user);
    UserDetail registeredUserDetail = entityManager().find(UserDetail.class, 1);
    User registeredUser = entityManager().find(User.class, 2);

    Assertions.assertEquals(userManagementSystem.users().size(), 1);
    Assertions.assertEquals(userManagementSystem.users().get(0), registeredUser);
    Assertions.assertEquals(userManagementSystem.users().get(0).getDetails(), registeredUserDetail);

  }

  /*
  @Test
  @DisplayName("Stop managing an user")
  public void stopManagingAnUserTest() throws Exception {

    UserManagementSystem userManagementSystem = UserManagementSystem.workingWith(this.persistenceSystem());
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());

    userManagementSystem.startManaging(user);

    Assertions.assertEquals(userManagementSystem.user(user), user);

    userManagementSystem.stopManaging(user);

    Assertions.assertTrue(userManagementSystem.users().isEmpty());


  }

  @Test
  @DisplayName("Update an user")
  public void updateAnUserTest() throws Exception {

    UserManagementSystem userManagementSystem = UserManagementSystem.workingWith(this.persistenceSystem());
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    User updatedUser = User.composedOf("almironeta", "theBestPassword", this.userDetails());
    userManagementSystem.startManaging(user);

    Assertions.assertEquals(userManagementSystem.user(user), user);

    userManagementSystem.updateWith(user, updatedUser);

    Assertions.assertTrue(userManagementSystem.users().contains(user));


  }
*/

}
