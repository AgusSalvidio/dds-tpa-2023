package ar.edu.utn.frba.dds.addons.servicescreationaddon.publicservicecreationaddon;

import ar.edu.utn.frba.dds.publicservice.Line;
import ar.edu.utn.frba.dds.publicservice.Station;
import ar.edu.utn.frba.dds.publicservice.TransportLine;

public class TransportLineCreationAddOn {

  private StationCreationAddOn stationCreationAddOn = new StationCreationAddOn();

  public TransportLine transportA() {
    TransportLine transport = new TransportLine();
    transport.setLine(new LineCreationAddOn().subwayHLine());
    transport.setType(new TransportTypeCreationAddOn().subway());

    transport.addNewStation(this.stationCreationAddOn.lawSchoolStation());
    transport.setDeparture(this.stationCreationAddOn.lawSchoolStation());
    transport.setArrival(this.stationCreationAddOn.lasHerasStation());
    return transport;
  }
}
