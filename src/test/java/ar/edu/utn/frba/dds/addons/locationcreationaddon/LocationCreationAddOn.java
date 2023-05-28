package ar.edu.utn.frba.dds.addons.locationcreationaddon;

import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.services.georef.entities.Municipality;

public class LocationCreationAddOn {
  private Municipality municipality = new MunicipalityCreationAddOn().caba();

  public Location locationA() {
    Location location = new Location();
    location.setStreet("AV. FIGUEROA ALCORTA Y AV. PUEYRREDON");
    location.setMunicipality(this.municipality);
    return location;
  }

  public Location locationB() {
    Location location = new Location();
    location.setStreet("AV. PUEYRREDON 2111");
    location.setMunicipality(this.municipality);
    return location;
  }
}