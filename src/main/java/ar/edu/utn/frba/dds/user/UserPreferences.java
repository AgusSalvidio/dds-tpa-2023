package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.publicservice.TransportLine;
import ar.edu.utn.frba.dds.service.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserPreferences {
  List<Service> services = new ArrayList<>();
  List<TransportLine> transports = new ArrayList<>();
  List<Location> locations = new ArrayList<>();

  public List<Service> services() {
    return this.services.stream()
        .collect(Collectors.toList());
  }

  public List<TransportLine> transports() {
    return this.transports.stream()
        .collect(Collectors.toList());
  }

  public void addService(Service service) {
    this.services.add(service);
  }

  public void addTransport(TransportLine transport) {
    this.transports.add(transport);
  }

  public void removeService(Service service) {
    this.services.remove(service);
  }

  public void removeTransport(TransportLine transport) {
    this.transports.remove(transport);
  }

  public void synchronizeWith(UserPreferences anUpdaterUserPreference) {
    this.services = anUpdaterUserPreference.services();
    this.transports = anUpdaterUserPreference.transports();
  }

  public List<Location> locations() {
    return this.locations.stream()
        .collect(Collectors.toList());
  }

  public void addLocation(Location location) {
    this.locations.add(location);
  }

  public void removeLocation(Location location) {
    this.locations.remove(location);
  }


}
