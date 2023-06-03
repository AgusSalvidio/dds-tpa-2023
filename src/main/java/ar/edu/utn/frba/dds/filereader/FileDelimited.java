package ar.edu.utn.frba.dds.filereader;

import java.io.IOException;

public class FileDelimited extends DataFile {

  public FileDelimited(String value) {
    super(value);
  }

  @Override
  public String[] parseLine() {
    try {
      String[] fieldsAux = new String[this.fields.size()];
      if (this.bufferedReader != null) {
        fieldsAux = this.bufferedReader.readLine().split(this.getColDelimiter());
      }
      return fieldsAux;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
