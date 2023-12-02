package ar.edu.utn.frba.dds.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "service_type")
@Table(name = "service")
@Setter
@Getter
public abstract class Service {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(name = "name")
  public String name;

  @Column(name = "description")
  public String description;

  @OneToOne
  @JoinColumn(name = "service_state_id", referencedColumnName = "id")
  public State state;

  @Transient
  public List<Section> sections;

  public void addNewSection(Section newSection) {
    this.sections.add(newSection);
  }

  public List<Section> sections() {
    return this.sections.stream().collect(Collectors.toList());
  }

  public void synchronizeWith(Service updateService) {
    this.name = updateService.name;
    this.description = updateService.description;
    this.state = updateService.state;
    this.sections = updateService.sections();
  }
}