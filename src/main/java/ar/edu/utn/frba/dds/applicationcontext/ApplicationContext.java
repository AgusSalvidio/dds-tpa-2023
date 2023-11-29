package ar.edu.utn.frba.dds.applicationcontext;

import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.managementsystem.*;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;

import ar.edu.utn.frba.dds.serviceholder.ServiceHolder;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;
import java.util.List;

public class ApplicationContext {

  RelationalDatabasePersistenceSystem persistenceSystem = new RelationalDatabasePersistenceSystem();
  UserManagementSystem userManagementSystem = new UserManagementSystem(this.persistenceSystem);
  EntityTypeManagementSystem entityTypeManagementSystem = new EntityTypeManagementSystem(this.persistenceSystem);
  EstablishmentTypeManagementSystem establishmentTypeManagementSystem = new EstablishmentTypeManagementSystem(this.persistenceSystem);
  TransportTypeManagementSystem transportTypeManagementSystem = new TransportTypeManagementSystem((this.persistenceSystem));



  IncidentManagementSystem incidentManagementSystem = new IncidentManagementSystem(this.persistenceSystem);
  EntityManagementSystem entityManagementSystem =  new EntityManagementSystem(this.persistenceSystem);
  CommunityManagementSystem communityManagementSystem = new CommunityManagementSystem(this.persistenceSystem);
  IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem = new IncidentPerCommunityManagementSystem(this.persistenceSystem);

  ServiceHolderManagementSystem serviceHolderManagementSystem = new ServiceHolderManagementSystem(this.persistenceSystem);

  public ApplicationContext() throws Exception {
  }

  public User loggedUser(Context context) {
    if (context.sessionAttribute("user_id") == null) {
      return null;
    } else {
      Integer userId = context.sessionAttribute("user_id");
      return this.userManagementSystem().userIdentifiedBy(userId);
    }
  }

  public UserManagementSystem userManagementSystem() {
    return this.userManagementSystem;
  }

  public EntityTypeManagementSystem entityTypeManagementSystem() { return this.entityTypeManagementSystem; }

  public EstablishmentTypeManagementSystem establishmentTypeManagementSystem() { return this.establishmentTypeManagementSystem; }

  public TransportTypeManagementSystem transportTypeManagementSystem() { return this.transportTypeManagementSystem; }

  public EntityManagementSystem entityManagementSystem() { return this.entityManagementSystem; }










  public IncidentManagementSystem incidentManagementSystem() {
    return this.incidentManagementSystem;
  }

  public CommunityManagementSystem communityManagementSystem() {
    return this.communityManagementSystem;
  }

  public IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem() {
    return this.incidentPerCommunityManagementSystem;
  }

  public ServiceHolderManagementSystem serviceHolderManagementSystem() {
    return this.serviceHolderManagementSystem;
  }

  public void startManagingServiceHolder(ServiceHolder serviceHolder) {
    this.persistenceSystem.startManagingServiceHolder(serviceHolder);
  }

  public List<ServiceHolder> serviceHolders() {
    return this.serviceHolderManagementSystem.serviceHolders();
  }

  public User userIdentifiedBy(Integer anUserId) {
    return this.userManagementSystem().userIdentifiedBy(anUserId);
  }

  public User userNamed(String anUserName) {
    return this.userManagementSystem().userNamed(anUserName);
  }

  public ServiceManagementSystem serviceManagementSystem() {
    return new ServiceManagementSystem(this.persistenceSystem);
  }

}
