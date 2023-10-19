package ar.edu.utn.frba.dds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entity_name")
public class EntityName {
  @Id
  @GeneratedValue
  Integer id;

  @Column(name = "name")
  public String name;
}
