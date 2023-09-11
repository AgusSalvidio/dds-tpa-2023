package ar.edu.utn.frba.dds.applicationcontext;

import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.managementsystem.CommunityManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.IncidentManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.IncidentPerCommunityManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.ManagementSystem;
import ar.edu.utn.frba.dds.managementsystem.UserManagementSystem;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationContext {
  User currentUser;
  List<ManagementSystem> systems;

  PersistenceSystem persistenceSystem = new MemoryBasedPersistenceSystem();
  EventNotificationSystem eventNotificationSystem = new EventNotificationSystem();
  UserManagementSystem userManagementSystem = new UserManagementSystem(this.persistenceSystem);

  public ApplicationContext() {
    this.systems = new ArrayList<>();
    this.systems.add(new CommunityManagementSystem(this.persistenceSystem));
    this.systems.add(new IncidentManagementSystem(
        this.persistenceSystem,
        this.eventNotificationSystem));
    this.systems.add(new IncidentPerCommunityManagementSystem(
        this.persistenceSystem,
        this.eventNotificationSystem));
  }

  public List<ManagementSystem> systems() {
    return this.systems;
  }

  public UserManagementSystem userManagementSystem() {
    return this.userManagementSystem;
  }

  public User currentUser() {
    return this.currentUser;
  }
}
