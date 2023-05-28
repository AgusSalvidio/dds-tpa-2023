package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.addons.locationcreationaddon.LocationCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.publicservicecreationaddon.TransportLineCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.ElevatorCreationAddOn;
import ar.edu.utn.frba.dds.publicservice.TransportLine;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.location.Location;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UserPreferencesTest {

  private Location location() {
    return new LocationCreationAddOn().locationA();
  }

  private Elevator elevator() {
    return new ElevatorCreationAddOn().elevator();
  }

  private TransportLine transport() {
    return new TransportLineCreationAddOn().transportA();
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
