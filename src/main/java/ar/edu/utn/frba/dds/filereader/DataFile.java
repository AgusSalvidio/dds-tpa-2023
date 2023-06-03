package ar.edu.utn.frba.dds.filereader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public abstract class DataFile {
  @Getter @Setter
  String fileName;
  @Getter @Setter
  String filePath;
  @Getter @Setter
  String rowDelimiter;
  @Getter @Setter
  String colDelimiter;
  @Getter @Setter
  Boolean firstRowHasColumnNames;
  @Getter @Setter
  Boolean synchronousRead;

  public List<Field> fields;
  BufferedReader bufferedReader;

  public DataFile(String mfileName) {
    this.fileName = mfileName;
    this.synchronousRead = true;
    this.fields = new ArrayList<>();
    return;
  }

  public void openFile() {
    try {
      this.bufferedReader = new BufferedReader(new FileReader(new File(getFilePath())));
      if (this.bufferedReader != null) {
        if (this.firstRowHasColumnNames) {
          this.bufferedReader.readLine();
        }
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void close() {
    try {
      if (this.bufferedReader != null) {
        this.bufferedReader.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean addField(Field localField) {
    this.fields.add(this.fields.size(), localField);
    return true;
  }

  public abstract String[] parseLine();

  public List<Field> getRow() {
    List<Field> row = this.fields;
    String[] parseline = this.parseLine();
    try {
      //Map Fields
      if (bufferedReader.ready()) {
        row.forEach(field -> field.setValue(parseline[field.index]));
      }
      //Return Mapped Row
      return row;
    } catch (Exception e) {
      return null;
    }
  }

}
