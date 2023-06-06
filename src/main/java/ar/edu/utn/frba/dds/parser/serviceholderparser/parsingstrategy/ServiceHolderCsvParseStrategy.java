package ar.edu.utn.frba.dds.parser.serviceholderparser.parsingstrategy;

import ar.edu.utn.frba.dds.importer.serviceholderimporter.ServiceHolderImportRecord;
import ar.edu.utn.frba.dds.parser.serviceholderparser.parsingadapter.ServiceHolderCsvAdapter;
import java.util.List;

public class ServiceHolderCsvParseStrategy implements ServiceHolderParsingStrategy {

  ServiceHolderCsvAdapter serviceHolderCsvAdapter;

  public ServiceHolderCsvParseStrategy(ServiceHolderCsvAdapter adapter) {
    this.serviceHolderCsvAdapter = adapter;
  }

  private ServiceHolderCsvAdapter serviceHolderParserAdapter() {
    return this.serviceHolderCsvAdapter;
  }

  public List<ServiceHolderImportRecord> parseFrom(String filePath) {
    return this.serviceHolderParserAdapter().parseFrom(filePath);
  }
}
