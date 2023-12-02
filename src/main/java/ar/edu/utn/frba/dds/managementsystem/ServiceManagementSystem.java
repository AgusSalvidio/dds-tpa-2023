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

  public List<Object> states() {
    return this.persistenceSystem.objectList(State.class.getName());
  }

  public State stateIdentifiedBy(Integer stateId) {
    return this.persistenceSystem.stateIdentifiedBy(stateId);
  }

  public State stateNamed(String stateName) {
    return this.persistenceSystem.stateNamed(stateName);
  }

  public void startManagingElevator(Elevator anElevator) {
    this.persistenceSystem().startManaging(anElevator);
  }

  public void updateElevatorWith(Elevator anElevator) {
    this.persistenceSystem().updateManaging(anElevator);
  }

  public void startManagingEscalator(Escalator anEscalator) {
    this.persistenceSystem().startManaging(anEscalator);
  }

  public void updateEscalatorWith(Escalator anEscalator) {
    this.persistenceSystem().updateManaging(anEscalator);
  }

  public void startManagingToilet(Toilet toilet) {
    this.persistenceSystem().startManaging(toilet);
  }

  public void updateToiletWith(Toilet toilet) {
    this.persistenceSystem().updateManaging(toilet);
  }

  public void stopManagingService(Service service) {
    this.persistenceSystem().stopManaging(service);
  }

  public void updateServiceWith(Service service) {
    this.persistenceSystem().updateManaging(service);
  }

  public List<Object> services() {
    return this.persistenceSystem.objectList(Service.class.getName());
  }

  public Service serviceIdentifiedBy(Integer serviceId) {
    return this.persistenceSystem.serviceIdentifiedBy(serviceId);
  }

  public Service serviceNamed(String serviceName) {
    return this.persistenceSystem.serviceNamed(serviceName);
  }

  public void updateServiceFrom(Service serviceToUpdate, Map model) {
    String name = model.get("name").toString();
    String description = model.get("description").toString();
    Integer stateId = Integer.valueOf(model.get("state").toString());
    State state = this.stateIdentifiedBy(stateId);

    String serviceType = serviceToUpdate.getClass().getSimpleName();

    switch (serviceType) {
      case "Elevator" -> {
        Elevator updatedElevator = Elevator.composedOf(name, description, state);
        updatedElevator.setId(serviceToUpdate.getId());
        this.updateServiceWith(updatedElevator);
      }
      case "Escalator" -> {
        Escalator updatedEscalator = Escalator.composedOf(name, description, state);
        updatedEscalator.setId(serviceToUpdate.getId());
        this.updateServiceWith(updatedEscalator);
      }
      case "Toilet" -> {
        Toilet updatedToilet = Toilet.composedOf(name, description, state);
        updatedToilet.setId(serviceToUpdate.getId());
        this.updateServiceWith(updatedToilet);
      }
      default -> {
      }
    }
  }

  public void startManagingServiceFrom(Map model) {
    String name = model.get("name").toString();
    String description = model.get("description").toString();
    String serviceType = model.get("serviceType").toString();
    Integer stateId = Integer.valueOf(model.get("state").toString());
    State state = this.stateIdentifiedBy(stateId);

    switch (serviceType) {
      case "elevator" ->
          this.startManagingElevator(Elevator.composedOf(name, description, state));
      case "escalator" ->
          this.startManagingEscalator(Escalator.composedOf(name, description, state));
      case "toilet" ->
          this.startManagingToilet(Toilet.composedOf(name, description, state));
      default -> {
      }
    }
  }
}
