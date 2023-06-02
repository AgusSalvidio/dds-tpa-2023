package ar.edu.utn.frba.dds.filereader;

import java.io.IOException;

public class FileDelimited extends DataFile {

  public FileDelimited(String vname) {
    super(vname);
  }

  @Override
  public String[] parseLine() {
    try {
      String[] vfields = this.bufferedReader.readLine().split(this.colDelimiter);
      return vfields;
    } catch (IOException e) {
      return null;
    }
  }

}
