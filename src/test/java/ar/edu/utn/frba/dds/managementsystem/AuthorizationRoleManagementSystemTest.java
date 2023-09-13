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

  @AfterEach
  public void cleanDataBase() {
    //Cleaning after running test
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();

    entityManager().createQuery("DELETE FROM AuthorizationRole ").executeUpdate();
    entityManager().createQuery("DELETE FROM User").executeUpdate();
    entityManager().createQuery("DELETE FROM UserDetail").executeUpdate();

    transaction.commit();
    entityManager().close();
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

    String jpql = "SELECT u FROM User u WHERE u.username = :username";

    User registeredUser = entityManager().createQuery(jpql, User.class)
        .setParameter("username", user.username())
        .getSingleResult();

    AuthorizationRole authorizationRole = AuthorizationRole.composedOf(registeredUser, "Administrador");

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      AuthorizationRole obtainedAuthorizationRole = invocation.getArgument(0);
      transact.begin();
      entityManager().persist(obtainedAuthorizationRole);
      transact.commit();
      return null;
    }).when(authorizationRoleManagementSystem).startManaging(authorizationRole);

    authorizationRoleManagementSystem.startManaging(authorizationRole);

    jpql = "SELECT u FROM AuthorizationRole u WHERE u.role = :role";

    AuthorizationRole registeredRole = entityManager().createQuery(jpql, AuthorizationRole.class)
        .setParameter("role", "Administrador")
        .getSingleResult();

    Assertions.assertEquals(authorizationRoleManagementSystem.roles().size(), 1);
    Assertions.assertEquals(authorizationRoleManagementSystem.roles().get(0), registeredRole);

  }

  @Test
  @DisplayName("Stop managing an authorizationRole")
  public void stopManagingAnAuthorizationRoleTest() throws Exception {

    AuthorizationRoleManagementSystem authorizationRoleManagementSystem =
        Mockito.spy(AuthorizationRoleManagementSystem.workingWith(this.persistenceSystem()));

    UserDetail userDetail = this.userDetails();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail);
    AuthorizationRole authorizationRole = AuthorizationRole.composedOf(user, "Administrador");

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(userDetail);
    entityManager().persist(user);
    entityManager().persist(authorizationRole);
    transaction.commit();
    entityManager().close();

    Assertions.assertEquals(authorizationRoleManagementSystem.roles().size(), 1);

    String jpql = "SELECT u FROM AuthorizationRole u WHERE u.role = :role";

    AuthorizationRole registeredRole = entityManager().createQuery(jpql, AuthorizationRole.class)
        .setParameter("role", "Administrador")
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      AuthorizationRole obtainedRole = invocation.getArgument(0);
      transact.begin();
      entityManager().remove(obtainedRole);
      transact.commit();
      return null;
    }).when(authorizationRoleManagementSystem).stopManaging(registeredRole);

    authorizationRoleManagementSystem.stopManaging(registeredRole);

    Assertions.assertTrue(authorizationRoleManagementSystem.roles().isEmpty());
  }

  @Test
  @DisplayName("Update an authorizationRole")
  public void updateAnAuthorizationRoleTest() throws Exception {


    AuthorizationRoleManagementSystem authorizationRoleManagementSystem =
        Mockito.spy(AuthorizationRoleManagementSystem.workingWith(this.persistenceSystem()));

    UserDetail userDetail = this.userDetails();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail);
    AuthorizationRole authorizationRole = AuthorizationRole.composedOf(user, "Administrador");

    AuthorizationRole updatedRole = AuthorizationRole.composedOf(user, "Seguridad");

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(userDetail);
    entityManager().persist(user);
    entityManager().persist(authorizationRole);
    transaction.commit();
    entityManager().close();

    String jpql = "SELECT u FROM AuthorizationRole u WHERE u.role = :role";

    AuthorizationRole registeredRole = entityManager().createQuery(jpql, AuthorizationRole.class)
        .setParameter("role", "Administrador")
        .getSingleResult();

    Assertions.assertEquals(authorizationRoleManagementSystem.roles().size(), 1);
    Assertions.assertEquals(registeredRole.role(), "Administrador");

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      AuthorizationRole obtainedRole = invocation.getArgument(0);

      AuthorizationRole obtainedUpdatedRole = invocation.getArgument(1);

      obtainedRole.synchronizeWith(obtainedUpdatedRole);

      transact.begin();
      entityManager().merge(obtainedRole);
      transact.commit();
      return null;
    }).when(authorizationRoleManagementSystem).updateWith(registeredRole, updatedRole);

    authorizationRoleManagementSystem.updateWith(registeredRole, updatedRole);

    AuthorizationRole persistedRole = entityManager().createQuery(jpql, AuthorizationRole.class)
        .setParameter("role", "Seguridad")
        .getSingleResult();

    Assertions.assertEquals(persistedRole.role(), "Seguridad");

  }

}
