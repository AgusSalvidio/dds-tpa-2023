package ar.edu.utn.frba.dds.ranking;

import java.util.ArrayList;
import java.util.List;

public class WeeklyReport {
  public List<WeeklyRanking> weeklyRankings;


  public WeeklyReport() {
    this.weeklyRankings = new ArrayList<>();
  }

  public void addNewWeeklyRanking(WeeklyRanking weeklyRanking) {
    this.weeklyRankings.add(weeklyRanking);
  }

  public List<WeeklyRanking> weeklyRankings() {
    return this.weeklyRankings;
  }

  public void generateReport() {
    this.weeklyRankings.forEach(WeeklyRanking::generateRanking);
  }

}
