package ar.edu.utn.frba.dds.parser.serviceholderparser.parsingadapter;

import ar.edu.utn.frba.dds.datafile.DataFile;
import ar.edu.utn.frba.dds.datafile.Field;
import ar.edu.utn.frba.dds.datafile.FieldNumber;
import ar.edu.utn.frba.dds.datafile.FieldString;
import ar.edu.utn.frba.dds.datafile.FileDelimited;
import ar.edu.utn.frba.dds.importer.serviceholderimporter.ServiceHolderImportRecord;
import java.util.List;

public class DataFileAdapter implements ServiceHolderCsvAdapter {

  DataFile dataFile = new FileDelimited("Organization");

  private void configDataFileWith(String filePath) {
    dataFile.setFilePath(filePath);
    dataFile.setRowDelimiter("CrLf");
    dataFile.setColDelimiter(";");
    dataFile.setFirstRowHasColumnNames(false);
    dataFile.addField(new FieldNumber(0, "ID", 10));
    dataFile.addField(new FieldString(1, "Name", 255));
  }

  private List<ServiceHolderImportRecord> serviceHolderImportRecordsFrom(List<Field> allRows) {

    return allRows.stream().filter(field -> field.name() == "Name").toList()
        .stream().map(fieldString ->
            new ServiceHolderImportRecord(fieldString.getStringValue(), "Description"))
        .toList();
  }


  private List<ServiceHolderImportRecord> parseServiceHolders() {

    List<Field> allRows;

    dataFile.openFile();
    allRows = dataFile.allRows();
    dataFile.close();

    return this.serviceHolderImportRecordsFrom(allRows);

  }

  public List<ServiceHolderImportRecord> parseFrom(String filePath) {

    this.configDataFileWith(filePath);

    return this.parseServiceHolders();

  }
}
