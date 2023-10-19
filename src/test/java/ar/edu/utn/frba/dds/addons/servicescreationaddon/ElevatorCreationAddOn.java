package ar.edu.utn.frba.dds.addons.servicescreationaddon;

import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.State;

public class ElevatorCreationAddOn {

  private State inServiceState() {
    return new StateCreationAddOn().inServiceState();
  }

  public Elevator elevator() {
    return Elevator.composedOf("Ascensor", "Ascensor Principal", this.inServiceState());
  }
}
