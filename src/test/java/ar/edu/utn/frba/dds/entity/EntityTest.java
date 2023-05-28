package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.services.georef.entities.Municipality;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntityTest {
  private Municipality municipality;
  private Location locationA;
  private Location locationB;
  private Location locationC;
  private EstablishmentType station;
  private Establishment stationA;
  private Establishment stationB;
  private Establishment stationC;
  private TransportLine transportLine;
  private EntityType subway;
  private EntityName transportLineName;
  private Location locationD;
  private Location locationE;
  private EstablishmentType branch;
  private Establishment branchA;
  private Establishment branchB;
  private Organization establishment;
  private EntityType bank;
  private EntityName bankName;


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
    this.station = new EstablishmentType();
    this.station.setName("STATION");
    this.stationA = new Establishment();
    this.stationA.setName("FACULTAD DE DERECHO");
    this.stationB = new Establishment();
    this.stationB.setName("ONCE");
    this.stationC = new Establishment();
    this.stationC.setName("HOSPITALES");
    this.transportLine = new TransportLine();
    this.subway = new EntityType();
    this.subway.setName("SUBWAY");
    this.transportLineName = new EntityName();
    this.transportLineName.setName("SUBTE H");
    this.locationD = new Location();
    this.locationD.setStreet("BARTOLOME MITRE");
    this.locationD.setNumber(326);
    this.locationE = new Location();
    this.locationE.setStreet("ALSINA");
    this.locationE.setNumber(1356);
    this.branch = new EstablishmentType();
    this.branch.setName("BRANCH");
    this.branchA = new Establishment();
    this.branchA.setName("CASA MATRIZ");
    this.branchB = new Establishment();
    this.branchB.setName("SUCURSAL ALSINA");
    this.establishment = new Organization();
    this.bank = new EntityType();
    this.bank.setName("BANK");
    this.bankName = new EntityName();
    this.bankName.setName("BANCO NACION");
  }

  @Test
  @DisplayName("An establishment has a location which belongs to a municipality")
  public void anEstablishmentHasALocationWhichBelongsToAMunicipality() {
    this.locationA.setMunicipality(municipality);
    this.stationA.setLocation(locationA);
    Assertions.assertEquals("CABA", this.stationA.getLocation().getMunicipality().name());
  }

  @Test
  @DisplayName("Sation and Branch are types of establishments")
  public void sationAndBranchAreTypesOfEstablishments() {
    this.stationA.setType(station);
    this.branchA.setType(branch);
    Assertions.assertEquals("STATION", this.stationA.getType().getName());
    Assertions.assertEquals("BRANCH", this.branchA.getType().getName());
  }

  @Test
  @DisplayName("A transportLine has a name and belongs to a type")
  public void aTransportLineHasANameAndBelongsToAType() {
    this.transportLine.setName(transportLineName);
    this.transportLine.setType(subway);
    Assertions.assertEquals("SUBTE H", this.transportLine.getName().getName());
    Assertions.assertEquals("SUBWAY", this.transportLine.getType().getName());
  }

  @Test
  @DisplayName("A transportLine has a departure and an arrival station")
  public void aTransportLineHasADepartureAndAnArrivalStation() {
    this.transportLine.setDeparture(stationA);
    Assertions.assertEquals("FACULTAD DE DERECHO", this.transportLine.getDeparture().getName());
    this.transportLine.setArrival(stationC);
    Assertions.assertEquals("HOSPITALES", this.transportLine.getArrival().getName());
  }

  @Test
  @DisplayName("A transportLine has a lot of stations")
  public void aTransportLineHasALotOfStations() {
    this.transportLine.addNewEstablishment(stationA);
    this.transportLine.addNewEstablishment(stationB);
    this.transportLine.addNewEstablishment(stationC);
    Assertions.assertEquals(3, this.transportLine.getEstablishments().size());
  }

  @Test
  @DisplayName("An Organization has a name and belongs to a type")
  public void anOrganizationHasANameAndBelongsToAType() {
    this.establishment.setName(bankName);
    this.establishment.setType(bank);
    Assertions.assertEquals("BANCO NACION", this.establishment.getName().getName());
    Assertions.assertEquals("BANK", this.establishment.getType().getName());
  }

  @Test
  @DisplayName("A bank has a lot of branchs")
  public void aBankHasALotOfBranchs() {
    this.establishment.addNewEstablishment(branchA);
    this.establishment.addNewEstablishment(branchB);
    Assertions.assertEquals(2, this.establishment.getEstablishments().size());
  }
}
