package ar.edu.utn.frba.dds.importer.serviceholderimporter;

import ar.edu.utn.frba.dds.parser.serviceholderparser.ServiceHolderParser;
import ar.edu.utn.frba.dds.parser.serviceholderparser.parsingadapter.DataFileAdapter;
import ar.edu.utn.frba.dds.parser.serviceholderparser.parsingstrategy.ServiceHolderCsvParseStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceHolderImporter {
  List<ServiceHolderImportRecord> servicesHolderRecords = new ArrayList<>();
  ServiceHolderParser parser =
      new ServiceHolderParser(new ServiceHolderCsvParseStrategy(new DataFileAdapter()));

  public void importFrom(String filePath) {
    List<ServiceHolderImportRecord> parsedRecords = parser.parseFrom(filePath);
    servicesHolderRecords.addAll(parsedRecords);
  }

  public List<ServiceHolderImportRecord> servicesHolderRecords() {
    return this.servicesHolderRecords.stream()
        .collect(Collectors.toList());
  }

}
