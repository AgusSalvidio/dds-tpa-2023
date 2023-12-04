package ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result {
  public String name;
  public Double impactLevel;

  public Result(String name, Double impactLevel) {
    this.name = name;
    this.impactLevel = impactLevel;
  }
}
