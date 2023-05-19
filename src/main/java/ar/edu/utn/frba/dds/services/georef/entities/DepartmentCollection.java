package ar.edu.utn.frba.dds.services.georef.entities;

import java.util.List;

public class DepartmentCollection {
  /*
    We have to keep the API parameters names in spanish to avoid fails.
    However, implemented accessors in English to avoid becoming BuggyTrade. -asalvidio*/
  public int cantidad;
  public int inicio;
  public int total;
  public Parameter parametros;

  public List<Department> departamentos;

  public int quantity() {
    return this.cantidad;
  }

  public int start() {
    return this.inicio;
  }

  public int total() {
    return this.total;
  }

  public Parameter parameters() {
    return this.parametros;
  }

  public List<Department> departments() {
    return this.departamentos;
  }
}
