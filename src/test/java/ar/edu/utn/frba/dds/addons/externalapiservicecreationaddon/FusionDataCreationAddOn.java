package ar.edu.utn.frba.dds.addons.externalapiservicecreationaddon;

import ar.edu.utn.frba.dds.services.communitiesfusion.entities.FusionData;

public class FusionDataCreationAddOn {
  public FusionData fusionData() {
    FusionData fusionData = new FusionData();
    fusionData.setCommunity1(new CommunityDataCreationAddOn().community1());
    fusionData.setCommunity2(new CommunityDataCreationAddOn().community2());
    return fusionData;
  }
}
