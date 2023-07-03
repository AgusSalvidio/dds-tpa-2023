package ar.edu.utn.frba.dds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceGroup extends Service {
  public List<Service> services;

  public static ServiceGroup composedOf(String name, String description) {
    return new ServiceGroup(name, description);
  }

  public ServiceGroup(String name, String description) {
    this.name = name;
    this.description = description;
    this.sections = new ArrayList<>();
    this.services = new ArrayList<>();
  }

  public void addNewService(Service newService) {
    this.services.add(newService);
  }

  public List<Service> services() {
    return this.services.stream().collect(Collectors.toList());
  }
}
