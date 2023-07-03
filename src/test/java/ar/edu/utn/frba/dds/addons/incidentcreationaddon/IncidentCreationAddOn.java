package ar.edu.utn.frba.dds.addons.incidentcreationaddon;

import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.ElevatorCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.StateCreationAddOn;
import ar.edu.utn.frba.dds.addons.usercreationaddon.UserCreationAddOn;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.user.User;
import java.time.LocalDateTime;

public class IncidentCreationAddOn {
  private User ibarraneta() throws Exception {
    return new UserCreationAddOn().ibarraneta();
  }

  private State openIncidentState() {
    return new StateCreationAddOn().openIncident();
  }

  private State closedIncidentState() {
    return new StateCreationAddOn().closedIncident();
  }


  private Elevator elevator() {
    return new ElevatorCreationAddOn().elevator();
  }

  private LocalDateTime curentDateTime() {
    return LocalDateTime.of(2023, 7, 2, 23, 40);
  }

  private String notWorkingElevatorObservation() {
    return "No funciona el ascensor correctamente";
  }

  public Incident notWorkingElevatorIncident() throws Exception {

    return Incident.composedOf(
        this.elevator(),
        this.openIncidentState(),
        this.notWorkingElevatorObservation(),
        this.curentDateTime(),
        this.ibarraneta()
    );
  }

  public Incident workingElevatorIncident() throws Exception {

    return Incident.composedOf(
        this.elevator(),
        this.closedIncidentState(),
        this.notWorkingElevatorObservation(),
        this.curentDateTime(),
        this.ibarraneta()
    );
  }
}
