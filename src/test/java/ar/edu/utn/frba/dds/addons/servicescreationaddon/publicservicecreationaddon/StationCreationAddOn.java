package ar.edu.utn.frba.dds.addons.servicescreationaddon.publicservicecreationaddon;

import ar.edu.utn.frba.dds.addons.locationcreationaddon.LocationCreationAddOn;
import ar.edu.utn.frba.dds.publicservice.Station;

public class StationCreationAddOn {

  public Station lawSchoolStation() {
    Station station = new Station();
    station.setName("FACULTAD DE DERECHO");
    station.setLocation(new LocationCreationAddOn().locationA());
    return station;
  }

  public Station lasHerasStation() {
    Station station = new Station();
    station.setName("LAS HERAS");
    station.setLocation(new LocationCreationAddOn().locationB());
    return station;
  }

}
