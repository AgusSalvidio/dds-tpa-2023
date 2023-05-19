package ar.edu.utn.frba.dds.filereader;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Getter @Setter
public abstract class FileSource {
    String name;
    String path;
    String rowdelimiter;
    String columndelimiter;
    Boolean firstrowhascolumnnames;
    List<Field> fields;

    public FileSource() {
        //TODO
        return;
    }

    public  Boolean Check() {
        //TODO
        return null;
    }

    public Boolean Open() {
        //TODO
        return null;
    }

    public Boolean Close() {
        //TODO
        return null;
    }

    public Boolean FieldAdd(Field field) {
        //TODO
        return null;
    }

    public Boolean FieldRemove(Integer id) {
        //TODO
        return null;
    }

    public List<Field> GetLine() {
        //TODO
        return null;
    }

    public abstract Boolean Load() throws IOException;

}
