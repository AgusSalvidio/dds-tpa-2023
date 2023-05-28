package ar.edu.utn.frba.dds.addons.servicescreationaddon.publicservicecreationaddon;

import ar.edu.utn.frba.dds.publicservice.TransportType;

public class TransportTypeCreationAddOn {

  public TransportType subway() {
    TransportType transportType = new TransportType();
    transportType.setName("SUBWAY");
    return transportType;
  }
}
