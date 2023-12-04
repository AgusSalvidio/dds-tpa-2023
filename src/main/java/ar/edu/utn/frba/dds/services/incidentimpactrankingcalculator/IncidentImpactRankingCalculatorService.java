package ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator;

import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.BodyData;
import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.Result;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IncidentImpactRankingCalculatorService {
  @POST("calculateImpactRanking")
  Call<List<Result>> calculateImpactRanking(@Body BodyData bodyData);
}
