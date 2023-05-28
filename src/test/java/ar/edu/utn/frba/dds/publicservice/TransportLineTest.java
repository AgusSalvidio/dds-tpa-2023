package ar.edu.utn.frba.dds.publicservice;

import ar.edu.utn.frba.dds.location.*;
import ar.edu.utn.frba.dds.services.georef.entities.Municipality;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransportLineTest {
  private Municipality municipality;
  private Location locationA;
  private Location locationB;
  private Location locationC;
  private Station stationA;
  private Station stationB;
  private Station stationC;
  private Line line;
  private TransportType transportType;
  private TransportLine transportLine;

  @BeforeEach
  public void init() {
    this.municipality = new Municipality();
    this.municipality.setNombre("CABA");
    this.locationA = new Location();
    this.locationA.setStreet("AV. FIGUEROA ALCORTA Y AV. PUEYRREDON");
    this.locationB = new Location();
    this.locationB.setStreet("AV. PUEYRREDON");
    this.locationB.setNumber(100);
    this.locationC = new Location();
    this.locationC.setStreet("AV. ALMAFUENTE");
    this.locationC.setNumber(300);
    this.stationA = new Station();
    this.stationA.setName("FACULTAD DE DERECHO");
    this.stationB = new Station();
    this.stationB.setName("ONCE");
    this.stationC = new Station();
    this.stationC.setName("HOSPITALES");
    this.line = new Line();
    this.line.setName("SUBTE H");
    this.transportType = new TransportType();
    this.transportType.setName("SUBWAY");
    this.transportLine = new TransportLine();
  }

  @Test
  @DisplayName("A station has a location which belongs to a city and a country")
  public void aStationHasALocationWhichBelongsToACityAndACountry() {
    this.locationA.setMunicipality(municipality);
    this.stationA.setLocation(locationA);
    Assertions.assertEquals("CABA", stationA.getLocation().getMunicipality().name());
  }

  @Test
  @DisplayName("A transportLine has a name and belongs to a type")
  public void aTransportLineHasANameAndBelongsToAType() {
    this.transportLine.setLine(line);
    this.transportLine.setType(transportType);
    Assertions.assertEquals("SUBTE H", transportLine.getLine().getName());
    Assertions.assertEquals("SUBWAY", transportLine.getType().getName());
  }

  @Test
  @DisplayName("A transportLine has a departure and an arrival station")
  public void aTransportLineHasADepartureAndAnArrivalStation() {
    this.transportLine.setDeparture(stationA);
    Assertions.assertEquals("FACULTAD DE DERECHO", transportLine.getDeparture().getName());
    this.transportLine.setArrival(stationC);
    Assertions.assertEquals("HOSPITALES", transportLine.getArrival().getName());
  }

  @Test
  @DisplayName("A transportLine has a lot of stations")
  public void aTransportLineHasALotOfStations() {
    this.transportLine.addNewStation(stationA);
    this.transportLine.addNewStation(stationB);
    this.transportLine.addNewStation(stationC);
    Assertions.assertEquals(3, this.transportLine.getStations().size());
  }
}
