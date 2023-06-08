package ar.edu.utn.frba.dds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceGroup extends Service {
  public List<Service> services;

  public ServiceGroup() {
    this.services = new ArrayList<>();
  }

  public void addNewService(Service newService) {
    this.services.add(newService);
  }

  public List<Service> getServices() {
    return this.services.stream().collect(Collectors.toList());
  }
}
