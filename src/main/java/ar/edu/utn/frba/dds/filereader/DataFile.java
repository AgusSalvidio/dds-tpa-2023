package ar.edu.utn.frba.dds.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

  List<Field> fields;
  BufferedReader bufferedReader;

  public DataFile(String v_name) {
    this.name = v_name;
    this.synchronousRead = true;
    this.fields = new ArrayList<>();
    return;
  }

  public void Open() {
    try {
      if (this.bufferedReader == null) {
        this.bufferedReader = new BufferedReader(new FileReader(this.path.toString()));
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

  public void Close() {
    try {
      if (this.bufferedReader != null) {
        this.bufferedReader.close();
      }
      return;
    } catch (IOException e) {
      return;
    }
  }

  public boolean addField(Field _field) {
    this.fields.add(this.fields.size(), _field);
    return true;
  }

  public abstract String[] parseLine();

  public List<Field> getRow() {
    List<Field> row = this.fields;
    String[] parse_line = this.parseLine();
    try {
      //Map Fields
      if (bufferedReader.ready()) {
        row.forEach(field -> field.setValue(parse_line[field.index]));
      }
      //Return Mapped Row
      return row;
    } catch (Exception e) {
      return null;
    }
  }

}
