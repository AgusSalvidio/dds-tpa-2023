package ar.edu.utn.frba.dds.filereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class FileReaderTest {

    @Test
    public void createFileSourceStructureTest() {
        FileSource fileSource = new FileDelimited("testFile");
        //Set Source Structure
        fileSource.path = "testFile.csv";
        fileSource.rowdelimiter = "CrLf";
        fileSource.columndelimiter = ";";
        fileSource.firstRowHasColumnNames = true;
        //Set Source Fields
        fileSource.fields.add(new Field("ID", FieldType.NUMBER, 5));
        fileSource.fields.add(new Field("Code", FieldType.STRING, 5));
        fileSource.fields.add(new Field("Filler01", FieldType.STRING, 5));
        fileSource.fields.add(new Field("Name", FieldType.STRING, 255));
        fileSource.fields.add(new Field("Filler02", FieldType.STRING, 5));
        //Test
        Assertions.assertEquals(5, fileSource.fields.size());
    }

    @Test
    public void createDataTableStructureTest() {
        DataTable dataTable = new DataTable("TestFile");
        //Set Destination Columns
        dataTable.cols.add(new DataColumnNumber("ID", 5, "ID"));
        dataTable.cols.add(new DataColumnString("Code", 5, "Code"));
        dataTable.cols.add(new DataColumnString("Name", 255, "Name"));
        //Test
        Assertions.assertEquals(3, dataTable.cols.size());
    }

    @Test
    public void importFileTest() throws IOException {
        FileSource fileSource = new FileDelimited("testFile");
        DataTable dataTable = new DataTable("TestFile");
        DataFile dataFile = new DataFile();
        //Set Source Structure
        fileSource.path = "testFile.csv";
        fileSource.rowdelimiter = "CrLf";
        fileSource.columndelimiter = ";";
        fileSource.firstRowHasColumnNames = true;
        //Set Source Fields
        fileSource.fields.add(new Field("ID", FieldType.NUMBER, 5));
        fileSource.fields.add(new Field("Code", FieldType.STRING, 5));
        fileSource.fields.add(new Field("Filler01", FieldType.STRING, 5));
        fileSource.fields.add(new Field("Name", FieldType.STRING, 255));
        fileSource.fields.add(new Field("Filler02", FieldType.STRING, 5));
        //Set Destination Columns
        dataTable.cols.add(new DataColumnNumber("ID", 5, "ID"));
        dataTable.cols.add(new DataColumnString("Code", 5, "Code"));
        dataTable.cols.add(new DataColumnString("Name", 255, "Name"));
        //Map Structures
        dataFile.setFileSource(fileSource);
        dataFile.setDataTable(dataTable);
        //Bind
        dataFile.populate();
        //Test
        Assertions.assertEquals(3, dataFile.dataTable.rows.size());
    }
}
