package ar.edu.utn.frba.dds.filereader;

import java.io.IOException;

public class FileDelimited extends DataFile {

  public FileDelimited(String value) {
    super(value);
  }

  @Override
  public String[] parseLine() {
    try {
<<<<<<< HEAD
      String[] fieldsAux = new String[this.fields.size()];
      if (this.bufferedReader != null) {
        fieldsAux = this.bufferedReader.readLine().split(this.getColDelimiter());
      }
      return fieldsAux;
=======
      String line;
      if ((line = this.bufferedReader.readLine()) != null) {
        String[] vfields = line.split(this.colDelimiter);
        return vfields;
      }
>>>>>>> 490afd623d14ae1a61e99c7d313f7add20e4692a
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
<<<<<<< HEAD

=======
    return new String[0];
>>>>>>> 490afd623d14ae1a61e99c7d313f7add20e4692a
  }

}
