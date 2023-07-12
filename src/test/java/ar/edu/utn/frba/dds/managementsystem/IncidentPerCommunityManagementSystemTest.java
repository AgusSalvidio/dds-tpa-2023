package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.addons.communitycreationaddon.CommunityCreationAddOn;
import ar.edu.utn.frba.dds.addons.incidentcreationaddon.IncidentCreationAddOn;
import ar.edu.utn.frba.dds.addons.managementsystemaddon.ManagementSystemAddOn;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IncidentPerCommunityManagementSystemTest {
  private EventNotificationSystem eventNotificationSystem() {
    return new EventNotificationSystem();
  }

  private PersistenceSystem persistenceSystem() {
    return this.memoryBasedPersistenceSystem();
  }

  private MemoryBasedPersistenceSystem memoryBasedPersistenceSystem() {
    return new MemoryBasedPersistenceSystem();
  }

  private Incident notWorkingElevatorIncident() throws Exception {
    return new IncidentCreationAddOn().notWorkingElevatorIncident();
  }

  private Incident workingElevatorIncident() throws Exception {
    return new IncidentCreationAddOn().workingElevatorIncident();
  }

  private IncidentManagementSystem incidentManagementSystemUsing(EventNotificationSystem eventNotificationSystem) {
    return new ManagementSystemAddOn().incidentManagementSystemUsing(eventNotificationSystem);
  }

  private Community community() throws Exception {
    return new CommunityCreationAddOn().firstCommunity();
  }

  @Test
  @DisplayName("Start managing an incidentPerCommunity")
  public void startManagingAnIncidentPerCommunityTest() throws Exception {

    IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem =
        IncidentPerCommunityManagementSystem.workingWith(this.persistenceSystem(),
            this.eventNotificationSystem());
    Incident incident = this.notWorkingElevatorIncident();
    Community community = this.community();
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(incident, community);

    Assertions.assertTrue(incidentPerCommunityManagementSystem.incidentsPerCommunity().isEmpty());

    incidentPerCommunityManagementSystem.startManaging(incidentPerCommunity);

    Assertions.assertEquals(incidentPerCommunityManagementSystem.incidentPerCommunity(incidentPerCommunity), incidentPerCommunity);

  }

  @Test
  @DisplayName("Stop managing an incidentPerCommunity")
  public void stopManagingAnIncidentPerCommunityTest() throws Exception {

    IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem =
        IncidentPerCommunityManagementSystem.workingWith(this.persistenceSystem(),
            this.eventNotificationSystem());
    Incident incident = this.notWorkingElevatorIncident();
    Community community = this.community();
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(incident, community);

    Assertions.assertTrue(incidentPerCommunityManagementSystem.incidentsPerCommunity().isEmpty());

    incidentPerCommunityManagementSystem.startManaging(incidentPerCommunity);

    Assertions.assertEquals(incidentPerCommunityManagementSystem.incidentPerCommunity(incidentPerCommunity), incidentPerCommunity);

    incidentPerCommunityManagementSystem.stopManaging(incidentPerCommunity);

    Assertions.assertTrue(incidentPerCommunityManagementSystem.incidentsPerCommunity().isEmpty());

  }

  @Test
  @DisplayName("Update an incidentPerCommunity")
  public void updateAnIncidentPerCommunityTest() throws Exception {

    IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem =
        IncidentPerCommunityManagementSystem.workingWith(this.persistenceSystem(),
            this.eventNotificationSystem());
    Incident incident = this.notWorkingElevatorIncident();
    Community community = this.community();
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(incident, community);

    Assertions.assertTrue(incidentPerCommunityManagementSystem.incidentsPerCommunity().isEmpty());

    incidentPerCommunityManagementSystem.startManaging(incidentPerCommunity);

    Assertions.assertEquals(incidentPerCommunityManagementSystem.incidentPerCommunity(incidentPerCommunity), incidentPerCommunity);

    IncidentPerCommunity updatedIncidentPerCommunity = incidentPerCommunity;
    updatedIncidentPerCommunity.close();

    incidentPerCommunityManagementSystem.updateWith(incidentPerCommunity, updatedIncidentPerCommunity);

    Assertions.assertEquals(incidentPerCommunityManagementSystem.incidentPerCommunity(incidentPerCommunity), incidentPerCommunity);
  }

}
