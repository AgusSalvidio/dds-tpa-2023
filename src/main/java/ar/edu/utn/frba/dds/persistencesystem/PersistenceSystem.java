package ar.edu.utn.frba.dds.persistencesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
  This implementation is not the best especially with all the casting made to store objects.
  However, knowing that Database persistence its on the way, we can bite the bullet with this.
  Or like Hernan W. said, if you think it can be improved, then do it yourself.*/
public interface PersistenceSystem {

  public void addObjectTypeToStore(String anObjectClassName);

  public void storeObjectTyped(String anObjectClassName, Object anObject);

  public void removeObjectTyped(String anObjectClassName, Object anObject);

  public Object findObjectTyped(String anObjectClassName, Object anObject);

  public List<Object> objectsFrom(String anObjectClassName);
}
