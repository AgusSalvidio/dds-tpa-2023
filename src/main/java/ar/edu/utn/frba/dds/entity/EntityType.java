package ar.edu.utn.frba.dds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EntityType {
  @Id
  @GeneratedValue
  Integer id;

  @Column(name = "name")
  public String name;
}
