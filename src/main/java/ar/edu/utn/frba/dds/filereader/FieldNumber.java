package ar.edu.utn.frba.dds.filereader;

public class FieldNumber extends Field {

  public FieldNumber(Integer v_index, String v_name, Integer v_length) {
    super(v_index, v_name, v_length);
  }

  @Override
  public void setValue(String v_value) {
    doubleValue = Double.valueOf(v_value);
  }

  @Override
  public Double getNumericValue() {
    return doubleValue;
  }


}
