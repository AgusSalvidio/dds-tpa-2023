package ar.edu.utn.frba.dds.applicationcontext;

import ar.edu.utn.frba.dds.managementsystem.CommunityManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.EntityManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.IncidentManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.IncidentPerCommunityManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.ServiceManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.UserManagementSystem;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;

public class ApplicationContext {

  //RelationalDatabasePersistenceSystem persistenceSystem =
  // new RelationalDatabasePersistenceSystem();
  MemoryBasedPersistenceSystem persistenceSystem = new MemoryBasedPersistenceSystem();
  //EventNotificationSystem eventNotificationSystem = new EventNotificationSystem();
  UserManagementSystem userManagementSystem = new UserManagementSystem(this.persistenceSystem);
  IncidentManagementSystem incidentManagementSystem =
      new IncidentManagementSystem(this.persistenceSystem);
  EntityManagementSystem entityManagementSystem =
      new EntityManagementSystem(this.persistenceSystem);
  CommunityManagementSystem communityManagementSystem =
      new CommunityManagementSystem(this.persistenceSystem);
  IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem =
      new IncidentPerCommunityManagementSystem(this.persistenceSystem);

  public ApplicationContext() throws Exception {
  }

  public UserManagementSystem userManagementSystem() {
    return this.userManagementSystem;
  }

  public IncidentManagementSystem incidentManagementSystem() {
    return this.incidentManagementSystem;
  }

  public EntityManagementSystem entityManagementSystem() {
    return this.entityManagementSystem;
  }

  public CommunityManagementSystem communityManagementSystem() {
    return this.communityManagementSystem;
  }

  public IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem() {
    return this.incidentPerCommunityManagementSystem;
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

  public User loggedUser(Context context) {
    if (context.sessionAttribute("user_id") == null) {
      return null;
    } else {
      Integer userId = context.sessionAttribute("user_id");
      return this.userManagementSystem().userIdentifiedBy(userId);
    }
  }
}
