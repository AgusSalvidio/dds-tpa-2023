package ar.edu.utn.frba.dds.filereader;

import java.time.LocalDateTime;

public class FieldDate extends Field {

  public FieldDate(Integer vindex, String vname, Integer vlength) {
    super(vindex, vname, vlength);
  }

  @Override
  public void setValue(String vvalue) {
    dateTimeValue = LocalDateTime.parse(vvalue);
  }

  @Override
  public LocalDateTime getDateValue() {
    return dateTimeValue;
  }

}
