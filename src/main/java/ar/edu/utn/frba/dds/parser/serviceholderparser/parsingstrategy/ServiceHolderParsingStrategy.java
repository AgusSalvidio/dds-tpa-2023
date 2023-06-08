package ar.edu.utn.frba.dds.parser.serviceholderparser.parsingstrategy;

import ar.edu.utn.frba.dds.importer.serviceholderimporter.ServiceHolderImportRecord;
import java.util.List;

public interface ServiceHolderParsingStrategy {
  public List<ServiceHolderImportRecord> parseFrom(String filePath);
}
