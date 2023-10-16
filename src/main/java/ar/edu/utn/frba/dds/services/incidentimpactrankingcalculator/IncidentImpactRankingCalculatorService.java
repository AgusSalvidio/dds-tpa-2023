package ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator;

import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.BodyData;
import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.ImpactResult;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IncidentImpactRankingCalculatorService {
  @POST("calculateImpactRanking")
  Call<List<ImpactResult>> calculateImpactRanking(@Body BodyData bodyData);
}
