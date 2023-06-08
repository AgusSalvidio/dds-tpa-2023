package ar.edu.utn.frba.dds.services.georef.entities;

import java.util.List;

public class MunicipalityCollection {

  /*
    We have to keep the API parameters names in spanish to avoid fails.
    However, implemented accessors in English to avoid becoming BuggyTrade. -asalvidio*/
  public int cantidad;
  public int inicio;
  public int total;

  public List<Municipality> municipios;
  public Parameter parametros;

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

  public List<Municipality> municipalities() {
    return this.municipios;
  }


}
