package ar.edu.utn.frba.dds.services.georef.entities;

import java.util.List;

public class ProvinceCollection {

  //We have to keep the API parameters names if not then it will fail.
  public int cantidad;
  public int inicio;
  public int total;
  public Parameter parametros;

  public List<Province> provincias;

  private class Parameter {
    public List<String> campos;
  }

}
