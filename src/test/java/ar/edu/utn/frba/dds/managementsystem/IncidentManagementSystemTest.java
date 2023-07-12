package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.addons.incidentcreationaddon.IncidentCreationAddOn;
import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IncidentManagementSystemTest {

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


  @Test
  @DisplayName("Start managing an incident")
  public void startManagingAnIncidentTest() throws Exception {

    IncidentManagementSystem incidentManagementSystem =
        IncidentManagementSystem.workingWith(this.persistenceSystem(), this.eventNotificationSystem());
    Incident incident = this.notWorkingElevatorIncident();

    Assertions.assertTrue(incidentManagementSystem.incidents().isEmpty());

    incidentManagementSystem.startManaging(incident);

    Assertions.assertEquals(incidentManagementSystem.incident(incident), incident);

  }

  @Test
  @DisplayName("Stop managing an incident")
  public void stopManagingAnIncidentTest() throws Exception {

    IncidentManagementSystem incidentManagementSystem =
        IncidentManagementSystem.workingWith(this.persistenceSystem(), this.eventNotificationSystem());
    Incident incident = this.notWorkingElevatorIncident();

    Assertions.assertTrue(incidentManagementSystem.incidents().isEmpty());

    incidentManagementSystem.startManaging(incident);

    Assertions.assertEquals(incidentManagementSystem.incident(incident), incident);

    incidentManagementSystem.stopManaging(incident);

    Assertions.assertTrue(incidentManagementSystem.incidents().isEmpty());

  }

  @Test
  @DisplayName("Update an incident")
  public void updateAnIncidentTest() throws Exception {

    IncidentManagementSystem incidentManagementSystem =
        IncidentManagementSystem.workingWith(this.persistenceSystem(), this.eventNotificationSystem());
    Incident incident = this.notWorkingElevatorIncident();

    Assertions.assertTrue(incidentManagementSystem.incidents().isEmpty());

    incidentManagementSystem.startManaging(incident);

    Assertions.assertEquals(incidentManagementSystem.incident(incident), incident);


    Incident updatedIncident = this.workingElevatorIncident();

    incidentManagementSystem.updateWith(incident, updatedIncident);

    Assertions.assertEquals(incidentManagementSystem.incident(incident), incident);
  }
}
