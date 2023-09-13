package ar.edu.utn.frba.dds.applicationcontext;

import ar.edu.utn.frba.dds.managementsystem.ServiceManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.UserManagementSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;

public class ApplicationContext {
  //User currentUser;

  RelationalDatabasePersistenceSystem persistenceSystem = new RelationalDatabasePersistenceSystem();
  //EventNotificationSystem eventNotificationSystem = new EventNotificationSystem();
  UserManagementSystem userManagementSystem = new UserManagementSystem(this.persistenceSystem);

  public UserManagementSystem userManagementSystem() {
    return this.userManagementSystem;
  }

  /*public User currentUser() {
    return this.currentUser;
  }*/

  public ServiceManagementSystem serviceManagementSystem() {
    return new ServiceManagementSystem(this.persistenceSystem);
  }
}
