package ar.edu.utn.frba.dds.filereader;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FileSource {
    @Getter @Setter
    String name;
    @Getter @Setter
    String path;
    @Getter @Setter
    String rowdelimiter;
    @Getter @Setter
    String columndelimiter;
    @Getter @Setter
    Boolean firstRowHasColumnNames;
    @Getter @Setter
    List<Field> fields;

    BufferedReader _bufferedReader;

    public FileSource(String _name) {
        this.name = _name;
        this.fields = new ArrayList<>();
        //TODO
        return;
    }

    public Boolean Check() {
        //TODO
        return null;
    }

    public Boolean Open() throws FileNotFoundException {
        this._bufferedReader = new BufferedReader(new FileReader(this.path.toString()));
        //TODO ErrHandler
        return null;
    }

    public Boolean Close() throws IOException {
        try {
            if ( this._bufferedReader != null ) {
                this._bufferedReader.close();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public abstract List<Field> GetLine() throws IOException;

}
