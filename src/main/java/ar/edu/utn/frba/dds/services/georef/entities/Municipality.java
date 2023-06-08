package ar.edu.utn.frba.dds.services.georef.entities;

import lombok.Setter;

public class Municipality {
  /* Add @Setter to avoid UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD spotbug warning -asalvidio*/
  @Setter
  public String id;
  @Setter
  public String nombre;

  public String name() {
    return this.nombre;
  }

  public String id() {
    return this.id;
  }
}
