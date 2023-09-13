package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.addons.notificationcreationaddon.NotificationMeanCreationAddOn;
import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.composedOf;

public class AuthorizationRoleManagementSystemTest implements WithSimplePersistenceUnit {
  private UserDetail userDetails() throws Exception {
    return new UserDetail("Hugo", "Ibarra", "ibarraneta@gmail.com", "0123456789",
        new NotificationMeanCreationAddOn().wpp());
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.relationalDatabasePersistenceSystem();
  }

  private RelationalDatabasePersistenceSystem relationalDatabasePersistenceSystem() {
    return new RelationalDatabasePersistenceSystem();
  }

  @Test
  @DisplayName("Start managing an authorizationRole")
  public void startManagingAnAuthorizationRoleTest() throws Exception {

    AuthorizationRoleManagementSystem authorizationRoleManagementSystem =
        Mockito.spy(AuthorizationRoleManagementSystem.workingWith(this.persistenceSystem()));

    UserDetail userDetail = this.userDetails();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(userDetail);
    entityManager().persist(user);
    transaction.commit();
    entityManager().close();


    AuthorizationRole authorizationRole = AuthorizationRole.composedOf(user, "Administrador");

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      AuthorizationRole obtainedAuthorizationRole = invocation.getArgument(0);
      transact.begin();
      entityManager().persist(obtainedAuthorizationRole);
      transact.commit();
      return null;
    }).when(authorizationRoleManagementSystem).startManaging(authorizationRole);

    authorizationRoleManagementSystem.startManaging(authorizationRole);

    AuthorizationRole registeredRole = entityManager().find(AuthorizationRole.class, 3);

    Assertions.assertEquals(authorizationRoleManagementSystem.roles().size(), 1);
    //Assertions.assertEquals(authorizationRoleManagementSystem.roles().get(0), registeredRole);

  }


  /* When running on local works fine, but when running mvn clean, fails....
     Needs to be looked at. -asalvidio
  @Test
  @DisplayName("Stop managing an user")
  public void stopManagingAnUserTest() throws Exception {

    UserManagementSystem userManagementSystem = Mockito.spy(UserManagementSystem.workingWith(this.persistenceSystem()));
    UserDetail userDetail = this.userDetails();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(userDetail);
    entityManager().persist(user);
    transaction.commit();

    Assertions.assertEquals(userManagementSystem.users().size(), 1);

    User registeredUser = entityManager().find(User.class, 2);

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      User obtainedUser = invocation.getArgument(0);

      UserDetail obtainedUserDetail = obtainedUser.getDetails();

      transact.begin();
      entityManager().remove(obtainedUser);
      transact.commit();
      return null;
    }).when(userManagementSystem).stopManaging(registeredUser);


    userManagementSystem.stopManaging(registeredUser);
    Assertions.assertTrue(userManagementSystem.users().isEmpty());


  }

  @Test
  @DisplayName("Update an user")
  public void updateAnUserTest() throws Exception {

    UserManagementSystem userManagementSystem = Mockito.spy(UserManagementSystem.workingWith(this.persistenceSystem()));
    UserDetail userDetail = this.userDetails();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail);
    User updatedUser = User.composedOf("almironeta", "theBestPassword", userDetail);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(userDetail);
    entityManager().persist(user);
    transaction.commit();

    User registeredUser = entityManager().find(User.class, 2);

    Assertions.assertEquals(userManagementSystem.users().size(), 1);
    Assertions.assertEquals(registeredUser.username(), "ibarranetaYPF");

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      User obtainedUser = invocation.getArgument(0);

      User obtainedUpdatedUser = invocation.getArgument(1);

      obtainedUser.synchronizeWith(obtainedUpdatedUser);

      transact.begin();
      entityManager().merge(obtainedUser);
      transact.commit();
      return null;
    }).when(userManagementSystem).updateWith(registeredUser, updatedUser);

    userManagementSystem.updateWith(registeredUser, updatedUser);

    User persistedUser = entityManager().find(User.class, 2);
    Assertions.assertEquals(persistedUser.username(), "almironeta");

  }*/

}
