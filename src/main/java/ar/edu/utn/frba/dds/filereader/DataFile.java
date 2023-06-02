package ar.edu.utn.frba.dds.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public abstract class DataFile {
  @Getter
  @Setter
  String name;
  @Getter
  @Setter
  String path;
  @Getter
  @Setter
  String rowDelimiter;
  @Getter
  @Setter
  String colDelimiter;
  @Getter
  @Setter
  Boolean firstRowHasColumnNames;
  @Getter
  @Setter
  Boolean synchronousRead;

  public List<Field> fields;
  BufferedReader bufferedReader;

  public DataFile(String vname) {
    this.name = vname;
    this.synchronousRead = true;
    this.fields = new ArrayList<>();
    return;
  }

  public void openFile() {
    try {
      if (this.bufferedReader == null) {
        this.bufferedReader = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8);
        //this.bufferedReader = new BufferedReader(new FileReader(this.path.getString()));
        if (this.firstRowHasColumnNames) {
          this.bufferedReader.readLine();
        }
      }
      return;
    } catch (FileNotFoundException e) {
      return;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void close() {
    try {
      if (this.bufferedReader != null) {
        this.bufferedReader.close();
      }
      return;
    } catch (IOException e) {
      return;
    }
  }

  public boolean addField(Field field) {
    this.fields.add(this.fields.size(), field);
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
