package ar.edu.utn.frba.dds.applicationcontext;

import ar.edu.utn.frba.dds.managementsystem.ServiceManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.UserManagementSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;

public class ApplicationContext {

  RelationalDatabasePersistenceSystem persistenceSystem = new RelationalDatabasePersistenceSystem();
  //EventNotificationSystem eventNotificationSystem = new EventNotificationSystem();
  UserManagementSystem userManagementSystem = new UserManagementSystem(this.persistenceSystem);

  public UserManagementSystem userManagementSystem() {
    return this.userManagementSystem;
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
