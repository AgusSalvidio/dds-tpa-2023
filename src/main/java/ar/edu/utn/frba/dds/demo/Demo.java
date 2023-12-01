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
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.ranking.WeeklyRanking;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import ar.edu.utn.frba.dds.serviceholder.Company;
import ar.edu.utn.frba.dds.serviceholder.GovermentDepartment;
import ar.edu.utn.frba.dds.serviceholder.ServiceHolder;
import ar.edu.utn.frba.dds.services.georef.entities.Municipality;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Demo {
  private List<User> users = new ArrayList<>();
  private List<ServiceHolder> serviceHolders = new ArrayList<>();
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
  private List<WeeklyRanking> rankings = new ArrayList<>();

  public List<User> users() {
    return this.users;
  }

  public List<UserDetail> userDetails() {
    return this.userDetails;
  }

  public List<Service> services() {
    return this.services;
  }

  public List<ServiceHolder> serviceHolders() {
    return this.serviceHolders;
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

  public List<WeeklyRanking> rankings() {
    return this.rankings;
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
    return userDetail;
  }

  public UserDetail basuraIntergalactica() throws Exception {
    UserDetail userDetail = UserDetail.composedOf("Basura",
        "Intergalactica", "basuraintergalactica@gmail.com", "0123456789", this.mail());
    return userDetail;
  }

  public UserDetail basuraIntergalactica2() throws Exception {
    UserDetail userDetail = UserDetail.composedOf("Basura",
        "Intergalactica2", "basuraintergalactica2@gmail.com", "0123456789", this.wpp());
    return userDetail;
  }

  public User hugo() throws Exception {
    User user = User.composedOf("admin", "admin", this.ibarra(), AuthorizationRole.ADMINISTRADOR);
    return user;
  }

  public User basura1() throws Exception {
    User user = User.composedOf("user",
        "user", this.basuraIntergalactica(), AuthorizationRole.USUARIO);
    return user;
  }

  public User basura2() throws Exception {
    User user = User.composedOf("entidad",
        "entidad", this.basuraIntergalactica2(), AuthorizationRole.ENTIDAD);
    return user;
  }

  public State inServiceState() {
    State state = State.composedOf("EN_SERVICIO", "Servicio funcionando normalmente");
    return state;
  }

  public State notInServiceState() {
    State state = State.composedOf("FUERA_DE_SERVICIO", "Servicio no funcionando");
    return state;
  }

  public Elevator elevatorA(State state) {
    return Elevator.composedOf("Ascensor Principal", "Ascensor Principal", state);
  }

  public Elevator elevatorB(State state) {
    return Elevator.composedOf("Ascensor Secundario", "Ascensor Secundario", state);
  }

  public Escalator escalatorA(State state) {
    return Escalator.composedOf("Escalera Mecanica Adaptada", "Escalera Mecanica Adaptada", state);
  }

  public Escalator escalatorB(State state) {
    return Escalator.composedOf("Escalera Nueva", "Escalera Nueva", state);
  }

  public Toilet toiletA(State state) {
    return Toilet.composedOf("Toilet Primer Piso", "Toilet Primer Piso", state);
  }

  public Toilet toiletB(State state) {
    return Toilet.composedOf("Toilet Segundo Piso", "Toilet Segundo Piso", state);
  }



  /*
  public State openIncident() {
    State state = State.composedOf("OPEN", "Open Incident");
    return state;
  }

  public State closedIncident() {
    State state = State.composedOf("CLOSED", "Closed Incident");
    return state;
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
    return incident;
  }

  public Incident workingElevatorIncident() throws Exception {
    Incident incident = Incident.composedOf(
        this.elevatorA(),
        this.workingElevatorObservation(),
        this.curentDateTime(),
        this.basura1()
    );
    return incident;
  }

  public Incident notWorkingEscalatorIncident() throws Exception {
    Incident incident = Incident.composedOf(
        this.escalatorB(),
        this.notWorkingEscalatorObservation(),
        this.curentDateTime(),
        this.basura2()
    );
    return incident;
  }

  public Incident notWorkingToiletIncident() throws Exception {
    Incident incident = Incident.composedOf(
        this.toiletA(),
        this.notWorkingToiletObservation(),
        this.curentDateTime(),
        this.basura2()
    );
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
    return location;
  }

  public Location locationB() {
    Location location = new Location();
    location.setStreet("AV. PUEYRREDON");
    location.setNumber(2111);
    location.setMunicipality(this.caba());
    return location;
  }

  public Location locationC() {
    Location location = new Location();
    location.setStreet("AV. ALMAFUENTE");
    location.setNumber(300);
    location.setMunicipality(this.caba());
    return location;
  }

  public Location locationD() {
    Location location = new Location();
    location.setStreet("BARTOLOME MITRE");
    location.setNumber(326);
    location.setMunicipality(this.caba());
    return location;
  }

  public Location locationE() {
    Location location = new Location();
    location.setStreet("ALSINA");
    location.setNumber(1356);
    location.setMunicipality(this.caba());
    return location;
  }

  public EstablishmentType station() {
    EstablishmentType establishmentType = new EstablishmentType();
    establishmentType.setName("STATION");
    return establishmentType;
  }

  public EstablishmentType branch() {
    EstablishmentType establishmentType = new EstablishmentType();
    establishmentType.setName("BRANCH");
    return establishmentType;
  }

  public Establishment lawSchoolStation() {
    Establishment establishment = new Establishment();
    establishment.setType(this.station());
    establishment.setName("FACULTAD DE DERECHO");
    establishment.setLocation(this.locationA());
    return establishment;
  }

  public Establishment lasHerasStation() {
    Establishment establishment = new Establishment();
    establishment.setType(this.station());
    establishment.setName("LAS HERAS");
    establishment.setLocation(this.locationB());
    return establishment;
  }

  public Establishment onceStation() {
    Establishment establishment = new Establishment();
    establishment.setType(this.station());
    establishment.setName("ONCE");
    establishment.setLocation(this.locationC());
    return establishment;
  }

  public Establishment hospitalStation() {
    Establishment establishment = new Establishment();
    establishment.setType(this.station());
    establishment.setName("HOSPITALES");
    establishment.setLocation(this.locationD());
    return establishment;
  }

  public Establishment headquarterBranch() {
    Establishment establishment = new Establishment();
    establishment.setType(this.branch());
    establishment.setName("CASA MATRIZ");
    establishment.setLocation(this.locationE());
    return establishment;
  }

  public TransportLine entityA() throws Exception {
    TransportLine transportLine = TransportLine.composedOf(
        this.lawSchoolStation(),
        this.lasHerasStation(),
        Direction.FORWARD);
    transportLine.addNewIncident(this.notWorkingElevatorIncident());
    return transportLine;
  }

  public TransportLine entityB() throws Exception {
    TransportLine transportLine = TransportLine.composedOf(
        this.headquarterBranch(),
        this.onceStation(),
        Direction.RETURN);
    transportLine.addNewIncident(this.workingElevatorIncident());
    return transportLine;
  }

  public TransportLine entityC() throws Exception {
    TransportLine transportLine = TransportLine.composedOf(
        this.hospitalStation(),
        this.lasHerasStation(),
        Direction.FORWARD);
    transportLine.addNewIncident(this.notWorkingElevatorIncident());
    transportLine.addNewIncident(this.notWorkingEscalatorIncident());
    return transportLine;
  }

  public Member memberA() throws Exception {
    Member member = Member.composedOf(this.hugo(), "Moderador");
    return member;
  }

  public Member memberB() throws Exception {
    Member member = Member.composedOf(this.basura1(), "Suscriptor");
    return member;
  }

  public Member memberC() throws Exception {
    Member member = Member.composedOf(this.basura2(), "Afectado");
    return member;
  }

  public Community communityA() throws Exception {
    Community community = Community.composedOf("Comunidad 1", "Comunidad de prueba");
    community.addMember(this.memberA());
    community.addMember(this.memberB());
    return community;
  }

  public Community communityB() throws Exception {
    Community community = Community.composedOf("Comunidad 2", "Comunidad de prueba");
    community.addMember(this.memberA());
    community.addMember(this.memberB());
    community.addMember(this.memberC());
    return community;
  }

  public IncidentPerCommunity incidentPerCommunityA() throws Exception {
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(
        this.notWorkingElevatorIncident(),
        this.communityB()
    );
    incidentPerCommunity.setState(this.openIncident());
    return incidentPerCommunity;
  }

  public IncidentPerCommunity incidentPerCommunityB() throws Exception {
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(
        this.notWorkingToiletIncident(),
        this.communityA()
    );
    incidentPerCommunity.setState(this.closedIncident());
    return incidentPerCommunity;
  }

  public IncidentPerCommunity incidentPerCommunityC() throws Exception {
    IncidentPerCommunity incidentPerCommunity = IncidentPerCommunity.composedOf(
        this.workingElevatorIncident(),
        this.communityA()
    );
    incidentPerCommunity.setState(this.closedIncident());
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
    this.services.add(elevatorA);

    Service elevatorB = this.elevatorB();
    this.services.add(elevatorB);

    Service escalatorA = this.escalatorA();
    this.services.add(escalatorA);

    Service escalatorB = this.escalatorB();
    this.services.add(escalatorB);

    Service toiletA = this.toiletA();
    this.services.add(toiletA);

    Service toiletB = this.toiletB();
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

  public void addServiceHolders() throws Exception {

    Company company = Company.composedOf("Empresa 1", "Empresa 1");
    GovermentDepartment govermentDepartment = GovermentDepartment.composedOf(
        "Empresa Estatal 1", "Empresa Estatal 1");
    Company company2 = Company.composedOf("Empresa 2", "Empresa 2");
    GovermentDepartment govermentDepartment2 = GovermentDepartment.composedOf(
        "Empresa Estatal 2", "Empresa Estatal 2");

    this.serviceHolders.add(company);
    this.serviceHolders.add(company2);
    this.serviceHolders.add(govermentDepartment);
    this.serviceHolders.add(govermentDepartment2);

  }

  public void addWeeklyRankings() {
    //TODO
  }

  public void initialize() throws Exception {
    this.addUsers();
    this.addServices();
    this.addIncidents();
    this.addEntities();
    this.addCommunities();
    this.addIncidentsPerCommunity();
    this.addServiceHolders();
  }
  */
  public void initialize(RelationalDatabasePersistenceSystem persistenceSystem) throws Exception {
    //USERS
    persistenceSystem.startManaging(this.hugo());
    persistenceSystem.startManaging(this.basura1());
    persistenceSystem.startManaging(this.basura2());
    //SERVICES & STATES
    persistenceSystem.startManaging(this.inServiceState());
    persistenceSystem.startManaging(this.notInServiceState());
    persistenceSystem.startManaging(this.elevatorA(persistenceSystem.stateIdentifiedBy(1)));
    persistenceSystem.startManaging(this.elevatorB(persistenceSystem.stateIdentifiedBy(2)));
    persistenceSystem.startManaging(this.escalatorA(persistenceSystem.stateNamed("EN_SERVICIO")));
    persistenceSystem
        .startManaging(this.escalatorB(persistenceSystem.stateNamed("FUERA_DE_SERVICIO")));
    persistenceSystem.startManaging(this.toiletA(persistenceSystem.stateIdentifiedBy(1)));
    persistenceSystem
        .startManaging(this.toiletB(persistenceSystem.stateNamed("FUERA_DE_SERVICIO")));
  }
}