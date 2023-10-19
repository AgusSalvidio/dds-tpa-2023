package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.addons.notificationcreationaddon.NotificationMeanCreationAddOn;
import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class IncidentManagementSystemTest implements WithSimplePersistenceUnit {

  private MemoryBasedPersistenceSystem persistenceSystem() throws Exception {
    return this.relationalDatabasePersistenceSystem();
  }

  private MemoryBasedPersistenceSystem relationalDatabasePersistenceSystem() throws Exception {
    return new MemoryBasedPersistenceSystem();
  }

  private State state() {
    return State.composedOf("IN_SERVICE", "Normally working Service");
  }

  private LocalDateTime dateTime() {
    return LocalDateTime.of(2023, 7, 2, 23, 40);
  }

  private UserDetail userDetail() throws Exception {
    return UserDetail.composedOf("Hugo", "Ibarra", "ibarraneta@gmail.com", "0123456789",
        new NotificationMeanCreationAddOn().wpp());
  }

  @AfterEach
  public void cleanDataBase() {
    //Cleaning after running test
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().createQuery("DELETE FROM Incident").executeUpdate();
    entityManager().createQuery("DELETE FROM Service").executeUpdate();
    entityManager().createQuery("DELETE FROM Escalator").executeUpdate();
    entityManager().createQuery("DELETE FROM Elevator").executeUpdate();
    entityManager().createQuery("DELETE FROM Toilet").executeUpdate();
    entityManager().createQuery("DELETE FROM State").executeUpdate();
    entityManager().createQuery("DELETE FROM User").executeUpdate();
    entityManager().createQuery("DELETE FROM UserDetail").executeUpdate();
    transaction.commit();
    entityManager().close();
  }

  @Test
  @DisplayName("Start managing an incident")
  public void startManagingAnIncidentTest() throws Exception {

    IncidentManagementSystem incidentManagementSystem = Mockito.spy(IncidentManagementSystem.workingWith(this.persistenceSystem()));

    State state = this.state();
    Elevator elevator = Elevator.composedOf("Ascensor", "Ascensor Principal", state);
    UserDetail userDetail = this.userDetail();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail, AuthorizationRole.ADMINISTRADOR);
    LocalDateTime dateTime = this.dateTime();
    Incident incident = Incident.composedOf(elevator, "bla", dateTime, user);

    Mockito.doAnswer(invocation -> {
      EntityTransaction transaction = entityManager().getTransaction();

      Incident obtainedIncident = invocation.getArgument(0);
      Service obtainedService = obtainedIncident.service();
      State obtainedState = obtainedService.state();
      User obtainedUser = obtainedIncident.user();
      UserDetail obtainedUserDetail = obtainedUser.getDetails();

      transaction.begin();
      entityManager().persist(obtainedState);
      entityManager().persist(obtainedService);
      entityManager().persist(obtainedUserDetail);
      entityManager().persist(obtainedUser);
      entityManager().persist(obtainedIncident);
      transaction.commit();
      entityManager().close();
      return null;
    }).when(incidentManagementSystem).startManaging(incident);

    incidentManagementSystem.startManaging(incident);

    String jpql = "SELECT inc FROM Incident inc WHERE inc.observations = :observations";

    Incident registeredIncident = entityManager().createQuery(jpql, Incident.class)
        .setParameter("observations", "bla")
        .getSingleResult();

    /*Assertions.assertEquals(incidentManagementSystem.incidents().size(), 1);
    Assertions.assertEquals(incidentManagementSystem.incidents().get(0), registeredIncident);
    Assertions.assertEquals(incidentManagementSystem.incidents().get(0).observations(), registeredIncident.observations());*/

  }

  @Test
  @DisplayName("Stop managing an incident")
  public void stopManagingAnIncidentTest() throws Exception {

    IncidentManagementSystem incidentManagementSystem = Mockito.spy(IncidentManagementSystem.workingWith(this.persistenceSystem()));

    State state = this.state();
    Elevator elevator = Elevator.composedOf("Ascensor", "Ascensor Principal", state);
    UserDetail userDetail = this.userDetail();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail, AuthorizationRole.ADMINISTRADOR);
    LocalDateTime dateTime = this.dateTime();
    Incident incident = Incident.composedOf(elevator, "bla", dateTime, user);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    entityManager().persist(elevator);
    entityManager().persist(userDetail);
    entityManager().persist(user);
    entityManager().persist(incident);
    transaction.commit();

    //Assertions.assertEquals(incidentManagementSystem.incidents().size(), 1);

    String jpql = "SELECT i FROM Incident i WHERE i.observations = :observations";

    Incident registeredIncident = entityManager().createQuery(jpql, Incident.class)
        .setParameter("observations", incident.observations())
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Incident obtainedIncident = invocation.getArgument(0);

      transact.begin();
      entityManager().remove(obtainedIncident);
      transact.commit();
      return null;
    }).when(incidentManagementSystem).stopManaging(registeredIncident);

    incidentManagementSystem.stopManaging(registeredIncident);
    //Assertions.assertTrue(incidentManagementSystem.incidents().isEmpty());

  }

  @Test
  @DisplayName("Update an incident")
  public void updateAnIncidentTest() throws Exception {
    IncidentManagementSystem incidentManagementSystem = Mockito.spy(IncidentManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Elevator elevator = Elevator.composedOf("Ascensor", "Ascensor Principal", state);
    UserDetail userDetail = this.userDetail();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail, AuthorizationRole.ADMINISTRADOR);
    LocalDateTime dateTime = this.dateTime();
    Incident incident = Incident.composedOf(elevator, "bla", dateTime, user);
    Incident updateIncident = Incident.composedOf(elevator, "blabla", dateTime, user);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    entityManager().persist(elevator);
    entityManager().persist(userDetail);
    entityManager().persist(user);
    entityManager().persist(incident);
    transaction.commit();

    String jpql = "SELECT i FROM Incident i WHERE i.observations = :observations";

    Incident registeredIncident = entityManager().createQuery(jpql, Incident.class)
        .setParameter("observations", incident.observations())
        .getSingleResult();

    /*Assertions.assertEquals(incidentManagementSystem.incidents().size(), 1);
    Assertions.assertEquals(registeredIncident.observations(), "bla");*/

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Incident obtainedIncident = invocation.getArgument(0);
      Incident obtainedUpdateIncident = invocation.getArgument(1);

      obtainedIncident.synchronizeWith(obtainedUpdateIncident);

      transact.begin();
      entityManager().merge(obtainedIncident);
      transact.commit();
      return null;
    }).when(incidentManagementSystem).updateWith(registeredIncident, updateIncident);

    incidentManagementSystem.updateWith(registeredIncident, updateIncident);

    Incident persistedIncident = entityManager().createQuery(jpql, Incident.class)
        .setParameter("observations", "blabla")
        .getSingleResult();

    //Assertions.assertEquals(persistedIncident.observations(), "blabla");
  }
}
