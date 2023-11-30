package ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.addons.ownapiservicecreationaddon.BodyDataCreationAddOn;
import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.BodyData;
import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.ImpactResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceIncidentImpactRankingCalculatorTest {
  private Gson gson() {
    return new GsonBuilder().setPrettyPrinting().create();
  }

  /*
  @Test
  @DisplayName("Calculate Impact Ranking")
  public void calculateImpactRanking() throws IOException {
    ServiceIncidentImpactRankingCalculator apiService = ServiceIncidentImpactRankingCalculator.instance();
    BodyData bodyData = new BodyDataCreationAddOn().bodyData();

    List<ImpactResult> results = apiService.calculateImpactRanking(bodyData);

    for(ImpactResult result:results) {
      System.out.println("name: " + result.getName() + " -> impactLevel: " + result.getImpactLevel());
    }
  }
  */
  @Test
  @DisplayName("Calculate Impact Ranking")
  public void calculateImpactRanking() throws IOException {
    ServiceIncidentImpactRankingCalculator mockApi = mock(ServiceIncidentImpactRankingCalculator.class);
    BodyData bodyData = new BodyDataCreationAddOn().bodyData();

    when(mockApi.calculateImpactRanking(any()))
        .thenReturn(
            this.gson().fromJson(
                new FileReader("src/main/resources/calculateImpactRankingTest.json"),
                new TypeToken<List<ImpactResult>>() {
                }.getType()
            )
        );

    List<ImpactResult> results = mockApi.calculateImpactRanking(bodyData);

    Assertions.assertEquals(results.size(), 3);
    Assertions.assertEquals(results.get(0).getName(), "entityTwo");
    Assertions.assertEquals(results.get(0).getImpactLevel(), 8249.7);
    Assertions.assertEquals(results.get(1).getName(), "entityOne");
    Assertions.assertEquals(results.get(1).getImpactLevel(), 4600.200000000001);
    Assertions.assertEquals(results.get(2).getName(), "entityThree");
    Assertions.assertEquals(results.get(2).getImpactLevel(), 3254.9999999999995);
  }
}
