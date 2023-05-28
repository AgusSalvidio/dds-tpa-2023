package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.entity.EntityName;
import ar.edu.utn.frba.dds.entity.EntityType;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.entity.TransportType;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Section;
import ar.edu.utn.frba.dds.location.Location;

import ar.edu.utn.frba.dds.services.georef.entities.Municipality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UserPreferencesTest {
  private Section section() {
    Section section = new Section();
    section.setName("Acceso Principal a Molinetes");
    return section;
  }

  private Elevator elevator() {
    Elevator elevator = new Elevator();
    elevator.setName("Ascensor Principal");
    elevator.addNewSection(this.section());
    return elevator;
  }

  private EntityName transportLineName() {
    EntityName transportLineName = new EntityName();
    transportLineName.setName("SUBTE H");
    return transportLineName;
  }

  private EntityType subway() {
    EntityType subway = new EntityType();
    subway.setName("SUBWAY");
    return subway;
  }

  private Municipality municipality() {
    Municipality municipality = new Municipality();
    municipality.setNombre("CABA");
    return municipality;
  }

  private Location location() {
    Location location = new Location();
    location.setStreet("AV. FIGUEROA ALCORTA Y AV. PUEYRREDON");
    location.setMunicipality(this.municipality());
    return location;
  }

  private EstablishmentType station() {
    EstablishmentType station = new EstablishmentType();
    station.setName("STATION");
    return station;
  }
  private Establishment subwayStation() {
    Establishment subwayStation = new Establishment();
    subwayStation.setName("FACULTAD DE DERECHO");
    subwayStation.setType(this.station());
    subwayStation.setLocation(this.location());
    return subwayStation;
  }

  private TransportLine transport() {
    TransportLine transport = new TransportLine();
    transport.setName(this.transportLineName());
    transport.setType(this.subway());
    Establishment station = this.subwayStation();
    transport.addNewEstablishment(station);
    transport.setDeparture(station);
    transport.setArrival(station);
    return transport;
  }

  @Test
  @DisplayName("Create user preferences")
  public void createUserPreferences() {
    UserPreferences userPreferences = new UserPreferences();

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.entities.isEmpty());
  }

  @Test
  @DisplayName("Add service and transport to user preferences")
  public void addServiceAndTransportToUserPreferences() {
    UserPreferences userPreferences = new UserPreferences();
    Elevator elevator = this.elevator();
    TransportLine transport = this.transport();

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.entities.isEmpty());

    userPreferences.addService(elevator);
    userPreferences.addEntity(transport);

    Assertions.assertTrue(userPreferences.services().contains(elevator));
    Assertions.assertTrue(userPreferences.entities.contains(transport));


  }

  @Test
  @DisplayName("Delete service from user preferences")
  public void deleteServiceFromUserPreferences() {
    UserPreferences userPreferences = new UserPreferences();
    Elevator elevator = this.elevator();
    TransportLine transport = this.transport();

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.entities.isEmpty());

    userPreferences.addService(elevator);
    userPreferences.addEntity(transport);

    Assertions.assertTrue(userPreferences.services().contains(elevator));
    Assertions.assertTrue(userPreferences.entities.contains(transport));

    userPreferences.removeService(elevator);

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.entities.contains(transport));


  }

  @Test
  @DisplayName("Add location to user preferences")
  public void addLocationToUserPreferences() {
    UserPreferences userPreferences = new UserPreferences();
    Location location = this.location();

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.entities.isEmpty());
    Assertions.assertTrue(userPreferences.locations().isEmpty());

    userPreferences.addLocation(location);

    Assertions.assertTrue(userPreferences.locations().contains(location));
  }

  @Test
  @DisplayName("Remove location to user preferences")
  public void removeLocationToUserPreferences() {
    UserPreferences userPreferences = new UserPreferences();
    Location location = this.location();

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.entities.isEmpty());
    Assertions.assertTrue(userPreferences.locations().isEmpty());

    userPreferences.addLocation(location);

    Assertions.assertTrue(userPreferences.locations().contains(location));

    userPreferences.removeLocation(location);
    Assertions.assertTrue(userPreferences.locations().isEmpty());
  }

}
