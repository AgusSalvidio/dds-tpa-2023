package ar.edu.utn.frba.dds.filereader;

public class FieldNumber extends Field {

  public FieldNumber(Integer index, String name, Integer length) {
    super(index, name, length);
  }

  @Override
  public void setValue(String value) {
    doubleValue = Double.valueOf(value);
  }

  @Override
  public Double getNumericValue() {
    return doubleValue;
  }

}
