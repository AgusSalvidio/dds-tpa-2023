package ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon;

import ar.edu.utn.frba.dds.service.Toilet;

public class ToiletCreationAddOn {

  public Toilet toiletA() {
    Toilet toilet = new Toilet();
    toilet.setName("Toilet Primer Piso");
    return toilet;
  }
}
