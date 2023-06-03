package ar.edu.utn.frba.dds.filereader;

import java.io.IOException;

public class FileDelimited extends DataFile {

  public FileDelimited(String vname) {
    super(vname);
  }

  @Override
  public String[] parseLine() {
    try {
      String line;
      if ((line = this.bufferedReader.readLine()) != null) {
        String[] vfields = line.split(this.colDelimiter);
        return vfields;
      }
    } catch (IOException e) {
      return null;
    }
    return new String[0];
  }

}
