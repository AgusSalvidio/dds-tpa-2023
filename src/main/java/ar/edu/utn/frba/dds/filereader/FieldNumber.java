package ar.edu.utn.frba.dds.filereader;

public class FieldNumber extends Field {

  public FieldNumber(Integer _index, String _name, Integer _length) {
    super(_index, _name, _length);
  }

  @Override
  public void setValue(String _value) {
    doubleValue = Double.valueOf(_value);
  }

  @Override
  public Double getNumericValue() {
    return doubleValue;
  }


}
