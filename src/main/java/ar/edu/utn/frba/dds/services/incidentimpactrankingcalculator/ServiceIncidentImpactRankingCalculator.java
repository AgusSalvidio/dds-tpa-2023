package ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator;

import ar.edu.utn.frba.dds.property.ReadProperties;
import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.BodyData;
import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.ImpactResult;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceIncidentImpactRankingCalculator {
  private static ServiceIncidentImpactRankingCalculator instance = null;
  private static int maxEntriesQuantity = 200;
  private final String urlApi;
  private Retrofit retrofit;

  private ServiceIncidentImpactRankingCalculator() {
    ReadProperties readProp = ReadProperties.getInstance();
    this.urlApi = readProp.getIncidentImpactRankingCalculatorUrl();
    this.retrofit =
        new Retrofit
            .Builder().baseUrl(urlApi).addConverterFactory(GsonConverterFactory.create()).build();
  }

  public static ServiceIncidentImpactRankingCalculator instance() {
    if (instance == null) {
      instance = new ServiceIncidentImpactRankingCalculator();
    }
    return instance;
  }

  public List<ImpactResult> calculateImpactRanking(BodyData bodyData) throws IOException {
    IncidentImpactRankingCalculatorService apiService =
        this.retrofit.create(IncidentImpactRankingCalculatorService.class);
    Call<List<ImpactResult>> call = apiService.calculateImpactRanking(bodyData);
    Response<List<ImpactResult>> response = call.execute();
    return response.body();
  }
}
