package ar.edu.utn.frba.dds.persistencesystem;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.demo.Demo;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.ranking.AverageClosingTimeRanking;
import ar.edu.utn.frba.dds.ranking.GreaterDegreeOfImpactRanking;
import ar.edu.utn.frba.dds.ranking.MostReportedIncidentsRanking;
import ar.edu.utn.frba.dds.ranking.WeeklyRanking;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import ar.edu.utn.frba.dds.serviceholder.ServiceHolder;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MemoryBasedPersistenceSystem implements PersistenceSystem {

  List<StorageAssignment> memoryStorage = new ArrayList<>();

  Demo demo = new Demo();

  public MemoryBasedPersistenceSystem() throws Exception {
    this.demo.initialize();
  }

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
    Integer generatedId;
    if (!this.demo.users().isEmpty()) {
      User lastUser = this.demo.users().get(this.demo.users().size() - 1);
      generatedId = lastUser.getId() + 1;
    } else {
      generatedId = 1;
    }
    anUser.setId(generatedId);
    this.demo.users().add(anUser);
  }

  public void stopManagingUser(User anUser) {
    this.demo.users().remove(anUser);
  }

  public void startManagingUserDetail(UserDetail anUserDetail) {
    Integer generatedId;
    if (!this.demo.userDetails().isEmpty()) {
      UserDetail lastUserDetail = this.demo.userDetails().get(this.demo.userDetails().size() - 1);
      generatedId = lastUserDetail.getId() + 1;
    } else {
      generatedId = 1;
    }
    anUserDetail.setId(generatedId);
    this.demo.userDetails().add(anUserDetail);
  }

  public void stopManagingUserDetail(UserDetail anUserDetail) {
    //TODO
  }

  public List<User> users() {
    return this.demo.users();
  }

  public User userIdentifiedBy(Integer anUserId) {
    return this.demo.users().stream()
        .filter(user -> user.getId().equals(anUserId))
        .findFirst()
        .orElse(null);
  }

  public IncidentPerCommunity incidentPerCommunityIdentifiedBy(Integer anId) {
    return this.demo.incidentPerCommunities().stream()
        .filter(incidentPerCommunity -> incidentPerCommunity.getId().equals(anId))
        .findFirst()
        .orElse(null);
  }


  public User userNamed(String anUserName) {
    return this.demo.users().stream()
        .filter(user -> user.username().equals(anUserName))
        .findFirst()
        .orElse(null);
  }

  public List<UserDetail> userDetails() {
    return this.demo.userDetails();
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
    return this.demo.services();
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
    Integer generatedId;
    if (!this.demo.incidents().isEmpty()) {
      Incident lastIncident = this.demo.incidents().get(this.demo.incidents().size() - 1);
      generatedId = lastIncident.getId() + 1;
    } else {
      generatedId = 1;
    }
    anIncident.setId(generatedId);
    this.demo.incidents().add(anIncident);
  }

  public void stopManagingIncident(Incident anIncident) {
    //TODO
  }

  public List<Incident> incidents() {
    return this.demo.incidents();
  }

  public void startManagingTransportLine(TransportLine transportLine) {
    //TODO
  }

  public void stopManagingTransportLine(TransportLine transportLine) {
    //TODO
  }

  public List<Entity> entities() {
    //TODO
    return this.demo.entities();
  }

  public void startManagingCommunity(Community community) {
    //TODO
  }

  public void stopManagingCommunity(Community community) {
    //TODO
  }

  public List<Community> communities() {
    return this.demo.communities();
  }

  public void startManagingIncidentPerCommunity(IncidentPerCommunity anIncidentPerCommunity) {
    Integer generatedId;
    if (!this.demo.incidentPerCommunities().isEmpty()) {
      IncidentPerCommunity lastIncident = this.demo.incidentPerCommunities()
          .get(this.demo.incidentPerCommunities().size() - 1);
      generatedId = lastIncident.getId() + 1;
    } else {
      generatedId = 1;
    }
    anIncidentPerCommunity.setId(generatedId);
    this.demo.incidentPerCommunities().add(anIncidentPerCommunity);
  }

  public void stopManagingIncidentPerCommunity(IncidentPerCommunity anIncidentPerCommunity) {
    //TODO
  }

  public void closeIncidentPerCommunity(IncidentPerCommunity anIncidentPerCommunity) {
    anIncidentPerCommunity.setState(State.composedOf("CLOSED", "CLOSED"));
  }

  public List<IncidentPerCommunity> incidentsPerCommunity() {
    return this.demo.incidentPerCommunities();
  }

  public Service serviceIdentifiedBy(Integer serviceId) {
    return this.demo.services().stream()
        .filter(service -> service.getId().equals(serviceId))
        .findFirst()
        .orElse(null);
  }

  public Community communityIdentifiedBy(Integer communityId) {
    return this.demo.communities().stream()
        .filter(community -> community.getId().equals(communityId))
        .findFirst()
        .orElse(null);
  }

  public List<IncidentPerCommunity> incidentsPerCommunityFilteredBy(String state) {

    if (Objects.equals(state, "ALL")) {
      return this.demo.incidentPerCommunities();
    } else {
      return this.demo.incidentPerCommunities().stream()
          .filter(incidentPerCommunity -> incidentPerCommunity.state().name.equals(state))
          .collect(Collectors.toList());
    }
  }

  public List<AuthorizationRole> authorizationRoles() {

    List<AuthorizationRole> authorizationRoles = new ArrayList<>();
    authorizationRoles.add(AuthorizationRole.ADMINISTRADOR);
    authorizationRoles.add(AuthorizationRole.ENTIDAD);
    authorizationRoles.add(AuthorizationRole.USUARIO);

    return authorizationRoles;
  }

  public List<ServiceHolder> serviceHolders() {
    return this.demo.serviceHolders();
  }

  public void startManagingServiceHolder(ServiceHolder serviceHolder) {
    this.demo.serviceHolders().add(serviceHolder);
  }

  public void startManagingAverageClosingTimeRanking(
      AverageClosingTimeRanking averageClosingTimeRanking) {
    //TODO
  }

  public void stopManagingAverageClosingTimeRanking(
      AverageClosingTimeRanking averageClosingTimeRanking) {
    //TODO
  }

  public void startManagingGreaterDegreeOfImpactRanking(
      GreaterDegreeOfImpactRanking greaterDegreeOfImpactRanking) {
    //TODO
  }

  public void stopManagingGreaterDegreeOfImpactRanking(
      GreaterDegreeOfImpactRanking greaterDegreeOfImpactRanking) {
    //TODO
  }

  public void startManagingMostReportedIncidentRanking(
      MostReportedIncidentsRanking mostReportedIncidentsRanking) {
    //TODO
  }

  public void stopManagingMostReportedIncidentRanking(
      MostReportedIncidentsRanking mostReportedIncidentsRanking) {
    //TODO
  }

  public List<WeeklyRanking> rankings() {
    return this.demo.rankings();
  }
}
