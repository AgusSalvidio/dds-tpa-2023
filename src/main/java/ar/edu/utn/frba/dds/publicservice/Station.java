package ar.edu.utn.frba.dds.publicservice;

import ar.edu.utn.frba.dds.service.Service;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Station {
  @Setter
  @Getter
  private String name;
  @Setter
  @Getter
  private Location location;
  @Getter
  private List<Service> services;

  public Station() {
    this.services = new ArrayList<>();
  }
  public void addNewService(Service newService) {
    this.services.add(newService);
  }
}
