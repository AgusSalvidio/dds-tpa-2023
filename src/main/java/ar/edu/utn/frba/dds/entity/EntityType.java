package ar.edu.utn.frba.dds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "entity_type")
@Getter
@Setter
public class EntityType {
  @Id
  @GeneratedValue
  Integer id;

  @Column(name = "name")
  public String name;
}

