package ar.edu.utn.frba.dds.persistencesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageAssignment {
  private String className;
  private List<Object> objectList;

  public StorageAssignment(String className) {
    this.className = className;
    this.objectList = new ArrayList<>();
  }

  public String className() {
    return this.className;
  }

  public List<Object> objectList() {
    return this.objectList.stream().collect(Collectors.toList());
  }

  public void store(Object anObject) {
    this.objectList.add(anObject);
  }

  public void remove(Object anObject) {
    this.objectList.remove(anObject);
  }

}
