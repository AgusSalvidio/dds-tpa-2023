package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.notification.notificationmean.NotificationType;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.*;
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

  public static ServiceManagementSystem workingWith(
          RelationalDatabasePersistenceSystem persistenceSystem) {
    return new ServiceManagementSystem(persistenceSystem);
  }

  public void startManagingElevator(Elevator anElevator) {
    this.persistenceSystem().startManagingElevator(anElevator);
  }

  public void startManagingEscalator(Escalator anEscalator) {
    this.persistenceSystem().startManagingEscalator(anEscalator);
  }

  public void startManagingToilet(Toilet toilet) {
    this.persistenceSystem().startManagingToilet(toilet);
  }

  public void stopManagingEscalator(Escalator anEscalator) {
    this.persistenceSystem().stopManagingEscalator(anEscalator);
  }

  public void stopManagingElevator(Elevator anElevator) {
    this.persistenceSystem().stopManagingElevator(anElevator);
  }

  public void stopManagingToilet(Toilet toilet) {
    this.persistenceSystem().stopManagingToilet(toilet);
  }

  public void startManagingState(State state) {
    this.persistenceSystem().startManagingState(state);
  }

  public void stopManagingState(State state) {
    this.persistenceSystem().stopManagingState(state);
  }

  public List<Service> services() {
    return this.persistenceSystem.services();
  }

  public void updateWith(Service currentService, Service updateService) {
    currentService.synchronizeWith(updateService);
  }

  public void startManagingServiceFrom(Map model) {
    String name = model.get("name").toString();
    String description = model.get("description").toString();
    String stateName = model.get("state-name").toString();
    String stateDescription = model.get("state-description").toString();
    String serviceType = model.get("service-type").toString();
    ServiceType servieType = ServiceType.valueOf(model.get("servicestype").toString());

    State state = new State(stateName, stateDescription);
    this.startManagingState(state);

    switch (serviceType) {
      case "ELEVADOR" -> this.startManagingElevator(Elevator.composedOf(name, description, state));
      case "ESCALERA" ->
          this.startManagingEscalator(Escalator.composedOf(name, description, state));
      case "TOILET" -> this.startManagingToilet(Toilet.composedOf(name, description, state));
      default -> {
      }
    }
  }

}
