package ar.edu.utn.frba.dds.filereader;

import ar.edu.utn.frba.dds.filereader.DataFile;
import ar.edu.utn.frba.dds.filereader.DataTable;
import ar.edu.utn.frba.dds.filereader.DataColumnString;
import ar.edu.utn.frba.dds.filereader.DataRow;
import ar.edu.utn.frba.dds.filereader.FileSource;

public class FileReaderTest {

    public void importFileTest() {

        DataFile dataFile = new DataFile();
        FileSource fileSource = new FileDelimited();
        DataTable dataTable = new DataTable();

        //Set Source Structure
        fileSource.name = "testFile";
        fileSource.path = "testFile.csv";
        fileSource.rowdelimiter = "CrLf";
        fileSource.columndelimiter = ";";
        fileSource.firstrowhascolumnnames = true;
        //Set Source Fields
        fileSource.fields.add(new Field("ID", FieldType.NUMBER, 5));
        fileSource.fields.add(new Field("Code", FieldType.STRING, 5));
        fileSource.fields.add(new Field("Filler01", FieldType.STRING, 5));
        fileSource.fields.add(new Field("Name", FieldType.STRING, 255));
        fileSource.fields.add(new Field("Filler02", FieldType.STRING, 5));

        //Set DataSet Structure
        dataTable.cols.add(new DataColumnNumber("ID", 5, "ID"));
        dataTable.cols.add(new DataColumnString("Code", 5, "Code"));
        dataTable.cols.add(new DataColumnString("Name", 255, "Name"));


        dataFile.setDataTable(dataTable);


        return;
    }
}
