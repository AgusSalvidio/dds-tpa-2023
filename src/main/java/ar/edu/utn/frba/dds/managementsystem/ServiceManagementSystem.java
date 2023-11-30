package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import java.util.List;
import java.util.Map;

public class ServiceManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public ServiceManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Servicios";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public void startManagingState(State state) {
    this.persistenceSystem().startManaging(state);
  }

  public void stopManagingState(State state) {
    this.persistenceSystem().stopManaging(state);
  }

  public void startManagingElevator(Elevator anElevator) {
    this.persistenceSystem().startManaging(anElevator);
  }

  public void stopManagingElevator(Elevator anElevator) {
    this.persistenceSystem().stopManaging(anElevator);
  }

  public void startManagingEscalator(Escalator anEscalator) {
    this.persistenceSystem().startManaging(anEscalator);
  }

  public void stopManagingEscalator(Escalator anEscalator) {
    this.persistenceSystem().stopManaging(anEscalator);
  }

  public void startManagingToilet(Toilet toilet) {
    this.persistenceSystem().startManaging(toilet);
  }

  public void stopManagingToilet(Toilet toilet) {
    this.persistenceSystem().stopManaging(toilet);
  }

  public void updateWith(Service service) {
    this.persistenceSystem().updateManaging(service);
  }

  public List<Object> services() {
    return this.persistenceSystem.objectList(Service.class.getName());
  }

  public void startManagingServiceFrom(Map model) {
    String name = model.get("name").toString();
    String description = model.get("description").toString();
    String stateName = model.get("state-name").toString();
    String stateDescription = model.get("state-description").toString();
    String serviceType = model.get("service-type").toString();

    State state = new State(stateName, stateDescription);
    this.startManagingState(state);

    switch (serviceType) {
      case "ELEVADOR" ->
              this.startManagingElevator(Elevator.composedOf(name, description, state));
      case "ESCALERA" ->
          this.startManagingEscalator(Escalator.composedOf(name, description, state));
      case "TOILET" ->
              this.startManagingToilet(Toilet.composedOf(name, description, state));
      default -> {
      }
    }
  }

}
