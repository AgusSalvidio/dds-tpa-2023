package ar.edu.utn.frba.dds.addons.entitycreationaddon;

import ar.edu.utn.frba.dds.establishment.EstablishmentType;

public class EstablishmentTypeCreationAddOn {
  public EstablishmentType station() {
    EstablishmentType establishmentType = new EstablishmentType();
    establishmentType.setName("STATION");
    return establishmentType;
  }

  public EstablishmentType branch() {
    EstablishmentType establishmentType = new EstablishmentType();
    establishmentType.setName("BRANCH");
    return establishmentType;
  }

}