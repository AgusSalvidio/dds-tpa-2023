package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityCreationAddOn;
import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityNameCreationAddOn;
import ar.edu.utn.frba.dds.addons.rankingcreationaddon.RankingCreationAddOn;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.AverageClosingTimeComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AverageClosingTimeRankingTest {

  private AverageClosingTimeRanking averageClosingTimeRanking() {
    return new RankingCreationAddOn().averageClosingTimeRanking();
  }

  @Test
  @DisplayName("Create a ranking")
  public void createARankingTest() {
    AverageClosingTimeComparator averageClosingTimeComparator = new AverageClosingTimeComparator();
    AverageClosingTimeRanking averageClosingTimeRanking = new AverageClosingTimeRanking(averageClosingTimeComparator);

    Assertions.assertTrue(averageClosingTimeRanking.entities().isEmpty());
    Assertions.assertEquals(averageClosingTimeRanking.rankingComparator(), averageClosingTimeComparator);
  }

  @Test
  @DisplayName("Add new entity to ranking")
  public void addEntityToRanking() throws Exception {
    AverageClosingTimeComparator averageClosingTimeComparator = new AverageClosingTimeComparator();
    AverageClosingTimeRanking averageClosingTimeRanking = new AverageClosingTimeRanking(averageClosingTimeComparator);

    Entity entityTest = new EntityCreationAddOn().entityA();
    entityTest.setName(new EntityNameCreationAddOn().subwayHLine());

    Assertions.assertTrue(averageClosingTimeRanking.entities().isEmpty());

    averageClosingTimeRanking.addEntityToRanking(entityTest);

    Assertions.assertTrue(averageClosingTimeRanking.entities().stream().anyMatch(entity -> entity.name().equals(entityTest.name())));
  }

  @Test
  @DisplayName("After generating the ranking, the entities are sorted in order")
  public void afterGeneratingTheRankingTheEntitiesAreSorted() throws Exception {
    AverageClosingTimeRanking averageClosingTimeRanking = this.averageClosingTimeRanking();
    Entity entityA = new EntityCreationAddOn().entityA();
    Entity entityB = new EntityCreationAddOn().entityB();

    averageClosingTimeRanking.addEntityToRanking(entityB);
    averageClosingTimeRanking.addEntityToRanking(entityA);

    List<Entity> unsortedList = new ArrayList<>(
        Arrays.asList(
            entityB,
            entityA
        )
    );

    List<Entity> sortedList = new ArrayList<>(
        Arrays.asList(
            entityA,
            entityB
        )
    );

    Assertions.assertEquals(averageClosingTimeRanking.entities(), unsortedList);

    averageClosingTimeRanking.generateRanking();

    Assertions.assertEquals(averageClosingTimeRanking.entities(), sortedList);
  }
}
