package ar.edu.utn.frba.dds.addons.externalapiservicecreationaddon;

import ar.edu.utn.frba.dds.services.communitiesfusion.entities.CommunityData;
import java.util.ArrayList;
import java.util.List;

public class RecommendationDataCreationAddOn {
  public List<CommunityData> recommendationData() {
    List<CommunityData> recommendationData = new ArrayList<>();
    recommendationData.add(new CommunityDataCreationAddOn().community1());
    CommunityData community2 = new CommunityDataCreationAddOn().community2();
    community2.setDegreeOfConfidence(0.7);
    recommendationData.add(community2);
    recommendationData.add(new CommunityDataCreationAddOn().community3());
    recommendationData.add(new CommunityDataCreationAddOn().community4());
    recommendationData.add(new CommunityDataCreationAddOn().community5());
    recommendationData.add(new CommunityDataCreationAddOn().community6());
    return recommendationData;
  }
}
