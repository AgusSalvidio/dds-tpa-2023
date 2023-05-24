package ar.edu.utn.frba.dds.filereader;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Setter @Getter
public class DataFile {
    FileSource fileSource;
    DataTable dataTable;

    public DataFile() {
        //TODO
        return;
    }

    public boolean populate() throws IOException {
        //Load File Data
        //this.fileSource.Load();
        return true;
    }

}
