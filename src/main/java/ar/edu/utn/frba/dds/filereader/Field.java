package ar.edu.utn.frba.dds.filereader;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Field {
    String name;
    Integer length;
    FieldType type;
    Boolean nullable;
    String value;
}
