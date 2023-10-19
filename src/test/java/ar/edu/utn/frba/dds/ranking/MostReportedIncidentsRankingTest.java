package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.addons.communitycreationaddon.CommunityCreationAddOn;
import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityCreationAddOn;
import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityNameCreationAddOn;
import ar.edu.utn.frba.dds.addons.incidentcreationaddon.IncidentCreationAddOn;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.EntityIncidentSummary;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.MostReportedIncidentsComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MostReportedIncidentsRankingTest {


  @Test
  @DisplayName("Create a ranking")
  public void createARankingTest() {
    MostReportedIncidentsComparator mostReportedIncidentsComparator = new MostReportedIncidentsComparator();
    MostReportedIncidentsRanking mostReportedIncidentsRanking = new MostReportedIncidentsRanking(mostReportedIncidentsComparator);

    Assertions.assertTrue(mostReportedIncidentsRanking.entityIncidentSummaries().isEmpty());
    Assertions.assertEquals(mostReportedIncidentsRanking.rankingComparator(), mostReportedIncidentsComparator);
  }

  @Test
  @DisplayName("Add new entity to ranking")
  public void addCommunityToRanking() throws Exception {
    MostReportedIncidentsComparator mostReportedIncidentsComparator = new MostReportedIncidentsComparator();
    MostReportedIncidentsRanking mostReportedIncidentsRanking = new MostReportedIncidentsRanking(mostReportedIncidentsComparator);

    Entity entityTest = new EntityCreationAddOn().entityA();
    entityTest.setName(new EntityNameCreationAddOn().subwayHLine());

    Incident incident = new IncidentCreationAddOn().notWorkingElevatorIncident();
    Community community = new CommunityCreationAddOn().firstCommunity();
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(incident, community);

    EntityIncidentSummary entityIncidentSummaryTest = new EntityIncidentSummary(entityTest, incidentPerCommunity);

    Assertions.assertTrue(mostReportedIncidentsRanking.entityIncidentSummaries().isEmpty());

      mostReportedIncidentsRanking.addEntitySummaryToRanking(entityIncidentSummaryTest);

      Assertions.assertTrue(mostReportedIncidentsRanking.entityIncidentSummaries().stream().anyMatch(entityIncidentSummary -> entityIncidentSummary.entity().name().equals(entityTest.name())));
  }
}
