package ar.edu.utn.frba.dds.filereader;

import java.io.IOException;

public class FileDelimited extends DataFile {

  public FileDelimited(String v_name) {
    super(v_name);
  }

  @Override
  public String[] parseLine() {
    try {
      String[] v_fields = this.bufferedReader.readLine().split(this.colDelimiter);
      return v_fields;
    } catch (IOException e) {
      return null;
    }
  }

}
