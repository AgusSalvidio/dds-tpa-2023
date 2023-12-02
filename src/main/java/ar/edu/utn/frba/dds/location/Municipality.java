package ar.edu.utn.frba.dds.location;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "municipality")
@Setter
@Getter
public class Municipality {
    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "name")
    public String name;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department department;

    public Municipality() { }
}
