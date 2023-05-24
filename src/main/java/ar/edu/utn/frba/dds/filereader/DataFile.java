package ar.edu.utn.frba.dds.filereader;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DataFile {
    FileSource fileSource;
    DataTable dataTable;

    public DataFile() {
        //TODO
        return;
    }

    public boolean populate() {
        //TODO
        return true;
    }

}
