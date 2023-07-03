package ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon;

import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;

public class ToiletCreationAddOn {

  private State inServiceState() {
    return new StateCreationAddOn().inServiceState();
  }

  public Toilet toiletA() {
    return Toilet.composedOf("Toilet", "Toilet Primer Piso", this.inServiceState());
  }
}
