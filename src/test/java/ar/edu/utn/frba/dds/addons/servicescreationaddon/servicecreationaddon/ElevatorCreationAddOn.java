package ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon;

import ar.edu.utn.frba.dds.service.Elevator;

public class ElevatorCreationAddOn {
  public Elevator elevator() {
    Elevator elevator = new Elevator();
    elevator.setName("Ascensor Principal");
    return elevator;
  }
}
