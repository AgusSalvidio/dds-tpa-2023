package ar.edu.utn.frba.dds.ranking.rankingcomparators;

import ar.edu.utn.frba.dds.community.Community;
import java.io.Serializable;
import java.util.Comparator;

public class CommunityComparator implements Comparator<Community>, Serializable {

  @Override
  public int compare(Community firstCommunity, Community secondCommunity) {

    return Double
        .compare(
            firstCommunity.affected().size(),
            secondCommunity.affected().size());
  }
}
