package ar.edu.utn.frba.dds.applicationcontext;

import ar.edu.utn.frba.dds.controller.view.EstablishmentViewController;
import ar.edu.utn.frba.dds.managementsystem.CommunityManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.EntityManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.EntityNameManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.EntityTypeManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.EstablishmentManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.EstablishmentTypeManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.IncidentManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.IncidentPerCommunityManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.ServiceHolderManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.ServiceManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.TransportTypeManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.UserManagementSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.serviceholder.ServiceHolder;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;
import java.util.List;

public class ApplicationContext {

  RelationalDatabasePersistenceSystem persistenceSystem = new RelationalDatabasePersistenceSystem();

  public ApplicationContext() throws Exception {
  }

  public UserManagementSystem userManagementSystem() {
    return new UserManagementSystem(this.persistenceSystem);
  }

  public User userIdentifiedBy(Integer anUserId) {
    return this.userManagementSystem().userIdentifiedBy(anUserId);
  }

  public User userNamed(String anUserName) {
    return this.userManagementSystem().userNamed(anUserName);
  }

  public User loggedUser(Context context) {
    if (context.sessionAttribute("user_id") == null) {
      return null;
    } else {
      Integer userId = context.sessionAttribute("user_id");
      return this.userManagementSystem().userIdentifiedBy(userId);
    }
  }
  public ServiceManagementSystem serviceManagementSystem() {
    return new ServiceManagementSystem(this.persistenceSystem);
  }

  public EstablishmentTypeManagementSystem establishmentTypeManagementSystem() {
    return new EstablishmentTypeManagementSystem(this.persistenceSystem);
  }

  public EstablishmentManagementSystem establishmentManagementSystem() {
    return new EstablishmentManagementSystem(this.persistenceSystem);
  }

  public EntityTypeManagementSystem entityTypeManagementSystem() {
    return new EntityTypeManagementSystem(this.persistenceSystem);
  }

  public EntityNameManagementSystem entityNameManagementSystem() {
    return new EntityNameManagementSystem(this.persistenceSystem);
  }

  public EntityManagementSystem entityManagementSystem() {
    return new EntityManagementSystem(this.persistenceSystem);
  }


  public IncidentManagementSystem incidentManagementSystem() {
    return new IncidentManagementSystem(this.persistenceSystem);
  }

  public CommunityManagementSystem communityManagementSystem() {
    return new CommunityManagementSystem(this.persistenceSystem);
  }

  public IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem() {
    return new IncidentPerCommunityManagementSystem(this.persistenceSystem);
  }

  public ServiceHolderManagementSystem serviceHolderManagementSystem() {
    return new ServiceHolderManagementSystem(this.persistenceSystem);
  }
}
