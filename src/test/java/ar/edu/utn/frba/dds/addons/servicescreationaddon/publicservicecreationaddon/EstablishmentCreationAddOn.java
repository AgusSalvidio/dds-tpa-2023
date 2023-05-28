package ar.edu.utn.frba.dds.addons.servicescreationaddon.publicservicecreationaddon;

import ar.edu.utn.frba.dds.addons.locationcreationaddon.LocationCreationAddOn;
import ar.edu.utn.frba.dds.establishment.Establishment;

public class EstablishmentCreationAddOn {
  public Establishment lawSchoolStation() {
    Establishment subwayStation = new Establishment();
    subwayStation.setType(new EstablishmentTypeCreationAddOn().station());
    subwayStation.setName("FACULTAD DE DERECHO");
    subwayStation.setLocation(new LocationCreationAddOn().locationA());
    return subwayStation;
  }

  public Establishment lasHerasStation() {
    Establishment subwayStation = new Establishment();
    subwayStation.setType(new EstablishmentTypeCreationAddOn().station());
    subwayStation.setName("LAS HERAS");
    subwayStation.setLocation(new LocationCreationAddOn().locationB());
    return subwayStation;
  }
}
