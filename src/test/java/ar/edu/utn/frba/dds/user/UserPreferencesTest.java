package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.publicservice.Line;
import ar.edu.utn.frba.dds.publicservice.Station;
import ar.edu.utn.frba.dds.publicservice.TransportLine;
import ar.edu.utn.frba.dds.publicservice.TransportType;
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

  private Line line() {
    Line line = new Line();
    line.setName("SUBTE H");
    return line;
  }

  private TransportType transportType() {
    TransportType transportType = new TransportType();
    transportType.setName("SUBWAY");
    return transportType;
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

  private Station station() {
    Station station = new Station();
    station.setName("FACULTAD DE DERECHO");
    station.setLocation(this.location());
    return station;
  }

  private TransportLine transport() {
    TransportLine transport = new TransportLine();
    transport.setLine(this.line());
    transport.setType(this.transportType());
    Station station = this.station();
    transport.addNewStation(station);
    transport.setDeparture(station);
    transport.setArrival(station);
    return transport;
  }

  @Test
  @DisplayName("Create user preferences")
  public void createUserPreferences() {
    UserPreferences userPreferences = new UserPreferences();

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.transports().isEmpty());
  }

  @Test
  @DisplayName("Add service and transport to user preferences")
  public void addServiceAndTransportToUserPreferences() {
    UserPreferences userPreferences = new UserPreferences();
    Elevator elevator = this.elevator();
    TransportLine transport = this.transport();

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.transports().isEmpty());

    userPreferences.addService(elevator);
    userPreferences.addTransport(transport);

    Assertions.assertTrue(userPreferences.services().contains(elevator));
    Assertions.assertTrue(userPreferences.transports().contains(transport));


  }

  @Test
  @DisplayName("Delete service from user preferences")
  public void deleteServiceFromUserPreferences() {
    UserPreferences userPreferences = new UserPreferences();
    Elevator elevator = this.elevator();
    TransportLine transport = this.transport();

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.transports().isEmpty());

    userPreferences.addService(elevator);
    userPreferences.addTransport(transport);

    Assertions.assertTrue(userPreferences.services().contains(elevator));
    Assertions.assertTrue(userPreferences.transports().contains(transport));

    userPreferences.removeService(elevator);

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.transports().contains(transport));


  }

  @Test
  @DisplayName("Add location to user preferences")
  public void addLocationToUserPreferences() {
    UserPreferences userPreferences = new UserPreferences();
    Location location = this.location();

    Assertions.assertTrue(userPreferences.services().isEmpty());
    Assertions.assertTrue(userPreferences.transports().isEmpty());
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
    Assertions.assertTrue(userPreferences.transports().isEmpty());
    Assertions.assertTrue(userPreferences.locations().isEmpty());

    userPreferences.addLocation(location);

    Assertions.assertTrue(userPreferences.locations().contains(location));

    userPreferences.removeLocation(location);
    Assertions.assertTrue(userPreferences.locations().isEmpty());
  }

}
