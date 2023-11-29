package ar.edu.utn.frba.dds.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "transportation_type")
@Getter
@Setter
public class TransportType {
  @Id
  @GeneratedValue
  Integer id;

  @Column(name = "name")
  public String name;
}
