package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.addons.entitycreationaddon.TransportLineCreationAddOn;
import ar.edu.utn.frba.dds.addons.locationcreationaddon.LocationCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.ElevatorCreationAddOn;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.service.Elevator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserPreferenceTest {

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
    UserPreference userPreference = new UserPreference();

    Assertions.assertTrue(userPreference.services().isEmpty());
    Assertions.assertTrue(userPreference.entities().isEmpty());
  }

  @Test
  @DisplayName("Add service and transport to user preferences")
  public void addServiceAndTransportToUserPreferences() {
    UserPreference userPreference = new UserPreference();
    Elevator elevator = this.elevator();
    TransportLine transport = this.transport();

    Assertions.assertTrue(userPreference.services().isEmpty());
    Assertions.assertTrue(userPreference.entities.isEmpty());

    userPreference.addService(elevator);
    userPreference.addEntity(transport);

    Assertions.assertTrue(userPreference.services().contains(elevator));
    Assertions.assertTrue(userPreference.entities.contains(transport));


  }

  @Test
  @DisplayName("Delete service from user preferences")
  public void deleteServiceFromUserPreferences() {
    UserPreference userPreference = new UserPreference();
    Elevator elevator = this.elevator();
    TransportLine transport = this.transport();

    Assertions.assertTrue(userPreference.services().isEmpty());
    Assertions.assertTrue(userPreference.entities.isEmpty());

    userPreference.addService(elevator);
    userPreference.addEntity(transport);

    Assertions.assertTrue(userPreference.services().contains(elevator));
    Assertions.assertTrue(userPreference.entities.contains(transport));

    userPreference.removeService(elevator);

    Assertions.assertTrue(userPreference.services().isEmpty());
    Assertions.assertTrue(userPreference.entities.contains(transport));


  }

  @Test
  @DisplayName("Add location to user preferences")
  public void addLocationToUserPreferences() {
    UserPreference userPreference = new UserPreference();
    Location location = this.location();

    Assertions.assertTrue(userPreference.services().isEmpty());
    Assertions.assertTrue(userPreference.entities.isEmpty());
    Assertions.assertTrue(userPreference.locations().isEmpty());

    userPreference.addLocation(location);

    Assertions.assertTrue(userPreference.locations().contains(location));
  }

  @Test
  @DisplayName("Remove location to user preferences")
  public void removeLocationToUserPreferences() {
    UserPreference userPreference = new UserPreference();
    Location location = this.location();

    Assertions.assertTrue(userPreference.services().isEmpty());
    Assertions.assertTrue(userPreference.entities.isEmpty());
    Assertions.assertTrue(userPreference.locations().isEmpty());

    userPreference.addLocation(location);

    Assertions.assertTrue(userPreference.locations().contains(location));

    userPreference.removeLocation(location);
    Assertions.assertTrue(userPreference.locations().isEmpty());
  }

}
