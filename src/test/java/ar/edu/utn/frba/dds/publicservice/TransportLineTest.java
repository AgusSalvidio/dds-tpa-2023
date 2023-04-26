package ar.edu.utn.frba.dds.publicservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransportLineTest {
  private TransportLine transportLine;
  private Station stationA;
  private Station stationB;
  private Station stationC;
  private Location locationA;
  private Location locationB;
  private Location locationC;

  @BeforeEach
  public void init() {
    this.transportLine = new TransportLine();
    this.transportLine.setName("Subte H");
    this.transportLine.setType(TransportType.SUBWAY);
    this.transportLine.setDirection(Direction.FORWARD);

    this.stationA = new Station();
    this.stationA.setName("Facultad de Derecho");
    this.stationB = new Station();
    this.stationB.setName("Once");
    this.stationC = new Station();
    this.stationC.setName("Hospitales");

    this.locationA = new Location();
    this.locationA.setAddress("Av. Figueroa Alcorta y Av. Pueyrredón");
    this.locationA.setCity("CABA");
    this.locationA.setLatitude(-34.58306);
    this.locationA.setLongitude(-58.39106);
    this.locationB = new Location();
    this.locationB.setAddress("Av. Pueyrredón 100");
    this.locationB.setCity("CABA");
    this.locationB.setLatitude(-34.609167);
    this.locationB.setLongitude(-58.406111);
    this.locationC = new Location();
    this.locationC.setAddress("Av. Almafuente 300");
    this.locationC.setCity("CABA");
    this.locationC.setLatitude(-34.641389);
    this.locationC.setLongitude(-58.4125);
  }

  @Test
  public void transportLineHasDepartureAndArrivalStation() {
    this.transportLine.setDeparture(stationA);
    Assertions.assertEquals("Facultad de Derecho", transportLine.getDeparture().getName());
    this.transportLine.setArrival(stationC);
    Assertions.assertEquals("Hospitales", transportLine.getArrival().getName());
  }

  @Test
  public void transportLineHasALotOfStations() {
    this.transportLine.addNewStation(stationA);
    this.transportLine.addNewStation(stationB);
    this.transportLine.addNewStation(stationC);
    Assertions.assertEquals(3, this.transportLine.getStations().size());
  }
}
