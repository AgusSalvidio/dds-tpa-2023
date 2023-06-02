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

  public Field(Integer v_index, String v_name, Integer v_length) {
    this.index = v_index;
    this.name = v_name;
    this.length = v_length;
    this.nullable = true;
  }

  public abstract void setValue(String v_value);

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
