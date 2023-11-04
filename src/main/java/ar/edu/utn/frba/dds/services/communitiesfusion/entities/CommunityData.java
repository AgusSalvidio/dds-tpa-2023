package ar.edu.utn.frba.dds.services.communitiesfusion.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

public class CommunityData {
  @Setter
  @Getter
  public String id;
  @Setter
  @Getter
  public String name;
  @Setter
  @Getter
  public String lastTimeMerged;
  @Setter
  @Getter
  public Double degreeOfConfidence;
  public List<CommonData> members;
  public List<CommonData> interestingServices;
  public List<CommonData> interestingEstablishments;

  public CommunityData() {
    this.members = new ArrayList<>();
    this.interestingServices = new ArrayList<>();
    this.interestingEstablishments = new ArrayList<>();
  }

  public void addNewMember(CommonData newMember) {
    this.members.add(newMember);
  }

  public List<CommonData> getMembers() {
    return this.members.stream().collect(Collectors.toList());
  }

  public void addNewInterestingService(CommonData newService) {
    this.interestingServices.add(newService);
  }

  public List<CommonData> getInterestingServices() {
    return this.interestingServices.stream().collect(Collectors.toList());
  }

  public void addNewInterestingEstablishment(CommonData newEstablishment) {
    this.interestingEstablishments.add(newEstablishment);
  }

  public List<CommonData> getInterestingEstablishment() {
    return this.interestingEstablishments.stream().collect(Collectors.toList());
  }
}
