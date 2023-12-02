package ar.edu.utn.frba.dds.location;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
@Setter
@Getter
public class Department {
    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "name")
    public String name;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    public Province province;

    public Department() { }
}
