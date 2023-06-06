package ar.edu.utn.frba.dds.parser.serviceholderparser.parsingadapter;

import ar.edu.utn.frba.dds.importer.serviceholderimporter.ServiceHolderImportRecord;
import java.util.List;

public interface ServiceHolderCsvAdapter {
  public List<ServiceHolderImportRecord> parseFrom(String filePath);
}
