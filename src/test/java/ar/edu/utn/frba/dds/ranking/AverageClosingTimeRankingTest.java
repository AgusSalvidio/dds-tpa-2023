package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.addons.communitycreationaddon.CommunityCreationAddOn;
import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityCreationAddOn;
import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityNameCreationAddOn;
import ar.edu.utn.frba.dds.addons.incidentcreationaddon.IncidentCreationAddOn;
import ar.edu.utn.frba.dds.addons.rankingcreationaddon.RankingCreationAddOn;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.EntityIncidentSummary;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.AverageClosingTimeComparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AverageClosingTimeRankingTest {

  private AverageClosingTimeRanking averageClosingTimeRanking() {
    return new RankingCreationAddOn().averageClosingTimeRanking();
  }

  @Test
  @DisplayName("Create a ranking")
  public void createARankingTest() {
    AverageClosingTimeComparator averageClosingTimeComparator = new AverageClosingTimeComparator();
    AverageClosingTimeRanking averageClosingTimeRanking = new AverageClosingTimeRanking(averageClosingTimeComparator);

    Assertions.assertTrue(averageClosingTimeRanking.entityIncidentSummaries().isEmpty());
    Assertions.assertEquals(averageClosingTimeRanking.rankingComparator(), averageClosingTimeComparator);
  }

  @Test
  @DisplayName("Add new entity incident summary to ranking")
  public void addEntityIncidentSummaryToRanking() throws Exception {
    AverageClosingTimeComparator averageClosingTimeComparator = new AverageClosingTimeComparator();
    AverageClosingTimeRanking averageClosingTimeRanking = new AverageClosingTimeRanking(averageClosingTimeComparator);

    Entity entity = new EntityCreationAddOn().entityA();
    entity.setName(new EntityNameCreationAddOn().subwayHLine());

    Incident incident = new IncidentCreationAddOn().notWorkingElevatorIncident();
    Community community = new CommunityCreationAddOn().firstCommunity();
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(incident, community);

    EntityIncidentSummary entityIncidentSummaryTest = new EntityIncidentSummary(entity, incidentPerCommunity);

    Assertions.assertTrue(averageClosingTimeRanking.entityIncidentSummaries().isEmpty());

    averageClosingTimeRanking.addEntitySummaryToRanking(entityIncidentSummaryTest);

    Assertions.assertTrue(averageClosingTimeRanking.entityIncidentSummaries().stream().anyMatch(entityIncidentSummary -> entityIncidentSummary.entity().name().equals(entity.name())));
  }

  @Test
  @DisplayName("After generating the ranking, the entities are sorted in order")
  public void afterGeneratingTheRankingTheEntitiesAreSorted() throws Exception {
    AverageClosingTimeRanking averageClosingTimeRanking = this.averageClosingTimeRanking();
    Entity entityA = new EntityCreationAddOn().entityA();
    Entity entityB = new EntityCreationAddOn().entityB();

    Incident incident = new IncidentCreationAddOn().notWorkingElevatorIncident();
    Community community = new CommunityCreationAddOn().firstCommunity();
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(incident, community);

    EntityIncidentSummary entityIncidentSummaryA = new EntityIncidentSummary(entityA, incidentPerCommunity);
    EntityIncidentSummary entityIncidentSummaryB = new EntityIncidentSummary(entityB, incidentPerCommunity);

    averageClosingTimeRanking.addEntitySummaryToRanking(entityIncidentSummaryA);
    averageClosingTimeRanking.addEntitySummaryToRanking(entityIncidentSummaryB);

    List<EntityIncidentSummary> unsortedList = new ArrayList<>(
        Arrays.asList(
            entityIncidentSummaryA,
            entityIncidentSummaryB
        )
    );

    List<EntityIncidentSummary> sortedList = new ArrayList<>(
        Arrays.asList(
            entityIncidentSummaryA,
            entityIncidentSummaryB
        )
    );

    Assertions.assertEquals(averageClosingTimeRanking.entityIncidentSummaries(), unsortedList);

    averageClosingTimeRanking.generateRanking();

    Assertions.assertEquals(averageClosingTimeRanking.entityIncidentSummaries(), sortedList);
  }
}
