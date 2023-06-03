package ar.edu.utn.frba.dds.filereader;

public class FieldString extends Field {

  public FieldString(Integer index, String name, Integer length) {
    super(index, name, length);
  }

  @Override
  public void setValue(String value) {
    stringValue = value;
  }

  @Override
  public String getStringValue() {
    return stringValue;
  }
}
