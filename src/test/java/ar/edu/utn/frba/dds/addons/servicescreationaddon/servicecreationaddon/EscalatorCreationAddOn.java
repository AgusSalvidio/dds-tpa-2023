package ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon;

import ar.edu.utn.frba.dds.service.Escalator;

public class EscalatorCreationAddOn {

  public Escalator escalatorA() {
    Escalator escalator = new Escalator();
    escalator.setName("Escalera Mecanica Adaptada");
    return escalator;
  }
}
