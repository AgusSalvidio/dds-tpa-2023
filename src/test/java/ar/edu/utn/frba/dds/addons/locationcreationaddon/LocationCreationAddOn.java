package ar.edu.utn.frba.dds.addons.locationcreationaddon;

import ar.edu.utn.frba.dds.location.City;
import ar.edu.utn.frba.dds.location.Country;
import ar.edu.utn.frba.dds.location.Location;

public class LocationCreationAddOn {

  private Country country = new CountryCreationAddOn().argentina();
  private City city = new CityCreationAddOn().caba();


  public Location locationA() {
    Location location = new Location();
    location.setStreet("AV. FIGUEROA ALCORTA Y AV. PUEYRREDON");
    location.setLatitude(-34.58306);
    location.setLongitude(-58.39106);
    location.setCity(this.city);
    location.setCountry(this.country);
    return location;
  }

  public Location locationB() {
    Location location = new Location();
    location.setStreet("AV. PUEYRREDON 2111");
    location.setLatitude(-34.587445);
    location.setLongitude(-58.397226);
    location.setCity(this.city);
    location.setCountry(this.country);
    return location;
  }

}
