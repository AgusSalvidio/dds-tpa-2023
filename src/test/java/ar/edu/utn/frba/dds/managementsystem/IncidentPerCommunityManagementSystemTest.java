package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.addons.notificationcreationaddon.NotificationMeanCreationAddOn;
import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
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

public class IncidentPerCommunityManagementSystemTest implements WithSimplePersistenceUnit {

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

  private Community community() {
    return Community.composedOf("Comunidad 1", "Comunidad de prueba");
  }

  private Community communityTwo() {
    return Community.composedOf("Comunidad 2", "Comunidad de prueba");
  }

  @AfterEach
  public void cleanDataBase() {
    //Cleaning after running test
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().createQuery("DELETE FROM IncidentPerCommunity").executeUpdate();
    entityManager().createQuery("DELETE FROM Incident ").executeUpdate();
    entityManager().createQuery("DELETE FROM Service").executeUpdate();
    entityManager().createQuery("DELETE FROM Escalator").executeUpdate();
    entityManager().createQuery("DELETE FROM Elevator").executeUpdate();
    entityManager().createQuery("DELETE FROM Toilet").executeUpdate();
    entityManager().createQuery("DELETE FROM State").executeUpdate();
    entityManager().createQuery("DELETE FROM User").executeUpdate();
    entityManager().createQuery("DELETE FROM UserDetail").executeUpdate();
    entityManager().createQuery("DELETE FROM Community").executeUpdate();
    transaction.commit();
    entityManager().close();
  }

  @Test
  @DisplayName("Start managing an incidentPerCommunity")
  public void startManagingAnIncidentPerCommunityTest() throws Exception {
    IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem = Mockito.spy(IncidentPerCommunityManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Elevator elevator = Elevator.composedOf("Ascensor", "Ascensor Principal", state);
    UserDetail userDetail = this.userDetail();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail, AuthorizationRole.ADMINISTRADOR);
    LocalDateTime dateTime = this.dateTime();
    Incident incident = Incident.composedOf(elevator, "bla", dateTime, user);

    Community community = this.community();

    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(incident, community);
    incidentPerCommunity.setState(state);

    Mockito.doAnswer(invocation -> {
      EntityTransaction transaction = entityManager().getTransaction();

      IncidentPerCommunity obtainedIncidentPerCommunity = invocation.getArgument(0);
      Community obtainedCommunity = obtainedIncidentPerCommunity.community();
      Incident obtainedIncident = obtainedIncidentPerCommunity.incident();
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
      entityManager().persist(obtainedCommunity);
      entityManager().persist(obtainedIncidentPerCommunity);
      transaction.commit();
      entityManager().close();
      return null;
    }).when(incidentPerCommunityManagementSystem).startManaging(incidentPerCommunity);

    incidentPerCommunityManagementSystem.startManaging(incidentPerCommunity);

    String jpql = "SELECT i FROM IncidentPerCommunity i WHERE i.community = :community";

    IncidentPerCommunity registeredIncidentPerCommunity = entityManager().createQuery(jpql, IncidentPerCommunity.class)
        .setParameter("community", incidentPerCommunity.community())
        .getSingleResult();

    /*Assertions.assertEquals(incidentPerCommunityManagementSystem.incidentsPerCommunity().size(), 1);
    Assertions.assertEquals(incidentPerCommunityManagementSystem.incidentsPerCommunity().get(0), registeredIncidentPerCommunity);
    Assertions.assertEquals(incidentPerCommunityManagementSystem.incidentsPerCommunity().get(0).community(), registeredIncidentPerCommunity.community());*/
  }

  @Test
  @DisplayName("Stop managing an incidentPerCommunity")
  public void stopManagingAnIncidentPerCommunityTest() throws Exception {

    IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem = Mockito.spy(IncidentPerCommunityManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Elevator elevator = Elevator.composedOf("Ascensor", "Ascensor Principal", state);
    UserDetail userDetail = this.userDetail();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail, AuthorizationRole.ADMINISTRADOR);
    LocalDateTime dateTime = this.dateTime();
    Incident incident = Incident.composedOf(elevator, "bla", dateTime, user);

    Community community = this.community();

    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(incident, community);
    incidentPerCommunity.setState(state);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    entityManager().persist(elevator);
    entityManager().persist(userDetail);
    entityManager().persist(user);
    entityManager().persist(incident);
    entityManager().persist(community);
    entityManager().persist(incidentPerCommunity);
    transaction.commit();

    //Assertions.assertEquals(incidentPerCommunityManagementSystem.incidentsPerCommunity().size(), 1);

    String jpql = "SELECT i FROM IncidentPerCommunity i WHERE i.community = :community";

    IncidentPerCommunity registeredIncidentPerCommunity = entityManager().createQuery(jpql, IncidentPerCommunity.class)
        .setParameter("community", incidentPerCommunity.community())
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      IncidentPerCommunity obtainedIncidentPerCommunity = invocation.getArgument(0);

      transact.begin();
      entityManager().remove(obtainedIncidentPerCommunity);
      transact.commit();
      return null;
    }).when(incidentPerCommunityManagementSystem).stopManaging(registeredIncidentPerCommunity);

    incidentPerCommunityManagementSystem.stopManaging(registeredIncidentPerCommunity);
    //Assertions.assertTrue(incidentPerCommunityManagementSystem.incidentsPerCommunity().isEmpty());
  }

  @Test
  @DisplayName("Update an incidentPerCommunity")
  public void updateAnIncidentPerCommunityTest() throws Exception {

    IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem = Mockito.spy(IncidentPerCommunityManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Elevator elevator = Elevator.composedOf("Ascensor", "Ascensor Principal", state);
    UserDetail userDetail = this.userDetail();
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", userDetail, AuthorizationRole.ADMINISTRADOR);
    LocalDateTime dateTime = this.dateTime();
    Incident incident = Incident.composedOf(elevator, "bla", dateTime, user);
    Community community = this.community();
    Community updateCommunity = this.communityTwo();

    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(incident, community);
    incidentPerCommunity.setState(state);

    IncidentPerCommunity updateIncidentPerCommunity = IncidentPerCommunity.composedOf(incident, updateCommunity);
    updateIncidentPerCommunity.setState(state);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    entityManager().persist(elevator);
    entityManager().persist(userDetail);
    entityManager().persist(user);
    entityManager().persist(incident);
    entityManager().persist(community);
    entityManager().persist(updateCommunity);
    entityManager().persist(incidentPerCommunity);
    transaction.commit();

    String jpql = "SELECT i FROM IncidentPerCommunity i WHERE i.community = :community";

    IncidentPerCommunity registeredIncidentPerCommunity = entityManager().createQuery(jpql, IncidentPerCommunity.class)
        .setParameter("community", incidentPerCommunity.community())
        .getSingleResult();

    /*Assertions.assertEquals(incidentPerCommunityManagementSystem.incidentsPerCommunity().size(), 1);
    Assertions.assertEquals(registeredIncidentPerCommunity.community().name(), "Comunidad 1");*/

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      IncidentPerCommunity obtainedIncidentPerCommunity = invocation.getArgument(0);
      IncidentPerCommunity obtainedUpdateIncidentPerCommunity = invocation.getArgument(1);

      obtainedIncidentPerCommunity.synchronizeWith(obtainedUpdateIncidentPerCommunity);

      transact.begin();
      entityManager().merge(obtainedIncidentPerCommunity);
      transact.commit();
      return null;
    }).when(incidentPerCommunityManagementSystem).updateWith(registeredIncidentPerCommunity, updateIncidentPerCommunity);

    incidentPerCommunityManagementSystem.updateWith(registeredIncidentPerCommunity, updateIncidentPerCommunity);

    IncidentPerCommunity persistedIncidentPerCommunity = entityManager().createQuery(jpql, IncidentPerCommunity.class)
        .setParameter("community", updateIncidentPerCommunity.community())
        .getSingleResult();

    //Assertions.assertEquals(persistedIncidentPerCommunity.community().name(), "Comunidad 2");
  }

}
