package ar.edu.utn.frba.dds.services.communitiesfusion;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.addons.externalapiservicecreationaddon.FusionDataCreationAddOn;
import ar.edu.utn.frba.dds.addons.externalapiservicecreationaddon.RecommendationDataCreationAddOn;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.CommunityData;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.FusionData;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.FusionResult;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.RecommendationsResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceCommunitiesFusionTest {
  private Gson gson() {
    return new GsonBuilder().setPrettyPrinting().create();
  }
  /*
  @Test
  @DisplayName("Fusion")
  public void fusion() throws IOException {
    ServiceCommunitiesFusion apiService = ServiceCommunitiesFusion.instance();
    FusionData fusionData = new FusionDataCreationAddOn().fusionData();
    FusionResult results = apiService.fusion(fusionData);

    for(CommunityData community:results.getMergedCommunity()) {
      System.out.println("id: " + community.getId() + " -> name: " + community.getName());
    }
  }

  @Test
  @DisplayName("Recommendations")
  public void recommendations() throws IOException {
    ServiceCommunitiesFusion apiService = ServiceCommunitiesFusion.instance();
    List<CommunityData> recommendationData = new RecommendationDataCreationAddOn().recommendationData();
    RecommendationsResult results = apiService.recommendations(recommendationData);

    for(FusionData fusionData:results.getPossibleMerges()) {
      System.out.println("community1 -> id: " + fusionData.getCommunity1().getId() + ", name: " + fusionData.community1.getName());
      System.out.println("community2 -> id: " + fusionData.getCommunity2().getId() + ", name: " + fusionData.community2.getName());
    }
  }
  */

  @Test
  @DisplayName("Fusion")
  public void fusion() throws IOException {
    ServiceCommunitiesFusion mockApi = mock(ServiceCommunitiesFusion.class);
    FusionData fusionData = new FusionDataCreationAddOn().fusionData();

    when(mockApi.fusion(any()))
        .thenReturn(
            this.gson().fromJson(
                new FileReader("src/main/resources/fusionTest.json"),
                new TypeToken<FusionResult>() {}.getType()
            )
        );

    FusionResult results = mockApi.fusion(fusionData);

    Assertions.assertEquals(results.getMergedCommunity().size(), 3);
    Assertions.assertEquals(results.getMergedCommunity().get(0).getId(), "101");
    Assertions.assertEquals(results.getMergedCommunity().get(0).getName(), "Comunidad 1");
    Assertions.assertEquals(results.getMergedCommunity().get(1).getId(), "102");
    Assertions.assertEquals(results.getMergedCommunity().get(1).getName(), "Comunidad 2");
    Assertions.assertEquals(results.getMergedCommunity().get(2).getId(), "merged");
    Assertions.assertEquals(results.getMergedCommunity().get(2).getName(), "Merged Community");
  }

  @Test
  @DisplayName("Recommendations")
  public void recommendations() throws IOException {
    ServiceCommunitiesFusion mockApi = mock(ServiceCommunitiesFusion.class);
    List<CommunityData> recommendationData = new RecommendationDataCreationAddOn().recommendationData();

    when(mockApi.recommendations(any()))
        .thenReturn(
            this.gson().fromJson(
                new FileReader("src/main/resources/recommendationsTest.json"),
                new TypeToken<RecommendationsResult>() {}.getType()
            )
        );

    RecommendationsResult results = mockApi.recommendations(recommendationData);

    Assertions.assertEquals(results.getPossibleMerges().size(), 2);
    Assertions.assertEquals(results.getPossibleMerges().get(0).getCommunity1().getId(), "101");
    Assertions.assertEquals(results.getPossibleMerges().get(0).getCommunity1().getName(), "Comunidad 1");
    Assertions.assertEquals(results.getPossibleMerges().get(0).getCommunity2().getId(), "105");
    Assertions.assertEquals(results.getPossibleMerges().get(0).getCommunity2().getName(), "Comunidad 5");
    Assertions.assertEquals(results.getPossibleMerges().get(1).getCommunity1().getId(), "103");
    Assertions.assertEquals(results.getPossibleMerges().get(1).getCommunity1().getName(), "Comunidad 3");
    Assertions.assertEquals(results.getPossibleMerges().get(1).getCommunity2().getId(), "106");
    Assertions.assertEquals(results.getPossibleMerges().get(1).getCommunity2().getName(), "Comunidad 6");
  }
}
