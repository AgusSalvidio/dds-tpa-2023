package ar.edu.utn.frba.dds.service;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class ServiceGroup extends Service{
  @Getter
  private List<Service> services;

  public ServiceGroup() {
    this.services = new ArrayList<>();
  }

  public void addNewService(Service newService) {
    this.services.add(newService);
  }
}
