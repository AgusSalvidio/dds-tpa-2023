package ar.edu.utn.frba.dds.parser.serviceholderparser;

import ar.edu.utn.frba.dds.importer.serviceholderimporter.ServiceHolderImportRecord;
import ar.edu.utn.frba.dds.parser.serviceholderparser.parsingstrategy.ServiceHolderParsingStrategy;
import java.util.List;

public class ServiceHolderParser {

  private ServiceHolderParsingStrategy serviceHolderParsingStrategy;

  public ServiceHolderParser(ServiceHolderParsingStrategy serviceHolderParsingStrategy) {
    this.serviceHolderParsingStrategy = serviceHolderParsingStrategy;
  }

  public List<ServiceHolderImportRecord> parseFrom(String filePath) {
    return serviceHolderParsingStrategy.parseFrom(filePath);
  }


}
