package ar.edu.utn.frba.dds.parser.serviceholderparser.parsingstrategy;

import ar.edu.utn.frba.dds.importer.serviceholderimporter.ServiceHolderImportRecord;
import ar.edu.utn.frba.dds.parser.serviceholderparser.parsingadapter.ServiceHolderParserAdapter;
import java.util.List;

public class ServiceHolderParserStrategy implements ServiceHolderParsingStrategy {

  ServiceHolderParserAdapter serviceHolderParserAdapter;

  public ServiceHolderParserStrategy(ServiceHolderParserAdapter adapter) {
    this.serviceHolderParserAdapter = adapter;
  }

  private ServiceHolderParserAdapter serviceHolderParserAdapter() {
    return this.serviceHolderParserAdapter;
  }

  public List<ServiceHolderImportRecord> parseFrom(String filePath) {
    return this.serviceHolderParserAdapter().parseFrom(filePath);
  }
}
