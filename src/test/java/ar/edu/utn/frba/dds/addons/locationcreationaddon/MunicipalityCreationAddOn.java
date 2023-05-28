package ar.edu.utn.frba.dds.addons.locationcreationaddon;

import ar.edu.utn.frba.dds.services.georef.entities.Municipality;

public class MunicipalityCreationAddOn {
  public Municipality caba() {
    Municipality municipality = new Municipality();
    municipality.setNombre("CABA");
    return municipality;
  }
}
