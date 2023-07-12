package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.entity.Entity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WeeklyRanking {

  public List<Entity> entities;
  public Comparator<Entity> rankingComparator;

  public WeeklyRanking(Comparator<Entity> rankingComparator) {
    this.entities = new ArrayList<>();
    this.rankingComparator = rankingComparator;
  }

  public void addEntityToRanking(Entity newEntity) {
    this.entities.add(newEntity);
  }

  public List<Entity> entities() {
    return this.entities;
  }

  public Comparator<Entity> rankingComparator() {
    return this.rankingComparator;
  }

  public void generateRanking() {
    this.entities.sort(this.rankingComparator);
  }

}
