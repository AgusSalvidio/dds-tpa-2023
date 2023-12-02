package ar.edu.utn.frba.dds.addons.locationcreationaddon;

import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.location.Municipality;


public class LocationCreationAddOn {
  private Municipality municipality = new MunicipalityCreationAddOn().caba();

  public Location locationA() {
    Location location = new Location();
    location.setStreet("AV. FIGUEROA ALCORTA Y AV. PUEYRREDON");
    location.setNumber(100);
    location.setMunicipality(this.municipality);
    return location;
  }

  public Location locationB() {
    Location location = new Location();
    location.setStreet("AV. PUEYRREDON");
    location.setNumber(2111);
    location.setMunicipality(this.municipality);
    return location;
  }

  public Location locationC() {
    Location location = new Location();
    location.setStreet("AV. ALMAFUENTE");
    location.setNumber(300);
    location.setMunicipality(this.municipality);
    return location;
  }

  public Location locationD() {
    Location location = new Location();
    location.setStreet("BARTOLOME MITRE");
    location.setNumber(326);
    location.setMunicipality(this.municipality);
    return location;
  }

  public Location locationE() {
    Location location = new Location();
    location.setStreet("ALSINA");
    location.setNumber(1356);
    location.setMunicipality(this.municipality);
    return location;
  }


}