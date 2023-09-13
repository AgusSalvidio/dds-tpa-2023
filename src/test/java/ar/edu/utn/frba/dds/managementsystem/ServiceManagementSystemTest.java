package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
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

  @Test
  @DisplayName("Start managing an Escalator")
  public void startManagingAnEscalatorTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Escalator escalator = Escalator.composedOf("Test", "Test", state);

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
    State registeredState = entityManager().find(State.class, 1);
    Service registeredService = entityManager().find(Service.class, 2);

    Assertions.assertEquals(serviceManagementSystem.services().size(), 1);
    Assertions.assertEquals(serviceManagementSystem.services().get(0), registeredService);
    Assertions.assertEquals(serviceManagementSystem.services().get(0).getState(), registeredState);

  }

  @Test
  @DisplayName("Start managing an Elevator")
  public void startManagingAnElevatorTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Elevator elevator = Elevator.composedOf("Test", "Test", state);

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
    State registeredState = entityManager().find(State.class, 1);
    Service registeredService = entityManager().find(Service.class, 2);

    Assertions.assertEquals(serviceManagementSystem.services().size(), 2);
    Assertions.assertEquals(serviceManagementSystem.services().get(0), registeredService);
    Assertions.assertEquals(serviceManagementSystem.services().get(0).getState(), registeredState);

  }

  @Test
  @DisplayName("Start managing an Toilet")
  public void startManagingAToiletTest() throws Exception {

    ServiceManagementSystem serviceManagementSystem = Mockito.spy(ServiceManagementSystem.workingWith(this.persistenceSystem()));
    State state = this.state();
    Toilet toilet = Toilet.composedOf("Test", "Test", state);

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
    State registeredState = entityManager().find(State.class, 1);
    Service registeredService = entityManager().find(Service.class, 2);

    Assertions.assertEquals(serviceManagementSystem.services().size(), 3);
    Assertions.assertEquals(serviceManagementSystem.services().get(0), registeredService);
    Assertions.assertEquals(serviceManagementSystem.services().get(0).getState(), registeredState);

  }
}
