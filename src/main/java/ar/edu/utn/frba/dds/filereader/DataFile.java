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
  BufferedReader _bufferedReader;

  public DataFile(String _name) {
    this.name = _name;
    this.synchronousRead = true;
    this.fields = new ArrayList<>();
    return;
  }

  public void Open() {
    try {
      if (this._bufferedReader == null) {
        this._bufferedReader = new BufferedReader(new FileReader(this.path.toString()));
        if (this.firstRowHasColumnNames) {
          this._bufferedReader.readLine();
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
      if (this._bufferedReader != null) {
        this._bufferedReader.close();
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
    List<Field> _row = this.fields;
    String[] _parse_line = this.parseLine();
    try {
      //Map Fields
      if (_bufferedReader.ready()) {
        _row.forEach(_field -> _field.setValue(_parse_line[_field.index]));
      }
      //Return Mapped Row
      return _row;
    } catch (Exception e) {
      return null;
    }
  }

}
