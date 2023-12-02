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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    public String name;

    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department department;

    public Municipality() { }
}
