package ar.edu.utn.frba.dds.addons.servicescreationaddon;

import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.State;

public class EscalatorCreationAddOn {
  private State inServiceState() {
    return new StateCreationAddOn().inServiceState();
  }

  public Escalator escalatorA() {
    return Escalator.composedOf("Escalera", "Escalera Mecanica Adaptada", this.inServiceState());
  }
}
