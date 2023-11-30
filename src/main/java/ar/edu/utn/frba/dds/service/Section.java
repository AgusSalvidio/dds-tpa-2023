package ar.edu.utn.frba.dds.service;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;

public class Section {
  @Id
  @GeneratedValue
  Integer id;

  @Getter
  @Column(name = "name")
  public String name;

  public Section() {
    //Sobrecarga para que no rompa Hibernate
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
