package ar.edu.utn.frba.dds.filereader;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Field {
    String name;
    FieldType type;
    Integer length;
    Boolean nullable;
    String value;

    public Field(String _name, FieldType _type, Integer _length) {
        this.name = _name;
        this.type = _type;
        this.length = _length;
        this.nullable = true;

    }

}
