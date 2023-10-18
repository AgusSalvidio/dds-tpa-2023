package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.addons.notificationcreationaddon.NotificationMeanCreationAddOn;
import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserManagementSystemTest implements WithSimplePersistenceUnit {

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

  @AfterEach
  public void cleanDataBase() {
    //Cleaning after running test
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().createQuery("DELETE FROM User").executeUpdate();
    entityManager().createQuery("DELETE FROM UserDetail").executeUpdate();
    transaction.commit();
    entityManager().close();
  }

  @Test
  @DisplayName("Start managing an user")
  public void startManagingAnUserTest() throws Exception {

    UserManagementSystem userManagementSystem = Mockito.spy(UserManagementSystem.workingWith(this.persistenceSystem()));
    UserDetail userDetail = this.userDetails();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail, AuthorizationRole.ADMINISTRADOR);

    Mockito.doAnswer(invocation -> {
      EntityTransaction transaction = entityManager().getTransaction();

      User obtainedUser = invocation.getArgument(0);

      UserDetail obtainedUserDetail = obtainedUser.getDetails();

      transaction.begin();
      entityManager().persist(obtainedUserDetail);
      entityManager().persist(obtainedUser);
      transaction.commit();
      entityManager().close();
      return null;
    }).when(userManagementSystem).startManaging(user);

    userManagementSystem.startManaging(user);

    String jpql = "SELECT u FROM User u WHERE u.username = :username";

    User registeredUser = entityManager().createQuery(jpql, User.class)
        .setParameter("username", user.username())
        .getSingleResult();

    Assertions.assertEquals(userManagementSystem.users().size(), 1);
    Assertions.assertEquals(userManagementSystem.users().get(0), registeredUser);
    Assertions.assertEquals(userManagementSystem.users().get(0).getDetails(), registeredUser.getDetails());

  }


  @Test
  @DisplayName("Stop managing an user")
  public void stopManagingAnUserTest() throws Exception {

    UserManagementSystem userManagementSystem = Mockito.spy(UserManagementSystem.workingWith(this.persistenceSystem()));
    UserDetail userDetail = this.userDetails();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail,AuthorizationRole.ADMINISTRADOR);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(userDetail);
    entityManager().persist(user);
    transaction.commit();

    Assertions.assertEquals(userManagementSystem.users().size(), 1);

    String jpql = "SELECT u FROM User u WHERE u.username = :username";

    User registeredUser = entityManager().createQuery(jpql, User.class)
        .setParameter("username", user.username())
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      User obtainedUser = invocation.getArgument(0);

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
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail,AuthorizationRole.ADMINISTRADOR);
    User updatedUser = User.composedOf("almironeta", "theBestPassword", userDetail,AuthorizationRole.ADMINISTRADOR);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(userDetail);
    entityManager().persist(user);
    transaction.commit();

    String jpql = "SELECT u FROM User u WHERE u.username = :username";

    User registeredUser = entityManager().createQuery(jpql, User.class)
        .setParameter("username", user.username())
        .getSingleResult();

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

    User persistedUser = entityManager().createQuery(jpql, User.class)
        .setParameter("username", "almironeta")
        .getSingleResult();

    Assertions.assertEquals(persistedUser.username(), "almironeta");

  }

  @Test
  @DisplayName("Start managing an userDetail")
  public void startManagingAnUserDetailTest() throws Exception {

    UserManagementSystem userManagementSystem = Mockito.spy(UserManagementSystem.workingWith(this.persistenceSystem()));
    UserDetail userDetail = this.userDetails();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transaction = entityManager().getTransaction();

      UserDetail obtainedUserDetail = invocation.getArgument(0);

      transaction.begin();
      entityManager().persist(obtainedUserDetail);
      transaction.commit();
      entityManager().close();
      return null;
    }).when(userManagementSystem).startManagingDetail(userDetail);

    userManagementSystem.startManagingDetail(userDetail);

    String jpql = "SELECT u FROM UserDetail u WHERE u.name = :name";

    UserDetail registeredUserDetail = entityManager().createQuery(jpql, UserDetail.class)
        .setParameter("name", userDetail.name())
        .getSingleResult();

    Assertions.assertEquals(userManagementSystem.userDetails().size(), 1);
    Assertions.assertEquals(userManagementSystem.userDetails().get(0), registeredUserDetail);

  }


  @Test
  @DisplayName("Stop managing an userDetail")
  public void stopManagingAnUserDetailTest() throws Exception {

    UserManagementSystem userManagementSystem = Mockito.spy(UserManagementSystem.workingWith(this.persistenceSystem()));
    UserDetail userDetail = this.userDetails();

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(userDetail);
    transaction.commit();

    Assertions.assertEquals(userManagementSystem.userDetails().size(), 1);

    String jpql = "SELECT u FROM UserDetail u WHERE u.name = :name";

    UserDetail registeredUserDetail = entityManager().createQuery(jpql, UserDetail.class)
        .setParameter("name", userDetail.name())
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      UserDetail obtainedUserDetail = invocation.getArgument(0);

      transact.begin();
      entityManager().remove(obtainedUserDetail);
      transact.commit();
      return null;
    }).when(userManagementSystem).stopManagingDetail(registeredUserDetail);

    userManagementSystem.stopManagingDetail(registeredUserDetail);
    Assertions.assertTrue(userManagementSystem.userDetails().isEmpty());

  }

  @Test
  @DisplayName("Update an userDetail")
  public void updateAnUserDetailTest() throws Exception {

    UserManagementSystem userManagementSystem = Mockito.spy(UserManagementSystem.workingWith(this.persistenceSystem()));
    UserDetail userDetail = this.userDetails();
    UserDetail updatedUserDetail = new UserDetail("Pepe", "Sand", "elpepe@gmail.com", "666666",
        new NotificationMeanCreationAddOn().wpp());

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(userDetail);
    transaction.commit();

    String jpql = "SELECT u FROM UserDetail u WHERE u.name = :name";

    UserDetail registeredUserDetail = entityManager().createQuery(jpql, UserDetail.class)
        .setParameter("name", userDetail.name())
        .getSingleResult();

    Assertions.assertEquals(userManagementSystem.userDetails().size(), 1);
    Assertions.assertEquals(registeredUserDetail.name(), "Hugo");

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      UserDetail obtainedUserDetail = invocation.getArgument(0);

      UserDetail obtainedUpdatedUserDetail = invocation.getArgument(1);

      obtainedUserDetail.synchronizeWith(obtainedUpdatedUserDetail);

      transact.begin();
      entityManager().merge(obtainedUserDetail);
      transact.commit();
      return null;
    }).when(userManagementSystem).updateDetailWith(registeredUserDetail, updatedUserDetail);

    userManagementSystem.updateDetailWith(registeredUserDetail, updatedUserDetail);

    UserDetail persistedUserDetail = entityManager().createQuery(jpql, UserDetail.class)
        .setParameter("name", "Pepe")
        .getSingleResult();

    Assertions.assertEquals(persistedUserDetail.name(), "Pepe");

  }
}
