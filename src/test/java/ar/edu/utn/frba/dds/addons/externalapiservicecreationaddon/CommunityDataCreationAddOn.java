package ar.edu.utn.frba.dds.addons.externalapiservicecreationaddon;

import ar.edu.utn.frba.dds.services.communitiesfusion.entities.CommonData;
import ar.edu.utn.frba.dds.services.communitiesfusion.entities.CommunityData;

public class CommunityDataCreationAddOn {
  public CommunityData community1() {
    CommunityData communityData = new CommunityData();
    communityData.setId("101");
    communityData.setName("Comunidad 1");
    communityData.setLastTimeMerged("2022-03-15T12:00:00.000Z");
    communityData.setDegreeOfConfidence(0.9);
    communityData.addNewMember(new CommonData("1", "Miembro 1"));
    communityData.addNewMember(new CommonData("2", "Miembro 2"));
    communityData.addNewMember(new CommonData("3", "Miembro 3"));
    communityData.addNewMember(new CommonData("4", "Miembro 4"));
    communityData.addNewInterestingService(new CommonData("1", "Servicio 1"));
    communityData.addNewInterestingService(new CommonData("2", "Servicio 2"));
    communityData.addNewInterestingService(new CommonData("3", "Servicio 3"));
    communityData.addNewInterestingService(new CommonData("4", "Servicio 4"));
    communityData.addNewInterestingService(new CommonData("4", "Servicio 4"));
    communityData.addNewInterestingEstablishment(new CommonData("1", "Establecimiento 1"));
    communityData.addNewInterestingEstablishment(new CommonData("2", "Establecimiento 2"));
    communityData.addNewInterestingEstablishment(new CommonData("3", "Establecimiento 3"));
    communityData.addNewInterestingEstablishment(new CommonData("4", "Establecimiento 4"));
    return communityData;
  }

  public CommunityData community2() {
    CommunityData communityData = new CommunityData();
    communityData.setId("102");
    communityData.setName("Comunidad 2");
    communityData.setLastTimeMerged("2022-03-15T12:00:00.000Z");
    communityData.setDegreeOfConfidence(0.9);
    communityData.addNewMember(new CommonData("1", "Miembro 1"));
    communityData.addNewMember(new CommonData("2", "Miembro 2"));
    communityData.addNewMember(new CommonData("3", "Miembro 3"));
    communityData.addNewMember(new CommonData("5", "Miembro 5"));
    communityData.addNewInterestingService(new CommonData("1", "Servicio 1"));
    communityData.addNewInterestingService(new CommonData("2", "Servicio 2"));
    communityData.addNewInterestingService(new CommonData("3", "Servicio 3"));
    communityData.addNewInterestingService(new CommonData("4", "Servicio 4"));
    communityData.addNewInterestingService(new CommonData("5", "Servicio 5"));
    communityData.addNewInterestingEstablishment(new CommonData("1", "Establecimiento 1"));
    communityData.addNewInterestingEstablishment(new CommonData("2", "Establecimiento 2"));
    communityData.addNewInterestingEstablishment(new CommonData("3", "Establecimiento 3"));
    communityData.addNewInterestingEstablishment(new CommonData("5", "Establecimiento 5"));
    return communityData;
  }

  public CommunityData community3() {
    CommunityData communityData = new CommunityDataCreationAddOn().community2();
    communityData.setId("103");
    communityData.setName("Comunidad 3");
    communityData.setDegreeOfConfidence(0.8);
    return communityData;
  }

  public CommunityData community4() {
    CommunityData communityData = new CommunityDataCreationAddOn().community2();
    communityData.setId("104");
    communityData.setName("Comunidad 4");
    communityData.setDegreeOfConfidence(0.3);
    return communityData;
  }

  public CommunityData community5() {
    CommunityData communityData = new CommunityDataCreationAddOn().community2();
    communityData.setId("105");
    communityData.setName("Comunidad 5");
    return communityData;
  }

  public CommunityData community6() {
    CommunityData communityData = new CommunityDataCreationAddOn().community3();
    communityData.setId("106");
    communityData.setName("Comunidad 6");
    return communityData;
  }
}
