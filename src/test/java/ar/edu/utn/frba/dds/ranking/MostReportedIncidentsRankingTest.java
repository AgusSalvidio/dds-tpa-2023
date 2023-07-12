package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityCreationAddOn;
import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityNameCreationAddOn;
import ar.edu.utn.frba.dds.addons.rankingcreationaddon.RankingCreationAddOn;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.MostReportedIncidentsComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MostReportedIncidentsRankingTest {

  private MostReportedIncidentsRanking mostReportedIncidentsRanking() {
    return new RankingCreationAddOn().mostReportedIncidentsRanking();
  }

  @Test
  @DisplayName("Create a ranking")
  public void createARankingTest() {
    MostReportedIncidentsComparator mostReportedIncidentsComparator = new MostReportedIncidentsComparator();
    MostReportedIncidentsRanking mostReportedIncidentsRanking = new MostReportedIncidentsRanking(mostReportedIncidentsComparator);

    Assertions.assertTrue(mostReportedIncidentsRanking.entities().isEmpty());
    Assertions.assertEquals(mostReportedIncidentsRanking.rankingComparator(), mostReportedIncidentsComparator);
  }

  @Test
  @DisplayName("Add new entity to ranking")
  public void addCommunityToRanking() throws Exception {
    MostReportedIncidentsComparator mostReportedIncidentsComparator = new MostReportedIncidentsComparator();
    MostReportedIncidentsRanking mostReportedIncidentsRanking = new MostReportedIncidentsRanking(mostReportedIncidentsComparator);

    Entity entityTest = new EntityCreationAddOn().entityA();
    entityTest.setName(new EntityNameCreationAddOn().subwayHLine());

    Assertions.assertTrue(mostReportedIncidentsRanking.entities().isEmpty());

    mostReportedIncidentsRanking.addEntityToRanking(entityTest);

    Assertions.assertTrue(mostReportedIncidentsRanking.entities().stream().anyMatch(entity -> entity.name().equals(entityTest.name())));
  }

  @Test
  @DisplayName("After generating the ranking, the entities are sorted in order")
  public void afterGeneratingTheRankingTheEntitiesAreSorted() throws Exception {
    MostReportedIncidentsRanking mostReportedIncidentsRanking = this.mostReportedIncidentsRanking();
    Entity entityA = new EntityCreationAddOn().entityA();
    Entity entityC = new EntityCreationAddOn().entityC();
    Entity entityB = new EntityCreationAddOn().entityB();


    mostReportedIncidentsRanking.addEntityToRanking(entityC);
    mostReportedIncidentsRanking.addEntityToRanking(entityA);
    mostReportedIncidentsRanking.addEntityToRanking(entityB);


    Assertions.assertEquals(mostReportedIncidentsRanking.entities(), Arrays.asList(entityC, entityA, entityB));

    mostReportedIncidentsRanking.generateRanking();

    Assertions.assertEquals(mostReportedIncidentsRanking.entities(), Arrays.asList(entityB, entityC, entityA));
  }
}
