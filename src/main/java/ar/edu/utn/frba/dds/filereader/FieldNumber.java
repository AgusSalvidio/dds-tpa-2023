package ar.edu.utn.frba.dds.filereader;

public class FieldNumber extends Field {

  public FieldNumber(Integer vindex, String vname, Integer vlength) {
    super(vindex, vname, vlength);
  }

  @Override
  public void setValue(String vvalue) {
    doubleValue = Double.valueOf(vvalue);
  }

  @Override
  public Double getNumericValue() {
    return doubleValue;
  }


}
