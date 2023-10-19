package ar.edu.utn.frba.dds.demo;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.community.Member;
import ar.edu.utn.frba.dds.entity.Direction;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.notification.notificationmean.JakartaAdapter;
import ar.edu.utn.frba.dds.notification.notificationmean.NotificationMean;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByMail;
import ar.edu.utn.frba.dds.notification.notificationmean.NotifyByWhatsApp;
import ar.edu.utn.frba.dds.notification.notificationmean.TwilioAdapter;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import ar.edu.utn.frba.dds.services.georef.entities.Municipality;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Demo {
  private List<User> users = new ArrayList<>();
  private List<UserDetail> userDetails = new ArrayList<>();
  private List<Service> services = new ArrayList<>();
  private List<Incident> incidents = new ArrayList<>();
  private List<Location> locations = new ArrayList<>();
  private List<Establishment> establishments = new ArrayList<>();
  private List<Entity> entities = new ArrayList<>();
  private List<Member> members = new ArrayList<>();
  private List<Community> communities = new ArrayList<>();
  private List<IncidentPerCommunity> incidentPerCommunities = new ArrayList<>();
  private List<State> states = new ArrayList<>();

  public List<User> users() {
    return this.users;
  }

  public List<UserDetail> userDetails() {
    return this.userDetails;
  }

  public List<Service> services() {
    return this.services;
  }


  public List<Incident> incidents() {
    return this.incidents;
  }


  public List<Location> locations() {
    return this.locations;
  }


  public List<Establishment> establishments() {
    return this.establishments;
  }

  public List<Entity> entities() {
    return this.entities;
  }

  public List<Member> members() {
    return this.members;
  }

  public List<Community> communities() {
    return this.communities;
  }

  public List<IncidentPerCommunity> incidentPerCommunities() {
    return this.incidentPerCommunities;
  }

  public List<State> states() {
    return this.states;
  }

  public Demo() throws Exception {
  }

  public NotificationMean wpp() throws Exception {
    return new NotifyByWhatsApp(new TwilioAdapter());
  }

  public NotificationMean mail() throws Exception {
    return new NotifyByMail(new JakartaAdapter());
  }

  public UserDetail ibarra() throws Exception {
    UserDetail userDetail = UserDetail.composedOf("Hugo",
        "Ibarra", "ibarraneta@gmail.com", "0123456789", this.wpp());
    userDetail.setId(1);
    return userDetail;
  }

  public UserDetail basuraIntergalactica() throws Exception {
    UserDetail userDetail = UserDetail.composedOf("Basura",
        "Intergalactica", "basuraintergalactica@gmail.com", "0123456789", this.mail());
    userDetail.setId(2);
    return userDetail;
  }

  public UserDetail basuraIntergalactica2() throws Exception {
    UserDetail userDetail = UserDetail.composedOf("Basura",
        "Intergalactica2", "basuraintergalactica2@gmail.com", "0123456789", this.wpp());
    userDetail.setId(3);
    return userDetail;
  }

  public User hugo() throws Exception {
    User user = User.composedOf("admin", "admin", this.ibarra(), AuthorizationRole.ADMINISTRADOR);
    user.setId(1);
    return user;
  }

  public User basura1() throws Exception {
    User user = User.composedOf("user",
        "user", this.basuraIntergalactica(), AuthorizationRole.USUARIO);
    user.setId(2);
    return user;
  }

  public User basura2() throws Exception {
    User user = User.composedOf("entidad",
        "entidad", this.basuraIntergalactica2(), AuthorizationRole.ENTIDAD);
    user.setId(3);
    return user;
  }

  public State inServiceState() {
    State state = State.composedOf("IN_SERVICE", "Normally working Service");
    state.setId(1);
    return state;
  }

  public State notInServiceState() {
    State state = State.composedOf("NOT_IN_SERVICE", "Not working Service");
    state.setId(2);
    return state;
  }

  public State openIncident() {
    State state = State.composedOf("OPEN", "Open Incident");
    state.setId(3);
    return state;
  }

  public State closedIncident() {
    State state = State.composedOf("CLOSED", "Closed Incident");
    state.setId(4);
    return state;
  }

  public Elevator elevatorA() {
    return Elevator.composedOf("Ascensor", "Ascensor Principal", this.inServiceState());
  }

  public Elevator elevatorB() {
    return Elevator.composedOf("Ascensor", "Ascensor Principal", this.notInServiceState());
  }

  public Escalator escalatorA() {
    return Escalator.composedOf("Escalera", "Escalera Mecanica Adaptada", this.notInServiceState());
  }

  public Escalator escalatorB() {
    return Escalator.composedOf("Escalera", "Escalera Mecanica Adaptada", this.inServiceState());
  }

  public Toilet toiletA() {
    return Toilet.composedOf("Toilet", "Toilet Primer Piso", this.inServiceState());
  }

  public Toilet toiletB() {
    return Toilet.composedOf("Toilet", "Toilet Primer Piso", this.notInServiceState());
  }

  private LocalDateTime curentDateTime() {
    return LocalDateTime.of(2023, 7, 2, 23, 40);
  }

  private String notWorkingElevatorObservation() {
    return "No funciona el ascensor correctamente";
  }

  private String workingElevatorObservation() {
    return "Funciona el ascensor correctamente";
  }

  private String notWorkingEscalatorObservation() {
    return "No funciona la escalera mecanica correctamente";
  }

  private String notWorkingToiletObservation() {
    return "No funciona el baño correctamente";
  }

  public Incident notWorkingElevatorIncident() throws Exception {
    Incident incident = Incident.composedOf(
        this.elevatorB(),
        this.notWorkingElevatorObservation(),
        this.curentDateTime(),
        this.hugo()
    );
    incident.setId(1);
    return incident;
  }

  public Incident workingElevatorIncident() throws Exception {
    Incident incident = Incident.composedOf(
        this.elevatorA(),
        this.workingElevatorObservation(),
        this.curentDateTime(),
        this.basura1()
    );
    incident.setId(2);
    return incident;
  }

  public Incident notWorkingEscalatorIncident() throws Exception {
    Incident incident = Incident.composedOf(
        this.escalatorB(),
        this.notWorkingEscalatorObservation(),
        this.curentDateTime(),
        this.basura2()
    );
    incident.setId(3);
    return incident;
  }

  public Incident notWorkingToiletIncident() throws Exception {
    Incident incident = Incident.composedOf(
        this.toiletA(),
        this.notWorkingToiletObservation(),
        this.curentDateTime(),
        this.basura2()
    );
    incident.setId(4);
    return incident;
  }

  public Municipality caba() {
    Municipality municipality = new Municipality();
    municipality.setNombre("CABA");
    return municipality;
  }

  public Location locationA() {
    Location location = new Location();
    location.setStreet("AV. FIGUEROA ALCORTA Y AV. PUEYRREDON");
    location.setNumber(100);
    location.setMunicipality(this.caba());
    location.setId(1);
    return location;
  }

  public Location locationB() {
    Location location = new Location();
    location.setStreet("AV. PUEYRREDON");
    location.setNumber(2111);
    location.setMunicipality(this.caba());
    location.setId(2);
    return location;
  }

  public Location locationC() {
    Location location = new Location();
    location.setStreet("AV. ALMAFUENTE");
    location.setNumber(300);
    location.setMunicipality(this.caba());
    location.setId(3);
    return location;
  }

  public Location locationD() {
    Location location = new Location();
    location.setStreet("BARTOLOME MITRE");
    location.setNumber(326);
    location.setMunicipality(this.caba());
    location.setId(4);
    return location;
  }

  public Location locationE() {
    Location location = new Location();
    location.setStreet("ALSINA");
    location.setNumber(1356);
    location.setMunicipality(this.caba());
    location.setId(5);
    return location;
  }

  public EstablishmentType station() {
    EstablishmentType establishmentType = new EstablishmentType();
    establishmentType.setName("STATION");
    establishmentType.setId(1);
    return establishmentType;
  }

  public EstablishmentType branch() {
    EstablishmentType establishmentType = new EstablishmentType();
    establishmentType.setName("BRANCH");
    establishmentType.setId(2);
    return establishmentType;
  }

  public Establishment lawSchoolStation() {
    Establishment establishment = new Establishment();
    establishment.setType(this.station());
    establishment.setName("FACULTAD DE DERECHO");
    establishment.setLocation(this.locationA());
    establishment.setId(1);
    return establishment;
  }

  public Establishment lasHerasStation() {
    Establishment establishment = new Establishment();
    establishment.setType(this.station());
    establishment.setName("LAS HERAS");
    establishment.setLocation(this.locationB());
    establishment.setId(2);
    return establishment;
  }

  public Establishment onceStation() {
    Establishment establishment = new Establishment();
    establishment.setType(this.station());
    establishment.setName("ONCE");
    establishment.setLocation(this.locationC());
    establishment.setId(3);
    return establishment;
  }

  public Establishment hospitalStation() {
    Establishment establishment = new Establishment();
    establishment.setType(this.station());
    establishment.setName("HOSPITALES");
    establishment.setLocation(this.locationD());
    establishment.setId(4);
    return establishment;
  }

  public Establishment headquarterBranch() {
    Establishment establishment = new Establishment();
    establishment.setType(this.branch());
    establishment.setName("CASA MATRIZ");
    establishment.setLocation(this.locationE());
    establishment.setId(5);
    return establishment;
  }

  public TransportLine entityA() throws Exception {
    TransportLine transportLine = TransportLine.composedOf(
        this.lawSchoolStation(),
        this.lasHerasStation(),
        Direction.FORWARD);
    transportLine.addNewIncident(this.notWorkingElevatorIncident());
    transportLine.setId(1);
    return transportLine;
  }

  public TransportLine entityB() throws Exception {
    TransportLine transportLine = TransportLine.composedOf(
        this.headquarterBranch(),
        this.onceStation(),
        Direction.RETURN);
    transportLine.addNewIncident(this.workingElevatorIncident());
    transportLine.setId(2);
    return transportLine;
  }

  public TransportLine entityC() throws Exception {
    TransportLine transportLine = TransportLine.composedOf(
        this.hospitalStation(),
        this.lasHerasStation(),
        Direction.FORWARD);
    transportLine.addNewIncident(this.notWorkingElevatorIncident());
    transportLine.addNewIncident(this.notWorkingEscalatorIncident());
    transportLine.setId(3);
    return transportLine;
  }

  public Member memberA() throws Exception {
    Member member = Member.composedOf(this.hugo(), "Moderador");
    member.setId(1);
    return member;
  }

  public Member memberB() throws Exception {
    Member member = Member.composedOf(this.basura1(), "Suscriptor");
    member.setId(2);
    return member;
  }

  public Member memberC() throws Exception {
    Member member = Member.composedOf(this.basura2(), "Afectado");
    member.setId(3);
    return member;
  }

  public Community communityA() throws Exception {
    Community community = Community.composedOf("Comunidad 1", "Comunidad de prueba");
    community.addMember(this.memberA());
    community.addMember(this.memberB());
    community.setId(1);
    return community;
  }

  public Community communityB() throws Exception {
    Community community = Community.composedOf("Comunidad 2", "Comunidad de prueba");
    community.addMember(this.memberA());
    community.addMember(this.memberB());
    community.addMember(this.memberC());
    community.setId(2);
    return community;
  }

  public IncidentPerCommunity incidentPerCommunityA() throws Exception {
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(
        this.notWorkingElevatorIncident(),
        this.communityB()
    );
    incidentPerCommunity.setState(this.openIncident());
    incidentPerCommunity.setId(1);
    return incidentPerCommunity;
  }

  public IncidentPerCommunity incidentPerCommunityB() throws Exception {
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(
        this.notWorkingToiletIncident(),
        this.communityA()
    );
    incidentPerCommunity.setState(this.closedIncident());
    incidentPerCommunity.setId(2);
    return incidentPerCommunity;
  }

  public IncidentPerCommunity incidentPerCommunityC() throws Exception {
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(
        this.workingElevatorIncident(),
        this.communityA()
    );
    incidentPerCommunity.setState(this.closedIncident());
    incidentPerCommunity.setId(3);
    return incidentPerCommunity;
  }

  public void addUsers() throws Exception {
    this.userDetails.add(this.ibarra());
    this.users.add(this.hugo());
    this.userDetails.add(this.basuraIntergalactica());
    this.users.add(this.basura1());
    this.userDetails.add(this.basuraIntergalactica2());
    this.users.add(this.basura2());
  }

  public void addServices() {
    this.states.add(this.inServiceState());
    this.states.add(this.notInServiceState());
    Service elevatorA = this.elevatorA();
    this.elevatorA().setId(1);
    this.services.add(elevatorA);
    Service elevatorB = this.elevatorB();
    this.elevatorB().setId(2);
    this.services.add(elevatorB);
    Service escalatorA = this.escalatorA();
    this.escalatorA().setId(3);
    this.services.add(escalatorA);
    Service escalatorB = this.escalatorB();
    this.escalatorB().setId(4);
    this.services.add(escalatorB);
    Service toiletA = this.toiletA();
    this.toiletA().setId(5);
    this.services.add(toiletA);
    Service toiletB = this.toiletB();
    this.toiletB().setId(6);
    this.services.add(toiletB);
  }

  public void addIncidents() throws Exception {
    this.incidents.add(this.notWorkingElevatorIncident());
    this.incidents.add(this.workingElevatorIncident());
    this.incidents.add(this.notWorkingEscalatorIncident());
    this.incidents.add(this.notWorkingToiletIncident());
  }

  public void addEntities() throws Exception {
    this.locations.add(this.locationA());
    this.locations.add(this.locationB());
    this.locations.add(this.locationC());
    this.locations.add(this.locationD());
    this.locations.add(this.locationE());
    this.establishments.add(this.lawSchoolStation());
    this.establishments.add(this.lasHerasStation());
    this.establishments.add(this.onceStation());
    this.establishments.add(this.hospitalStation());
    this.establishments.add(this.headquarterBranch());
    this.entities.add(this.entityA());
    this.entities.add(this.entityB());
    this.entities.add(this.entityC());
  }

  public void addCommunities() throws Exception {
    this.members.add(this.memberA());
    this.members.add(this.memberB());
    this.members.add(this.memberC());
    this.communities.add(this.communityA());
    this.communities.add(this.communityB());
  }

  public void addIncidentsPerCommunity() throws Exception {
    this.states.add(this.openIncident());
    this.states.add(this.closedIncident());
    this.incidentPerCommunities.add(this.incidentPerCommunityA());
    this.incidentPerCommunities.add(this.incidentPerCommunityB());
    this.incidentPerCommunities.add(this.incidentPerCommunityC());
  }

  public void initialize() throws Exception {
    this.addUsers();
    this.addServices();
    this.addIncidents();
    this.addEntities();
    this.addCommunities();
    this.addIncidentsPerCommunity();

  }
}
