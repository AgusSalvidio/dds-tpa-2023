package ar.edu.utn.frba.dds.applicationcontext;

import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.managementsystem.AuthorizationRoleManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.CommunityManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.IncidentManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.IncidentPerCommunityManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.ManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.ServiceManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.UserManagementSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;

public class ApplicationContext {

  RelationalDatabasePersistenceSystem persistenceSystem = new RelationalDatabasePersistenceSystem();
  //EventNotificationSystem eventNotificationSystem = new EventNotificationSystem();
  UserManagementSystem userManagementSystem = new UserManagementSystem(this.persistenceSystem);

  AuthorizationRoleManagementSystem authorizationRoleManagementSystem =
      new AuthorizationRoleManagementSystem(this.persistenceSystem);

  public UserManagementSystem userManagementSystem() {
    return this.userManagementSystem;
  }

  public AuthorizationRoleManagementSystem authorizationRoleManagementSystem() {
    return this.authorizationRoleManagementSystem;
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
