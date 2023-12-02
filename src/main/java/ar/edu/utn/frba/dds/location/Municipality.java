package ar.edu.utn.frba.dds.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "municipality")
@Setter
@Getter
public class Municipality {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(name = "name")
  public String name;

  @OneToOne
  @JoinColumn(name = "department_id", referencedColumnName = "id")
  public Department department;

  public Municipality() {
  }
}
