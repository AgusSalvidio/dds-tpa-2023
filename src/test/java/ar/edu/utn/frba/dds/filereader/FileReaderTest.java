package ar.edu.utn.frba.dds.filereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FileReaderTest {

    private DataFile testDataFile() {
        DataFile dataFile = new FileDelimited("testFile");
        //Set Source Structure
        dataFile.setFilePath("src/test/java/ar/edu/utn/frba/dds/filereader/testFile.csv");
        dataFile.setRowDelimiter("CrLf");
        dataFile.setColDelimiter(";");
        dataFile.setFirstRowHasColumnNames(true);
        //Set Source Fields
        dataFile.addField(new FieldNumber(0, "ID", 5));
        dataFile.addField(new FieldString(1, "Code", 5));
        dataFile.addField(new FieldString(2, "Filler01", 5));
        dataFile.addField(new FieldString(3, "Name", 255));
        dataFile.addField(new FieldString(4, "Filler02", 5));
        //Return
        return dataFile;
    }

    @Test
    public void createDataFileStructureTest() {
        DataFile dataFile = testDataFile();
        //Test
        Assertions.assertEquals(5, dataFile.fields.size());
    }


    @Test
    public void readFileSourceFirstLineFirstFieldTest() {
        DataFile dataFile = testDataFile();
        List<Field> mRow;
        //Read File
        dataFile.openFile();
        mRow = dataFile.getRow();
        //Test
        Assertions.assertEquals(1, mRow.get(0).getNumericValue());
        //Close
        dataFile.close();
    }

    @Test
    public void readFileSourceFirstLineLastFieldTest() {
        DataFile dataFile = testDataFile();
        //Read File
        dataFile.openFile();
        List<Field> row;
        row = dataFile.getRow();
        //Test
        Assertions.assertEquals("N/A", row.get(4).getStringValue());
        //Close
        dataFile.close();
    }

}
