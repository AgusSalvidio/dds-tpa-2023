package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.persistence.EntityTransaction;

public class ServiceManagementSystemTest implements WithSimplePersistenceUnit {
  private State state() {
    return new State("OK", "Testing");
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
    entityManager().createQuery("DELETE FROM Service").executeUpdate();
    entityManager().createQuery("DELETE FROM Escalator").executeUpdate();
    entityManager().createQuery("DELETE FROM Elevator").executeUpdate();
    entityManager().createQuery("DELETE FROM Toilet").executeUpdate();
    entityManager().createQuery("DELETE FROM State").executeUpdate();
    transaction.commit();
    entityManager().close();
  }


  @Test
  @DisplayName("Start managing an Escalator")
  public void startManagingAnEscalatorTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Escalator escalator = Escalator.composedOf("Test", "Escalera", state);

    Mockito.doAnswer(invocation -> {
      EntityTransaction transaction = entityManager().getTransaction();

      Escalator obtainedEscalator = invocation.getArgument(0);

      State obtainedUserState = obtainedEscalator.getState();

      transaction.begin();
      entityManager().persist(obtainedUserState);
      entityManager().persist(obtainedEscalator);
      transaction.commit();
      return null;
    }).when(serviceManagementSystem).startManagingEscalator(escalator);

    serviceManagementSystem.startManagingEscalator(escalator);

    String jpql = "SELECT u FROM State u WHERE u.description = :description";

    State registeredState = entityManager().createQuery(jpql, State.class)
        .setParameter("description", "Testing")
        .getSingleResult();

    jpql = "SELECT u FROM Service u WHERE u.description = :description";

    Service registeredService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Escalera")
        .getSingleResult();

    Assertions.assertEquals(serviceManagementSystem.services().size(), 1);
    Assertions.assertEquals(serviceManagementSystem.services().get(0), registeredService);
    Assertions.assertEquals(serviceManagementSystem.services().get(0).getState(), registeredState);

  }

  @Test
  @DisplayName("Stop managing an Escalator")
  public void stopManagingAnEscalatorTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Escalator escalator = Escalator.composedOf("Test", "Escalera", state);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    entityManager().persist(escalator);
    transaction.commit();

    Assertions.assertEquals(serviceManagementSystem.services().size(), 1);

    String jpql = "SELECT u FROM Service u WHERE u.description = :description";

    Service registeredService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Escalera")
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Escalator obtainedEscalator = invocation.getArgument(0);

      transact.begin();
      entityManager().remove(obtainedEscalator);
      transact.commit();
      return null;
    }).when(serviceManagementSystem).stopManagingEscalator((Escalator) registeredService);

    serviceManagementSystem.stopManagingEscalator((Escalator) registeredService);

    Assertions.assertTrue(serviceManagementSystem.services().isEmpty());

  }

  @Test
  @DisplayName("Update an Escalator")
  public void updateAnEscalatorTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Escalator escalator = Escalator.composedOf("Test", "Escalera", state);
    Escalator updatedEscalator = Escalator.composedOf("Test", "Escalera trucada", state);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    entityManager().persist(escalator);
    transaction.commit();

    Assertions.assertEquals(serviceManagementSystem.services().size(), 1);

    String jpql = "SELECT u FROM Service u WHERE u.description = :description";

    Service registeredService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Escalera")
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Escalator obtainedEscalator = invocation.getArgument(0);
      Escalator obtainedUpdatedEscalator = invocation.getArgument(1);

      obtainedEscalator.synchronizeWith(obtainedUpdatedEscalator);

      transact.begin();
      entityManager().merge(obtainedEscalator);
      transact.commit();
      ;
      return null;
    }).when(serviceManagementSystem).updateWith(registeredService, updatedEscalator);

    serviceManagementSystem.updateWith(registeredService, updatedEscalator);

    Service persistedService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Escalera trucada")
        .getSingleResult();

    Assertions.assertEquals(persistedService.description(), "Escalera trucada");

  }

  @Test
  @DisplayName("Start managing an Elevator")
  public void startManagingAnElevatorTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Elevator elevator = Elevator.composedOf("Test", "Ascensor", state);

    Mockito.doAnswer(invocation -> {
      EntityTransaction transaction = entityManager().getTransaction();

      Elevator obtainedElevator = invocation.getArgument(0);

      State obtainedState = obtainedElevator.getState();

      transaction.begin();
      entityManager().persist(obtainedState);
      entityManager().persist(obtainedElevator);
      transaction.commit();
      return null;
    }).when(serviceManagementSystem).startManagingElevator(elevator);

    serviceManagementSystem.startManagingElevator(elevator);

    String jpql = "SELECT u FROM State u WHERE u.description = :description";

    State registeredState = entityManager().createQuery(jpql, State.class)
        .setParameter("description", "Testing")
        .getSingleResult();

    jpql = "SELECT u FROM Service u WHERE u.description = :description";

    Service registeredService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Ascensor")
        .getSingleResult();

    Assertions.assertEquals(serviceManagementSystem.services().size(), 1);
    Assertions.assertEquals(serviceManagementSystem.services().get(0), registeredService);
    Assertions.assertEquals(serviceManagementSystem.services().get(0).getState(), registeredState);

  }

  @Test
  @DisplayName("Stop managing an Elevator")
  public void stopManagingAnElevatorTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Elevator elevator = Elevator.composedOf("Test", "Ascensor", state);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    entityManager().persist(elevator);
    transaction.commit();

    Assertions.assertEquals(serviceManagementSystem.services().size(), 1);

    String jpql = "SELECT u FROM Service u WHERE u.description = :description";

    Service registeredService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Ascensor")
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Elevator obtainedElevator = invocation.getArgument(0);

      transact.begin();
      entityManager().remove(obtainedElevator);
      transact.commit();
      return null;
    }).when(serviceManagementSystem).stopManagingElevator((Elevator) registeredService);

    serviceManagementSystem.stopManagingElevator((Elevator) registeredService);

    Assertions.assertTrue(serviceManagementSystem.services().isEmpty());

  }

  @Test
  @DisplayName("Update an Elevator")
  public void updateAnElevatorTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Elevator elevator = Elevator.composedOf("Test", "Ascensor", state);
    Elevator updatedElevator = Elevator.composedOf("Test", "Ascensor trucado", state);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    entityManager().persist(elevator);
    transaction.commit();

    Assertions.assertEquals(serviceManagementSystem.services().size(), 1);

    String jpql = "SELECT u FROM Service u WHERE u.description = :description";

    Service registeredService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Ascensor")
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Elevator obtainedElevator = invocation.getArgument(0);
      Elevator obtainedUpdatedElevator = invocation.getArgument(1);

      obtainedElevator.synchronizeWith(obtainedUpdatedElevator);

      transact.begin();
      entityManager().merge(obtainedElevator);
      transact.commit();
      ;
      return null;
    }).when(serviceManagementSystem).updateWith(registeredService, updatedElevator);

    serviceManagementSystem.updateWith(registeredService, updatedElevator);

    Service persistedService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Ascensor trucado")
        .getSingleResult();

    Assertions.assertEquals(persistedService.description(), "Ascensor trucado");

  }

  @Test
  @DisplayName("Start managing an Toilet")
  public void startManagingAToiletTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Toilet toilet = Toilet.composedOf("Test", "Baño", state);

    Mockito.doAnswer(invocation -> {
      EntityTransaction transaction = entityManager().getTransaction();

      Toilet obtainedToilet = invocation.getArgument(0);

      State obtainedState = obtainedToilet.getState();

      transaction.begin();
      entityManager().persist(obtainedState);
      entityManager().persist(obtainedToilet);
      transaction.commit();
      return null;
    }).when(serviceManagementSystem).startManagingToilet(toilet);

    serviceManagementSystem.startManagingToilet(toilet);

    String jpql = "SELECT u FROM State u WHERE u.description = :description";

    State registeredState = entityManager().createQuery(jpql, State.class)
        .setParameter("description", "Testing")
        .getSingleResult();

    jpql = "SELECT u FROM Service u WHERE u.description = :description";

    Service registeredService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Baño")
        .getSingleResult();

    Assertions.assertEquals(serviceManagementSystem.services().size(), 1);
    Assertions.assertEquals(serviceManagementSystem.services().get(0), registeredService);
    Assertions.assertEquals(serviceManagementSystem.services().get(0).getState(), registeredState);

  }

  @Test
  @DisplayName("Stop managing a Toilet")
  public void stopManagingAToiletTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Toilet toilet = Toilet.composedOf("Test", "Baño", state);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    entityManager().persist(toilet);
    transaction.commit();

    Assertions.assertEquals(serviceManagementSystem.services().size(), 1);

    String jpql = "SELECT u FROM Service u WHERE u.description = :description";

    Service registeredService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Baño")
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Toilet obtainedToilet = invocation.getArgument(0);

      transact.begin();
      entityManager().remove(obtainedToilet);
      transact.commit();
      return null;
    }).when(serviceManagementSystem).stopManagingToilet((Toilet) registeredService);

    serviceManagementSystem.stopManagingToilet((Toilet) registeredService);

    Assertions.assertTrue(serviceManagementSystem.services().isEmpty());

  }

  @Test
  @DisplayName("Update toilet")
  public void updateToiletTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Toilet toilet = Toilet.composedOf("Test", "Baño", state);
    Toilet updatedToilet = Toilet.composedOf("Test", "Baño trucado", state);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    entityManager().persist(toilet);
    transaction.commit();

    Assertions.assertEquals(serviceManagementSystem.services().size(), 1);

    String jpql = "SELECT u FROM Service u WHERE u.description = :description";

    Service registeredService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Baño")
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Toilet obtainedToilet = invocation.getArgument(0);
      Toilet obtainedUpdatedToilet = invocation.getArgument(1);

      obtainedToilet.synchronizeWith(obtainedUpdatedToilet);

      transact.begin();
      entityManager().merge(obtainedToilet);
      transact.commit();
      ;
      return null;
    }).when(serviceManagementSystem).updateWith(registeredService, updatedToilet);

    serviceManagementSystem.updateWith(registeredService, updatedToilet);

    Service persistedService = entityManager().createQuery(jpql, Service.class)
        .setParameter("description", "Baño trucado")
        .getSingleResult();

    Assertions.assertEquals(persistedService.description(), "Baño trucado");

  }
}
