package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.addons.rankingcreationaddon.RankingCreationAddOn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class WeeklyReportTest {

  @Test
  @DisplayName("Create weekly report")
  public void createAWeeklyReport() {
    WeeklyReport weeklyReport = new WeeklyReport();

    Assertions.assertTrue(weeklyReport.weeklyRankings().isEmpty());
  }

  @Test
  @DisplayName("Add a new ranking to a weekly report")
  public void addRankingToWeeklyReport() {
    WeeklyReport weeklyReport = new WeeklyReport();
    AverageClosingTimeRanking averageClosingTimeRanking = new RankingCreationAddOn().averageClosingTimeRanking();
    weeklyReport.addNewWeeklyRanking(averageClosingTimeRanking);

    Assertions.assertEquals(weeklyReport.weeklyRankings(), Arrays.asList(averageClosingTimeRanking));
  }
}
