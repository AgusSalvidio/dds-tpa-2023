package ar.edu.utn.frba.dds.addons.entitycreationaddon;

import ar.edu.utn.frba.dds.entity.TransportLine;

public class TransportLineCreationAddOn {
  private EstablishmentCreationAddOn establishmentCreationAddOn = new EstablishmentCreationAddOn();

  public TransportLine transportA() {
    TransportLine transport = new TransportLine();
    transport.setName(new EntityNameCreationAddOn().subwayHLine());
    transport.setType(new EntityTypeCreationAddOn().subway());

    transport.addNewEstablishment(this.establishmentCreationAddOn.lawSchoolStation());
    transport.setDeparture(this.establishmentCreationAddOn.lawSchoolStation());
    transport.setArrival(this.establishmentCreationAddOn.lasHerasStation());
    return transport;
  }
}
