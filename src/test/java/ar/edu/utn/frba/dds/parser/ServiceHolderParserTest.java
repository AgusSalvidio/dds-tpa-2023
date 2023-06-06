package ar.edu.utn.frba.dds.parser;

import ar.edu.utn.frba.dds.importer.serviceholderimporter.ServiceHolderImportRecord;
import ar.edu.utn.frba.dds.parser.serviceholderparser.ServiceHolderParser;
import ar.edu.utn.frba.dds.parser.serviceholderparser.parsingadapter.DataFileAdapter;
import ar.edu.utn.frba.dds.parser.serviceholderparser.parsingstrategy.ServiceHolderCsvParseStrategy;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceHolderParserTest {

  private String filePath() {
    return "src/main/resources/Organismos.csv";
  }

  @Test
  @DisplayName("Parse service holder import record from file")
  public void parseServiceHolderImportRecordFromFileTest() {
    String filePath = this.filePath();
    List<ServiceHolderImportRecord> parsedRecords = new ArrayList<>();
    ServiceHolderParser parser =
        new ServiceHolderParser(new ServiceHolderCsvParseStrategy(new DataFileAdapter()));

    parsedRecords.addAll(parser.parseFrom(filePath));

    ServiceHolderImportRecord serviceHolderImportRecord = parsedRecords.get(0);

    Assertions.assertEquals(serviceHolderImportRecord.name(), "BCRA");
    Assertions.assertEquals(serviceHolderImportRecord.description(), "Description");

  }


}
