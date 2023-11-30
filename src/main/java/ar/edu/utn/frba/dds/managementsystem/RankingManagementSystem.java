package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.ranking.AverageClosingTimeRanking;
import ar.edu.utn.frba.dds.ranking.GreaterDegreeOfImpactRanking;
import ar.edu.utn.frba.dds.ranking.MostReportedIncidentsRanking;
import ar.edu.utn.frba.dds.ranking.WeeklyRanking;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.AverageClosingTimeComparator;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.CommunityComparator;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.GreaterDegreeOfImpactComparator;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.MostReportedIncidentsComparator;
import java.util.List;
import java.util.Map;

public class RankingManagementSystem {

  RelationalDatabasePersistenceSystem persistenceSystem;

  public RankingManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de rankings.";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static RankingManagementSystem workingWith(
      RelationalDatabasePersistenceSystem persistenceSystem) {
    return new RankingManagementSystem(persistenceSystem);
  }

  public void startManagingAverageClosingTimeRanking(AverageClosingTimeRanking ranking) {
    this.persistenceSystem().startManagingAverageClosingTimeRanking(ranking);
  }

  public void stopManagingAverageClosingTimeRanking(AverageClosingTimeRanking ranking) {
    this.persistenceSystem().stopManagingAverageClosingTimeRanking(ranking);
  }

  public void startManagingGreaterDegreeOfImpactRanking(GreaterDegreeOfImpactRanking ranking) {
    this.persistenceSystem().startManagingGreaterDegreeOfImpactRanking(ranking);
  }

  public void stopManagingGreaterDegreeOfImpactRanking(GreaterDegreeOfImpactRanking ranking) {
    this.persistenceSystem().stopManagingGreaterDegreeOfImpactRanking(ranking);
  }

  public void startManagingMostReportedIncidentRanking(MostReportedIncidentsRanking ranking) {
    this.persistenceSystem().startManagingMostReportedIncidentRanking(ranking);
  }

  public void stopManagingMostReportedIncidentRanking(MostReportedIncidentsRanking ranking) {
    this.persistenceSystem().stopManagingMostReportedIncidentRanking(ranking);
  }

  public List<WeeklyRanking> rankings() {
    return this.persistenceSystem().rankings();
  }

  public void updateWith(WeeklyRanking currentRanking, WeeklyRanking updateRanking) {
    currentRanking.synchronizeWith(updateRanking);
  }

  public void startManagingRankingFrom(Map model) {
    String rankingType = model.get("ranking-type").toString();

    switch (rankingType) {
      case "Tiempo de cierre" -> this.startManagingAverageClosingTimeRanking(
          AverageClosingTimeRanking.composedOf(new AverageClosingTimeComparator()));
      case "Mayor impacto" -> this.startManagingGreaterDegreeOfImpactRanking(
          GreaterDegreeOfImpactRanking.composedOf(
              new GreaterDegreeOfImpactComparator(), new CommunityComparator()));
      case "Incidentes reportados" -> this.startManagingMostReportedIncidentRanking(
          MostReportedIncidentsRanking.composedOf(new MostReportedIncidentsComparator()));
      default -> {

      }
    }

  }
}
