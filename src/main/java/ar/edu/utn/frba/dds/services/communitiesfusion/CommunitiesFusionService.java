package ar.edu.utn.frba.dds.services.communitiesfusion;

import ar.edu.utn.frba.dds.services.communitiesfusion.entities.CommunityData;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.FusionData;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.FusionResult;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.RecommendationsResult;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CommunitiesFusionService {
  @POST("fusion")
  Call<FusionResult> fusion(@Body FusionData fusionData);

  @POST("recommendations")
  Call<RecommendationsResult> recommendations(@Body List<CommunityData> recommendationData);
}
