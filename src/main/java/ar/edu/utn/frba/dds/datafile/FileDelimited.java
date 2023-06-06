package ar.edu.utn.frba.dds.datafile;

import java.io.IOException;

public class FileDelimited extends DataFile {

  public FileDelimited(String value) {
    super(value);
  }

  @Override
  public String[] parseLine() {
    try {
      String line;
      if ((line = this.bufferedReader.readLine()) != null) {
        String[] vfields = line.split(this.getColDelimiter());
        return vfields;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return new String[0];
  }
}
