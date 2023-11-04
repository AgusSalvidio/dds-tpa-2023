package ar.edu.utn.frba.dds.services.communitiesfusion.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonData {
  public String id;
  public String name;

  public CommonData(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
