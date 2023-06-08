package ar.edu.utn.frba.dds.importer.serviceholderimporter;

public class ServiceHolderImportRecord {
  String name;
  String description;

  public ServiceHolderImportRecord(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String name() {
    return this.name;
  }

  public String description() {
    return this.description;
  }


}
