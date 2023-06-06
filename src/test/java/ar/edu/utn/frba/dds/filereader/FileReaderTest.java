package ar.edu.utn.frba.dds.filereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FileReaderTest {

    private DataFile testDataFile() {
        DataFile dataFile = new FileDelimited("testFile");
        //Set Source Structure
        dataFile.setFilePath("src/main/resources/testFile.csv");
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
    public void createFieldNumberStructureTest() {
        //Set Source Fields
        FieldNumber fieldNumber =  new FieldNumber(0, "Number", 5);
        //Test
        Assertions.assertFalse(fieldNumber == null);
    }

    @Test
    public void createFieldStringStructureTest() {
        //Set Source Fields
        FieldString fieldString =  new FieldString(0, "String", 255);
        //Test
        Assertions.assertFalse(fieldString == null);
    }

    @Test
    public void createFieldDateStructureTest() {
        //Set Source Fields
        FieldDate fieldDate =  new FieldDate(0, "Fecha", 8);
        //Test
        Assertions.assertFalse(fieldDate == null);
    }

    @Test
    public void createDataFileStructureTest() {
        DataFile dataFile = testDataFile();
        //Test
        Assertions.assertEquals(5, dataFile.fields.size());
    }

    @Test
    public void openDataFileStructureTest() {
        DataFile dataFile = testDataFile();
        List<Field> mRow;
        //Read File
        dataFile.openFile();
        //Test
        Assertions.assertFalse(dataFile == null);
        //Close
        dataFile.close();
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

    @Test
    public void readFileEntityTest() {
        DataFile dataFile = new FileDelimited("Entity");
        //Set Source Structure
        dataFile.setFilePath("src/main/resources/Entidades.csv");
        dataFile.setRowDelimiter("CrLf");
        dataFile.setColDelimiter(";");
        dataFile.setFirstRowHasColumnNames(false);
        //Set Source Fields
        dataFile.addField(new FieldNumber(0, "ID", 10));
        dataFile.addField(new FieldString(1, "Name", 255));
        dataFile.addField(new FieldString(2, "Type", 255));
        dataFile.addField(new FieldNumber(3, "ControllerId", 10));
        //Read File
        dataFile.openFile();
        List<Field> row;
        row = dataFile.getRow();
        //Test
        Assertions.assertEquals("Banco de la Nacion Argentina", row.get(1).getStringValue());
        //Close
        dataFile.close();
    }

    @Test
    public void readFileEstablishmentTest() {
        DataFile dataFile = new FileDelimited("Establishment");
        //Set Source Structure
        dataFile.setFilePath("src/main/resources/Establecimientos.csv");
        dataFile.setRowDelimiter("CrLf");
        dataFile.setColDelimiter(";");
        dataFile.setFirstRowHasColumnNames(false);
        //Set Source Fields
        dataFile.addField(new FieldNumber(0, "EntityId", 10));
        dataFile.addField(new FieldNumber(1, "ID", 10));
        dataFile.addField(new FieldString(2, "Name", 255));
        dataFile.addField(new FieldString(3, "Type", 255));
        dataFile.addField(new FieldString(4, "Address", 255));
        //Read File
        dataFile.openFile();
        List<Field> row;
        row = dataFile.getRow();
        //Test
        Assertions.assertEquals("Sucursal Plaza de Mayo", row.get(2).getStringValue());
        //Close
        dataFile.close();
    }

    @Test
    public void readFileServiceTest() {
        DataFile dataFile = new FileDelimited("Entity");
        //Set Source Structure
        dataFile.setFilePath("src/main/resources/Servicios.csv");
        dataFile.setRowDelimiter("CrLf");
        dataFile.setColDelimiter(";");
        dataFile.setFirstRowHasColumnNames(false);
        //Set Source Fields
        dataFile.addField(new FieldNumber(0, "EntityId", 10));
        dataFile.addField(new FieldNumber(1, "EstablishmentId", 10));
        dataFile.addField(new FieldString(2, "Service", 255));
        dataFile.addField(new FieldString(3, "Type", 255));
        //Read File
        dataFile.openFile();
        List<Field> row;
        row = dataFile.getRow();
        //Test
        Assertions.assertEquals("Toilet", row.get(2).getStringValue());
        //Close
        dataFile.close();
    }

    @Test
    public void readFileOrgenismTest() {
        DataFile dataFile = new FileDelimited("Entity");
        //Set Source Structure
        dataFile.setFilePath("src/main/resources/Organismos.csv");
        dataFile.setRowDelimiter("CrLf");
        dataFile.setColDelimiter(";");
        dataFile.setFirstRowHasColumnNames(false);
        //Set Source Fields
        dataFile.addField(new FieldNumber(0, "ID", 10));
        dataFile.addField(new FieldString(1, "Name", 255));
        //Read File
        dataFile.openFile();
        List<Field> row;
        row = dataFile.getRow();
        //Test
        Assertions.assertEquals("BCRA", row.get(1).getStringValue());
        //Close
        dataFile.close();
    }

}
