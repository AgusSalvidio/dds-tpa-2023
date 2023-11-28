package ar.edu.utn.frba.dds.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service")
@Setter
@Getter
public abstract class Service {
  @Id
  @GeneratedValue
  Integer id;

  @Column(name = "name")
  public String name;

  @Column(name = "description")
  public String description;

  @Column(name = "type")
  public ServiceType type;

  @Transient
  //@ManyToOne
  //@JoinColumn(name = "state_id", referencedColumnName = "id")
  public State state;

  /*@OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "section_id", referencedColumnName = "id")*/
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