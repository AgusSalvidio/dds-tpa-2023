package ar.edu.utn.frba.dds.filereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

public class FileReaderTest {

    private DataFile testDataFile() {
        DataFile _dataFile = new FileDelimited("testFile");
        //Set Source Structure
        _dataFile.path = "d:/testFile.csv";
        _dataFile.rowDelimiter = "CrLf";
        _dataFile.colDelimiter = ";";
        _dataFile.firstRowHasColumnNames = true;
        //Set Source Fields
        _dataFile.addField(new FieldNumber(0, "ID", 5));
        _dataFile.addField(new FieldString(1, "Code", 5));
        _dataFile.addField(new FieldString(2, "Filler01", 5));
        _dataFile.addField(new FieldString(3, "Name", 255));
        _dataFile.addField(new FieldString(4, "Filler02", 5));
        //Return
        return _dataFile;
    }

    @Test
    public void createDataFileStructureTest() {
        DataFile _dataFile = testDataFile();
        //Test
        Assertions.assertEquals(5, _dataFile.fields.size());
    }


    @Test
    public void readFileSourceFirstLineFirstFieldTest() {
        DataFile _dataFile = testDataFile();
        List<Field> _row;
        //Read File
        _dataFile.Open();
        _row = _dataFile.getRow();
        //Test
        Assertions.assertEquals(1, _row.get(0).getNumericValue());
        //Close
        _dataFile.Close();
    }

    @Test
    public void readFileSourceFirstLineLastFieldTest() {
        DataFile _dataFile = testDataFile();
        //Read File
        _dataFile.Open();
        List<Field> _row;
        _row = _dataFile.getRow();
        //Test
        Assertions.assertEquals("N/A", _row.get(4).getStringValue());
        //Close
        _dataFile.Close();
    }

}
