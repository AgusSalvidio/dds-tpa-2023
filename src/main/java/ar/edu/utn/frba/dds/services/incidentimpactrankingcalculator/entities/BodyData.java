package ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

public class BodyData {
  @Setter
  @Getter
  public Double cfn;
  public List<EntityData> entities;

  public BodyData() {
    this.entities = new ArrayList<>();
  }

  public void addNewEntityData(EntityData newEntityData) {
    this.entities.add(newEntityData);
  }

  public List<EntityData> getEntitiesData() {
    return this.entities.stream().collect(Collectors.toList());
  }
}
