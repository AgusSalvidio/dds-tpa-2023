package ar.edu.utn.frba.dds.filereader;

public class FieldString extends Field {

  public FieldString(Integer vindex, String vname, Integer vlength) {
    super(vindex, vname, vlength);
  }

  @Override
  public void setValue(String vvalue) {
    stringValue = vvalue;
  }

  @Override
  public String getStringValue() {
    return stringValue;
  }
}
