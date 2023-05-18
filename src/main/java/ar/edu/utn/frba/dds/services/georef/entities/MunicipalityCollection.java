package ar.edu.utn.frba.dds.services.georef.entities;

import java.util.List;

public class MunicipalityCollection {
  public int cantidad;
  public int inicio;
  public int total;

  public List<Municipality> municipios;
  public Parameter parametros;

  private class Parameter {
    public List<String> campos;
    public int max;
    public List<String> provincia;
  }

}
