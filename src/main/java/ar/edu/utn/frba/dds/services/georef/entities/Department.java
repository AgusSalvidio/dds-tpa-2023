package ar.edu.utn.frba.dds.services.georef.entities;

import lombok.Setter;

public class Department {
  /* Add @Setter to avoid UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD spotbug warning -asalvidio*/
  @Setter
  public String id;
  @Setter
  public String nombre;
  @Setter
  public Province provincia;

  public String name() {
    return this.nombre;
  }

  public String id() {
    return this.id;
  }

  public Province province() {
    return this.provincia;
  }
}
