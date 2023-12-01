package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.notification.notificationmean.JakartaAdapter;
import ar.edu.utn.frba.dds.notification.notificationmean.NotificationMean;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByMail;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByWhatsApp;
import ar.edu.utn.frba.dds.notification.notificationmean.TwilioAdapter;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import ar.edu.utn.frba.dds.user.User;
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

  public void startManagingEscalator(Escalator anEscalator) {
    this.persistenceSystem().startManaging(anEscalator);
  }

  public void startManagingToilet(Toilet toilet) {
    this.persistenceSystem().startManaging(toilet);
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
      default -> {}
    }
  }

}
