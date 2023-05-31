package ar.edu.utn.frba.dds.addons.entitycreationaddon;

import ar.edu.utn.frba.dds.addons.locationcreationaddon.LocationCreationAddOn;
import ar.edu.utn.frba.dds.establishment.Establishment;

public class EstablishmentCreationAddOn {
  public Establishment lawSchoolStation() {
    Establishment establishment = new Establishment();
    establishment.setType(new EstablishmentTypeCreationAddOn().station());
    establishment.setName("FACULTAD DE DERECHO");
    establishment.setLocation(new LocationCreationAddOn().locationA());
    return establishment;
  }

  public Establishment lasHerasStation() {
    Establishment establishment = new Establishment();
    establishment.setType(new EstablishmentTypeCreationAddOn().station());
    establishment.setName("LAS HERAS");
    establishment.setLocation(new LocationCreationAddOn().locationB());
    return establishment;
  }

  public Establishment onceStation() {
    Establishment establishment = new Establishment();
    establishment.setType(new EstablishmentTypeCreationAddOn().station());
    establishment.setName("ONCE");
    establishment.setLocation(new LocationCreationAddOn().locationC());
    return establishment;
  }

  public Establishment hospitalStation() {
    Establishment establishment = new Establishment();
    establishment.setType(new EstablishmentTypeCreationAddOn().station());
    establishment.setName("HOSPITALES");
    establishment.setLocation(new LocationCreationAddOn().locationD());
    return establishment;
  }

  public Establishment headquarterBranch() {
    Establishment establishment = new Establishment();
    establishment.setType(new EstablishmentTypeCreationAddOn().branch());
    establishment.setName("CASA MATRIZ");
    establishment.setLocation(new LocationCreationAddOn().locationE());
    return establishment;
  }

  public Establishment alsinaBranch() {
    Establishment establishment = new Establishment();
    establishment.setType(new EstablishmentTypeCreationAddOn().branch());
    establishment.setName("SUCURSAL ALSINA");
    establishment.setLocation(new LocationCreationAddOn().locationE());
    return establishment;
  }

}
