package ar.edu.utn.frba.dds.filereader;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Field {
  Integer index;
  String name;
  Integer length;
  Boolean nullable;

  String stringValue;
  Double doubleValue;
  LocalDateTime dateTimeValue;

  public Field(Integer vindex, String vname, Integer vlength) {
    this.index = vindex;
    this.name = vname;
    this.length = vlength;
    this.nullable = true;
  }

  public abstract void setValue(String vvalue);

  public String getStringValue() {
    return "";
  }

  public Double getNumericValue() {
    return null;
  }

  public LocalDateTime getDateValue() {
    return null;
  }

}
