package ar.edu.utn.frba.dds.addons.servicescreationaddon.publicservicecreationaddon;

import ar.edu.utn.frba.dds.establishment.EstablishmentType;

public class EstablishmentTypeCreationAddOn {
  public EstablishmentType station() {
    EstablishmentType station = new EstablishmentType();
    station.setName("STATION");
    return station;
  }
}