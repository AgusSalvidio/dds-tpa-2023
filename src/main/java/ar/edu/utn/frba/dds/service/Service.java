package ar.edu.utn.frba.dds.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

  @ManyToOne
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