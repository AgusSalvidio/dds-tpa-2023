package ar.edu.utn.frba.dds.persistencesystem;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class MemoryBasedPersistenceSystem implements PersistenceSystem {

  List<StorageAssignment> memoryStorage = new ArrayList<>();



  public void addObjectTypeToStore(String anObjectClassName) {
    this.memoryStorage.add(new StorageAssignment(anObjectClassName));
  }

  public void storeObjectTyped(String anObjectClassName, Object anObject) {
    StorageAssignment selectedStorageAssignment = this.storageAssignmentFor(anObjectClassName);
    selectedStorageAssignment.store(anObject);
  }

  public void removeObjectTyped(String anObjectClassName, Object anObject) {
    StorageAssignment storageAssignment = this.storageAssignmentFor(anObjectClassName);
    storageAssignment.remove(anObject);

  }

  public Object findObjectTyped(String anObjectClassName, Object anObject) {
    try {
      List<Object> obtainedObjects = this.objectsFrom(anObjectClassName);
      return obtainedObjects.get(obtainedObjects.indexOf(anObject));
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }

  }

  public StorageAssignment storageAssignmentFor(String anObjectClassName) {
    try {
      List<Object> obtainedStorageAssignmentList =
          this.memoryStorage.stream()
              .filter(storageAssignment -> storageAssignment.className()
                  .equals(anObjectClassName))
              .collect(Collectors.toList());
      return (StorageAssignment) obtainedStorageAssignmentList.get(0);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public List<Object> objectsFrom(String anObjectClassName) {
    return storageAssignmentFor(anObjectClassName).objectList();
  }

  public void startManagingUser(User anUser) {
    //TODO
  }

  public void stopManagingUser(User anUser) {
    //TODO
  }

  public void startManagingUserDetail(UserDetail anUserDetail) {
    //TODO
  }

  public void stopManagingUserDetail(UserDetail anUserDetail) {
    //TODO
  }

  public List<User> users() {
    //TODO
    return null;
  }

  public User userIdentifiedBy(Integer anUserId) {
    //TODO
    return null;
  }

  public User userNamed(String anUserName) {
    //TODO
    return null;
  }

  public List<UserDetail> userDetails() {
    //TODO
    return null;
  }

  public void startManagingElevator(Elevator anElevator) {
    //TODO
  }

  public void startManagingEscalator(Escalator anEscalator) {
    //TODO
  }

  public void startManagingToilet(Toilet toilet) {
    //TODO
  }

  public void stopManagingEscalator(Escalator anEscalator) {
    //TODO
  }

  public void stopManagingElevator(Elevator anElevator) {
    //TODO
  }

  public void stopManagingToilet(Toilet toilet) {
    //TODO
  }

  public void startManagingState(State state) {
    //TODO
  }

  public void stopManagingState(State state) {
    //TODO
  }

  public List<Service> services() {
    //TODO
    return null;
  }

  public void startManagingAuthorizationRole(AuthorizationRole authorizationRole) {
    //TODO
  }

  public void stopManagingAuthorizationRole(AuthorizationRole authorizationRole) {
    //TODO
  }

  public List<AuthorizationRole> roles() {
    //TODO
    return null;
  }

  public void startManagingIncident(Incident anIncident) {
    //TODO
  }

  public void stopManagingIncident(Incident anIncident) {
    //TODO
  }

  public List<Incident> incidents() {
    //TODO
    return null;
  }

  public void startManagingTransportLine(TransportLine transportLine) {
    //TODO
  }

  public void stopManagingTransportLine(TransportLine transportLine) {
    //TODO
  }

  public List<Entity> entities() {
    //TODO
    return null;
  }

  public void startManagingCommunity(Community community) {
    //TODO
  }

  public void stopManagingCommunity(Community community) {
    //TODO
  }

  public List<Community> communities() {
    //TODO
    return null;
  }

  public void startManagingIncidentPerCommunity(IncidentPerCommunity anIncidentPerCommunity) {
    //TODO
  }

  public void stopManagingIncidentPerCommunity(IncidentPerCommunity anIncidentPerCommunity) {
    //TODO
  }

  public List<IncidentPerCommunity> incidentsPerCommunity() {
    //TODO
    return null;
  }

}
