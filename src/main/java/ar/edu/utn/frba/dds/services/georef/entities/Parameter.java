package ar.edu.utn.frba.dds.services.georef.entities;

import java.util.List;
import lombok.Setter;

public class Parameter {
  @Setter
  public List<String> campos;
  public int max;
  @Setter
  public List<String> provincia;

  public List<String> fields() {
    return this.campos;
  }

  public int max() {
    return this.max;
  }

  public List<String> province() {
    return this.provincia;
  }

}
