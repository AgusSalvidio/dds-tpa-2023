package ar.edu.utn.frba.dds.services.communitiesfusion.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FusionResult {
  public List<CommunityData> mergedCommunity;
}