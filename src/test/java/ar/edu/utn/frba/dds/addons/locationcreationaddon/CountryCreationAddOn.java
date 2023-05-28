package ar.edu.utn.frba.dds.addons.locationcreationaddon;

import ar.edu.utn.frba.dds.location.Country;

public class CountryCreationAddOn {

  public Country argentina() {
    Country country = new Country();
    country.setName("ARGENTINA");
    return country;
  }

  public Country uruguay() {
    Country country = new Country();
    country.setName("URUGUAY");
    return country;
  }
}
