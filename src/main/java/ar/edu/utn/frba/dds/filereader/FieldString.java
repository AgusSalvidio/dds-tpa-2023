package ar.edu.utn.frba.dds.filereader;

public class FieldString extends Field {

  public FieldString(Integer v_index, String v_name, Integer v_length) {
    super(v_index, v_name, v_length);
  }

  @Override
  public void setValue(String v_value) {
    stringValue = v_value;
  }

  @Override
  public String getStringValue() {
    return stringValue;
  }
}
