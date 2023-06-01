package ar.edu.utn.frba.dds.filereader;

public class FieldString extends Field {

  public FieldString(Integer _index, String _name, Integer _length) {
    super(_index, _name, _length);
  }

  @Override
  public void setValue(String _value) {
    stringValue = _value;
  }

  @Override
  public String getStringValue() {
    return stringValue;
  }
}
