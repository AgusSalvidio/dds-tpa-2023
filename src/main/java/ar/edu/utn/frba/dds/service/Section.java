package ar.edu.utn.frba.dds.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "section")
public class Section {
  @Id
  @GeneratedValue
  Integer id;

  @Getter
  @Column(name = "name")
  public String name;

  public Section() {

  }

  public static Section named(String name) {
    return new Section(name);
  }

  public Section(String name) {
    this.name = name;
  }

  public String name() {
    return this.name;
  }

}
