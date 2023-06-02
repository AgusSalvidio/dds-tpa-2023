package ar.edu.utn.frba.dds.filereader;

import java.time.LocalDateTime;

public class FieldDate extends Field {

  public FieldDate(Integer v_index, String v_name, Integer v_length) {
    super(v_index, v_name, v_length);
  }

  @Override
  public void setValue(String v_value) {
    dateTimeValue = LocalDateTime.parse(v_value);
  }

  @Override
  public LocalDateTime getDateValue() {
    return dateTimeValue;
  }

}
