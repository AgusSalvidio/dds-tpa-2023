package ar.edu.utn.frba.dds.filereader;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DataTable {
    @Getter @Setter
    String name;
    List<DataColumn> cols;
    List<DataRow> rows;

    public DataTable(String _name) {
        this.name = _name;
        this.cols = new ArrayList<>();
        //TODO
        return;
    }
}
