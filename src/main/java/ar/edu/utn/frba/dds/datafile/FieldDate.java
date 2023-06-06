package ar.edu.utn.frba.dds.datafile;

import java.time.LocalDateTime;

public class FieldDate extends Field {

  public FieldDate(Integer index, String name, Integer length) {
    super(index, name, length);
  }

  @Override
  public void setValue(String value) {
    dateTimeValue = LocalDateTime.parse(value);
  }

  @Override
  public LocalDateTime getDateValue() {
    return dateTimeValue;
  }

}
