package ar.edu.utn.frba.dds.filereader;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DataTable {
    @Getter @Setter
    String name;
    List<DataColumn> cols;
    List<DataRow> rows;

    public DataTable() {
        //TODO
        return;
    }
}
