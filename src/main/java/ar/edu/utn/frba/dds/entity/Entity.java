package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.establishment.Establishment;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

public abstract class Entity {
  @Setter
  @Getter
  public EntityType type;
  @Setter

  public EntityName name;
  public List<Establishment> establishments;

  public Entity() {
    this.establishments = new ArrayList();
  }

  public void addNewEstablishment(Establishment newEstablishment) {
    this.establishments.add(newEstablishment);
  }

  public List<Establishment> getEstablishments() {
    return this.establishments.stream().collect(Collectors.toList());
  }

  public String name() {
    return this.name.getName();
  }

}
