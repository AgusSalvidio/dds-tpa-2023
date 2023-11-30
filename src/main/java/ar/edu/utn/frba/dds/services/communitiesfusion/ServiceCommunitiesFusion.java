package ar.edu.utn.frba.dds.services.communitiesfusion;

import ar.edu.utn.frba.dds.services.communitiesfusion.entities.CommunityData;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.FusionData;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.FusionResult;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.RecommendationsResult;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCommunitiesFusion {
  private static ServiceCommunitiesFusion instance = null;
  private static int maxEntriesQuantity = 200;
  private static final String urlAPI = "http://localhost:3000/api/";
  private Retrofit retrofit;

  private ServiceCommunitiesFusion() {
    this.retrofit =
        new Retrofit
            .Builder().baseUrl(urlAPI).addConverterFactory(GsonConverterFactory.create()).build();
  }

  public static ServiceCommunitiesFusion instance() {
    if (instance == null) {
      instance = new ServiceCommunitiesFusion();
    }
    return instance;
  }

  public FusionResult fusion(FusionData fusionData) throws IOException {
    CommunitiesFusionService apiService =
        this.retrofit.create(CommunitiesFusionService.class);
    Call<FusionResult> call = apiService.fusion(fusionData);
    Response<FusionResult> response = call.execute();
    return response.body();
  }

  public RecommendationsResult recommendations(List<CommunityData> recommendationData)
      throws IOException {
    CommunitiesFusionService apiService =
        this.retrofit.create(CommunitiesFusionService.class);
    Call<RecommendationsResult> call = apiService.recommendations(recommendationData);
    Response<RecommendationsResult> response = call.execute();
    return response.body();
  }
}
