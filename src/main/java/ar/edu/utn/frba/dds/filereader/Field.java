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

  public Field(Integer _index, String _name, Integer _length) {
    this.index = _index;
    this.name = _name;
    this.length = _length;
    this.nullable = true;
  }

  public abstract void setValue(String _value);

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
