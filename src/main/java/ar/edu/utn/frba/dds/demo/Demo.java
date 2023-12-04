package ar.edu.utn.frba.dds.demo;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.community.Member;
import ar.edu.utn.frba.dds.community.MemberRole;
import ar.edu.utn.frba.dds.entity.Direction;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.EntityName;
import ar.edu.utn.frba.dds.entity.EntityType;
import ar.edu.utn.frba.dds.entity.Organization;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.location.Department;
import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.location.Municipality;
import ar.edu.utn.frba.dds.location.Province;
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
import ar.edu.utn.frba.dds.serviceholder.ServiceHolder;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import java.time.Duration;
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

  public UserDetail userDetailA() throws Exception {
    return UserDetail.composedOf("Miguel", "Garcia", "mg@gmail.com", "0123456789", this.wpp());
  }

  public UserDetail userDetailB() throws Exception {
    return UserDetail.composedOf("Juan", "Rodriguez", "jr@gmail.com", "0123456789", this.mail());
  }

  public UserDetail userDetailC() throws Exception {
    return UserDetail.composedOf("Antonio", "Alvarez", "aa@gmail.com", "0123456789", this.mail());
  }

  public UserDetail userDetailD() throws Exception {
    return UserDetail.composedOf("David", "Romero", "dv@gmail.com", "0123456789", this.wpp());
  }

  public UserDetail userDetailE() throws Exception {
    return UserDetail.composedOf("Javier", "Jimenez", "jj@gmail.com", "0123456789", this.mail());
  }

  public UserDetail userDetailF() throws Exception {
    return UserDetail.composedOf("Daniel", "Perez", "dp@gmail.com", "0123456789", this.wpp());
  }

  public UserDetail userDetailG() throws Exception {
    return UserDetail.composedOf("Ana", "Alonso", "aa@gmail.com", "0123456789", this.mail());
  }

  public UserDetail userDetailH() throws Exception {
    return UserDetail.composedOf("Laura", "Martinez", "lm@gmail.com", "0123456789", this.wpp());
  }

  public UserDetail userDetailI() throws Exception {
    return UserDetail.composedOf("Isabel", "Diaz", "ld@gmail.com", "0123456789", this.mail());
  }

  public UserDetail userDetailJ() throws Exception {
    return UserDetail.composedOf("Carmen", "Moreno", "cm@gmail.com", "0123456789", this.wpp());
  }

  public UserDetail userDetailK() throws Exception {
    return UserDetail.composedOf("Maria", "Fernandez", "mf@gmail.com", "0123456789", this.mail());
  }

  public UserDetail userDetailL() throws Exception {
    return UserDetail.composedOf("Sofia", "Gonzalez", "sg@gmail.com", "0123456789", this.wpp());
  }

  public User userAdmin() throws Exception {
    return User.composedOf("admin", "admin", this.userDetailA(), AuthorizationRole.ADMINISTRADOR);
  }

  public User userEntity() throws Exception {
    return User.composedOf("entidad", "entidad", this.userDetailB(), AuthorizationRole.ENTIDAD);
  }

  public User userUserA() throws Exception {
    return User.composedOf("usera", "usera", this.userDetailC(), AuthorizationRole.USUARIO);
  }

  public User userUserB() throws Exception {
    return User.composedOf("userb", "userb", this.userDetailD(), AuthorizationRole.USUARIO);
  }

  public User userUserC() throws Exception {
    return User.composedOf("userc", "userc", this.userDetailC(), AuthorizationRole.USUARIO);
  }

  public User userUserD() throws Exception {
    return User.composedOf("userd", "userd", this.userDetailD(), AuthorizationRole.USUARIO);
  }

  public User userUserE() throws Exception {
    return User.composedOf("usere", "usere", this.userDetailC(), AuthorizationRole.USUARIO);
  }

  public User userUserF() throws Exception {
    return User.composedOf("userf", "userf", this.userDetailD(), AuthorizationRole.USUARIO);
  }

  public User userUserG() throws Exception {
    return User.composedOf("userg", "userg", this.userDetailC(), AuthorizationRole.USUARIO);
  }

  public User userUserH() throws Exception {
    return User.composedOf("userh", "userh", this.userDetailD(), AuthorizationRole.USUARIO);
  }

  public User userUserI() throws Exception {
    return User.composedOf("useri", "useri", this.userDetailC(), AuthorizationRole.USUARIO);
  }

  public User userUserJ() throws Exception {
    return User.composedOf("userj", "userj", this.userDetailD(), AuthorizationRole.USUARIO);
  }

  public User userUserK() throws Exception {
    return User.composedOf("userk", "userk", this.userDetailC(), AuthorizationRole.USUARIO);
  }

  public User userUserL() throws Exception {
    return User.composedOf("userl", "userl", this.userDetailD(), AuthorizationRole.USUARIO);
  }

  public Community communityA() {
    return Community.composedOf("Comunidad 1", "Comunidad 1");
  }

  public Community communityB() {
    return Community.composedOf("Comunidad 2", "Comunidad 2");
  }

  public Community communityC() {
    return Community.composedOf("Comunidad 3", "Comunidad 3");
  }

  public State inService() {
    return State.composedOf("EN_SERVICIO", "Servicio funcionando normalmente");
  }

  public State notInService() {
    return State.composedOf("FUERA_DE_SERVICIO", "Servicio no funcionando");
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

  public Province buenosAires() {
    Province province = new Province();
    province.setName("BUENOS AIRES");
    return province;
  }

  public Department cabaD(Province province) {
    Department department = new Department();
    department.setName("CABA");
    department.setProvince(province);
    return department;
  }

  public Municipality cabaM(Department department) {
    Municipality municipality = new Municipality();
    municipality.setName("CABA");
    municipality.setDepartment(department);
    return municipality;
  }

  public Location locationA(Municipality municipality) {
    Location location = new Location();
    location.setStreet("AV. FIGUEROA ALCORTA Y AV. PUEYRREDON");
    location.setNumber(100);
    location.setMunicipality(municipality);
    return location;
  }

  public Location locationB(Municipality municipality) {
    Location location = new Location();
    location.setStreet("AV. ALMAFUERTE");
    location.setNumber(300);
    location.setMunicipality(municipality);
    return location;
  }

  public Location locationC(Municipality municipality) {
    Location location = new Location();
    location.setStreet("AV. PUEYRREDON");
    location.setNumber(100);
    location.setMunicipality(municipality);
    return location;
  }

  public Location locationD(Municipality municipality) {
    Location location = new Location();
    location.setStreet("AV. SANTA FE");
    location.setNumber(4100);
    location.setMunicipality(municipality);
    return location;
  }

  public Location locationE(Municipality municipality) {
    Location location = new Location();
    location.setStreet("BARTOLOME MITRE");
    location.setNumber(326);
    location.setMunicipality(municipality);
    return location;
  }

  public Location locationF(Municipality municipality) {
    Location location = new Location();
    location.setStreet("ALSINA");
    location.setNumber(1356);
    location.setMunicipality(municipality);
    return location;
  }

  public EstablishmentType station() {
    EstablishmentType establishmentType = new EstablishmentType();
    establishmentType.setName("ESTACION");
    return establishmentType;
  }

  public EstablishmentType branch() {
    EstablishmentType establishmentType = new EstablishmentType();
    establishmentType.setName("SUCURSAL");
    return establishmentType;
  }

  public Establishment lawSchoolStation(
      EstablishmentType establishmentType, Location location, List<Service> services) {
    Establishment establishment =
        Establishment.composedOf(establishmentType, "FACULTAD DE DERECHO", location);
    establishment.setServices(services);
    return establishment;
  }

  public Establishment hospitalStation(
      EstablishmentType establishmentType, Location location, Service service) {
    Establishment establishment =
        Establishment.composedOf(establishmentType, "HOSPITALES", location);
    establishment.addNewService(service);
    return establishment;
  }

  public Establishment puerreydonStation(
      EstablishmentType establishmentType, Location location, List<Service> services) {
    Establishment establishment =
        Establishment.composedOf(establishmentType, "PUEYRREDON", location);
    establishment.setServices(services);
    return establishment;
  }

  public Establishment italySquareStation(
      EstablishmentType establishmentType, Location location, Service service) {
    Establishment establishment =
        Establishment.composedOf(establishmentType, "PLAZA ITALIA", location);
    establishment.addNewService(service);
    return establishment;
  }

  public Establishment headquarterBranch(
      EstablishmentType establishmentType, Location location, List<Service> services) {
    Establishment establishment =
        Establishment.composedOf(establishmentType, "CASA MATRIZ", location);
    establishment.setServices(services);
    return establishment;
  }

  public Establishment alsinaBranch(
      EstablishmentType establishmentType, Location location, Service service) {
    Establishment establishment =
        Establishment.composedOf(establishmentType, "SUCURSAL ALSINA", location);
    establishment.addNewService(service);
    return establishment;
  }

  public EntityName subwayLineH() {
    EntityName entityName = new EntityName();
    entityName.setName("SUBTE H");
    return entityName;
  }

  public EntityName subwayLineD() {
    EntityName entityName = new EntityName();
    entityName.setName("SUBTE D");
    return entityName;
  }

  public EntityName nationalBank() {
    EntityName entityName = new EntityName();
    entityName.setName("BANCO NACION");
    return entityName;
  }

  public EntityName icbcBank() {
    EntityName entityName = new EntityName();
    entityName.setName("BANCO ICBC");
    return entityName;
  }

  public EntityType subway() {
    EntityType entityType = new EntityType();
    entityType.setName("SUBTE");
    return entityType;
  }

  public EntityType bank() {
    EntityType entityType = new EntityType();
    entityType.setName("BANCO");
    return entityType;
  }

  public TransportLine entityA(
      EntityName entityName, EntityType entityType,
      Establishment departure, Establishment arrival, Direction direction) throws Exception {
    TransportLine transportLine = TransportLine.composedOf(departure, arrival, direction);
    transportLine.setName(entityName);
    transportLine.setType(entityType);
    return transportLine;
  }

  public TransportLine entityB(
      EntityName entityName, EntityType entityType,
      Establishment departure, Establishment arrival, Direction direction) throws Exception {
    TransportLine transportLine = TransportLine.composedOf(departure, arrival, direction);
    transportLine.setName(entityName);
    transportLine.setType(entityType);
    return transportLine;
  }

  public Organization entityC(EntityName entityName, EntityType entityType) throws Exception {
    Organization organization = new Organization();
    organization.setName(entityName);
    organization.setType(entityType);
    return organization;
  }

  public Organization entityD(EntityName entityName, EntityType entityType) throws Exception {
    Organization organization = new Organization();
    organization.setName(entityName);
    organization.setType(entityType);
    return organization;
  }

  public Incident incidentClosed(
      Entity entity, Establishment establishment, Service service, User user,
      List<Community> communities, LocalDateTime start, LocalDateTime finish) {
    Incident incident = new Incident();
    incident.setEntity(entity);
    incident.setEstablishment(establishment);
    incident.setService(service);
    incident.setUser(user);
    incident.setCommunities(communities);
    incident.setIsClosed(true);
    incident.setReportDateTime(start);
    incident.setCloseDateTime(finish);
    incident.setTimeIncident((double) Math.abs(Duration
        .between(incident.getCloseDateTime(), incident.getReportDateTime()).toMinutes()));
    return incident;
  }

  public Incident incidentOpened(
      Entity entity, Establishment establishment, Service service, User user,
      List<Community> communities, LocalDateTime start, LocalDateTime finish) {
    Incident incident = new Incident();
    incident.setEntity(entity);
    incident.setEstablishment(establishment);
    incident.setService(service);
    incident.setUser(user);
    incident.setCommunities(communities);
    incident.setIsClosed(false);
    incident.setReportDateTime(start);
    return incident;
  }

  public void initialize(RelationalDatabasePersistenceSystem persistenceSystem) throws Exception {
    //USERS
    persistenceSystem.startManaging(this.userAdmin());
    persistenceSystem.startManaging(this.userEntity());
    persistenceSystem.startManaging(this.userUserA());
    persistenceSystem.startManaging(this.userUserB());
    persistenceSystem.startManaging(this.userUserC());
    persistenceSystem.startManaging(this.userUserD());
    persistenceSystem.startManaging(this.userUserE());
    persistenceSystem.startManaging(this.userUserF());
    persistenceSystem.startManaging(this.userUserG());
    persistenceSystem.startManaging(this.userUserH());
    persistenceSystem.startManaging(this.userUserI());
    persistenceSystem.startManaging(this.userUserJ());
    persistenceSystem.startManaging(this.userUserK());
    persistenceSystem.startManaging(this.userUserL());
    //User admin = persistenceSystem.userIdentifiedBy(1);
    //User entity = persistenceSystem.userIdentifiedBy(2);
    User userA = persistenceSystem.userIdentifiedBy(3);
    User userB = persistenceSystem.userIdentifiedBy(4);
    User userC = persistenceSystem.userIdentifiedBy(5);
    User userD = persistenceSystem.userIdentifiedBy(6);
    Community communityA = this.communityA();
    communityA.addMemberComposedOf(userA, MemberRole.AFECTADO);
    communityA.addMemberComposedOf(userB, MemberRole.AFECTADO);
    communityA.addMemberComposedOf(userC, MemberRole.AFECTADO);
    communityA.addMemberComposedOf(userD, MemberRole.AFECTADO);
    persistenceSystem.startManaging(communityA);
    User userE = persistenceSystem.userIdentifiedBy(7);
    User userF = persistenceSystem.userIdentifiedBy(8);
    User userG = persistenceSystem.userIdentifiedBy(9);
    User userH = persistenceSystem.userIdentifiedBy(10);
    User userI = persistenceSystem.userIdentifiedBy(11);
    User userJ = persistenceSystem.userIdentifiedBy(12);
    User userL = persistenceSystem.userIdentifiedBy(14);
    Community communityB = this.communityB();
    communityB.addMemberComposedOf(userE, MemberRole.AFECTADO);
    communityB.addMemberComposedOf(userF, MemberRole.AFECTADO);
    communityB.addMemberComposedOf(userG, MemberRole.AFECTADO);
    communityB.addMemberComposedOf(userH, MemberRole.AFECTADO);
    communityB.addMemberComposedOf(userI, MemberRole.AFECTADO);
    communityB.addMemberComposedOf(userJ, MemberRole.AFECTADO);
    communityB.addMemberComposedOf(userA, MemberRole.AFECTADO);
    communityB.addMemberComposedOf(userL, MemberRole.AFECTADO);
    persistenceSystem.startManaging(communityB);
    User userK = persistenceSystem.userIdentifiedBy(13);
    Community communityC = this.communityC();
    communityC.addMemberComposedOf(userK, MemberRole.AFECTADO);
    communityC.addMemberComposedOf(userL, MemberRole.AFECTADO);
    communityC.addMemberComposedOf(userA, MemberRole.AFECTADO);
    persistenceSystem.startManaging(communityC);
    persistenceSystem.startManaging(this.inService());
    persistenceSystem.startManaging(this.notInService());
    State inService = persistenceSystem.stateIdentifiedBy(1);
    State notInService = persistenceSystem.stateIdentifiedBy(2);
    persistenceSystem.startManaging(this.elevatorA(inService));
    persistenceSystem.startManaging(this.elevatorB(notInService));
    persistenceSystem.startManaging(this.escalatorA(inService));
    persistenceSystem.startManaging(this.escalatorB(notInService));
    persistenceSystem.startManaging(this.toiletA(inService));
    persistenceSystem.startManaging(this.toiletB(notInService));
    Service elevatorA = persistenceSystem.serviceIdentifiedBy(1);
    Service escalatorA = persistenceSystem.serviceIdentifiedBy(3);
    Service toiletA = persistenceSystem.serviceIdentifiedBy(5);
    List<Service> servicesA = new ArrayList<>();
    servicesA.add(elevatorA);
    servicesA.add(escalatorA);
    servicesA.add(toiletA);
    Service elevatorB = persistenceSystem.serviceIdentifiedBy(2);
    Service escalatorB = persistenceSystem.serviceIdentifiedBy(4);
    Service toiletB = persistenceSystem.serviceIdentifiedBy(6);
    List<Service> servicesB = new ArrayList<>();
    servicesB.add(elevatorB);
    servicesB.add(escalatorB);
    servicesB.add(toiletB);
    List<Service> servicesC = new ArrayList<>();
    servicesC.add(toiletA);
    servicesC.add(toiletB);
    persistenceSystem.startManaging(this.buenosAires());
    Province buenosAires = persistenceSystem.provinceIdentifiedBy(1);
    persistenceSystem.startManaging(this.cabaD(buenosAires));
    Department cabaD = persistenceSystem.departmentById(1);
    persistenceSystem.startManaging(this.cabaM(cabaD));
    Municipality cabaM = persistenceSystem.municipalityIdentifiedBy(1);
    persistenceSystem.startManaging(this.locationA(cabaM));
    persistenceSystem.startManaging(this.locationB(cabaM));
    persistenceSystem.startManaging(this.locationC(cabaM));
    persistenceSystem.startManaging(this.locationD(cabaM));
    persistenceSystem.startManaging(this.locationE(cabaM));
    persistenceSystem.startManaging(this.locationF(cabaM));
    Location locationA = persistenceSystem.locationIdentifiedBy(1);
    Location locationB = persistenceSystem.locationIdentifiedBy(2);
    Location locationC = persistenceSystem.locationIdentifiedBy(3);
    Location locationD = persistenceSystem.locationIdentifiedBy(4);
    Location locationE = persistenceSystem.locationIdentifiedBy(5);
    Location locationF = persistenceSystem.locationIdentifiedBy(6);
    //ESTABLISHMENTS & TYPES
    persistenceSystem.startManaging(this.station());
    persistenceSystem.startManaging(this.branch());
    EstablishmentType station = persistenceSystem.establishmentTypeIdentifiedBy(1);
    EstablishmentType branch = persistenceSystem.establishmentTypeIdentifiedBy(2);
    persistenceSystem.startManaging(this.lawSchoolStation(station, locationA, servicesA));
    persistenceSystem.startManaging(this.hospitalStation(station, locationB, toiletB));
    persistenceSystem.startManaging(this.puerreydonStation(station, locationC, servicesB));
    persistenceSystem.startManaging(this.italySquareStation(station, locationD, elevatorB));
    persistenceSystem.startManaging(this.headquarterBranch(branch, locationE, servicesC));
    persistenceSystem.startManaging(this.alsinaBranch(branch, locationF, elevatorA));
    persistenceSystem.startManaging(this.subwayLineH());
    persistenceSystem.startManaging(this.subwayLineD());
    persistenceSystem.startManaging(this.nationalBank());
    persistenceSystem.startManaging(this.icbcBank());
    persistenceSystem.startManaging(this.subway());
    persistenceSystem.startManaging(this.bank());
    EntityType subway = persistenceSystem.entityTypeIdentifiedBy(1);
    Establishment lawSchoolStation = persistenceSystem.establishmentIdentifiedBy(1);
    Establishment hospitalStation = persistenceSystem.establishmentIdentifiedBy(2);
    EntityName subwayLineH = persistenceSystem.entityNameIdentifiedBy(1);
    persistenceSystem.startManaging(
        this.entityA(subwayLineH, subway, lawSchoolStation, hospitalStation, Direction.FORWARD));
    Entity entityA = persistenceSystem.entityIdentifiedBy(1);
    entityA.addNewEstablishment(lawSchoolStation);
    persistenceSystem.updateManaging(entityA);
    entityA.addNewEstablishment(hospitalStation);
    persistenceSystem.updateManaging(entityA);
    Establishment puerreydonStation = persistenceSystem.establishmentIdentifiedBy(3);
    Establishment italySquareStation = persistenceSystem.establishmentIdentifiedBy(4);
    EntityName subwayLineD = persistenceSystem.entityNameIdentifiedBy(2);
    persistenceSystem.startManaging(
        this.entityB(subwayLineD, subway, italySquareStation, puerreydonStation, Direction.RETURN));
    Entity entityB = persistenceSystem.entityIdentifiedBy(2);
    entityB.addNewEstablishment(italySquareStation);
    persistenceSystem.updateManaging(entityB);
    entityB.addNewEstablishment(puerreydonStation);
    persistenceSystem.updateManaging(entityB);
    Establishment headquarterBranch = persistenceSystem.establishmentIdentifiedBy(5);
    EntityName nationalBank = persistenceSystem.entityNameIdentifiedBy(3);
    EntityType bank = persistenceSystem.entityTypeIdentifiedBy(2);
    persistenceSystem.startManaging(this.entityC(nationalBank, bank));
    Entity entityC = persistenceSystem.entityIdentifiedBy(3);
    entityC.addNewEstablishment(headquarterBranch);
    persistenceSystem.updateManaging(entityC);
    Establishment alsinaBranch = persistenceSystem.establishmentIdentifiedBy(6);
    entityC.addNewEstablishment(alsinaBranch);
    persistenceSystem.updateManaging(entityC);
    EntityName icbcBank = persistenceSystem.entityNameIdentifiedBy(4);
    persistenceSystem.startManaging(this.entityD(icbcBank, bank));
    Entity entityD = persistenceSystem.entityIdentifiedBy(4);
    entityC.addNewEstablishment(headquarterBranch);
    persistenceSystem.updateManaging(entityD);
    //INCIDENTS
    List<Community> communitiesA = new ArrayList<>();
    communitiesA.add(communityA);
    List<Community> communitiesB = new ArrayList<>();
    communitiesB.add(communityB);
    List<Community> communitiesC = new ArrayList<>();
    communitiesC.add(communityC);
    List<Community> communitiesAbc = new ArrayList<>();
    communitiesAbc.add(communityA);
    communitiesAbc.add(communityB);
    communitiesAbc.add(communityC);
    List<Community> communitiesBc = new ArrayList<>();
    communitiesBc.add(communityB);
    communitiesBc.add(communityC);

    persistenceSystem.startManaging(this.incidentClosed(
        entityA, lawSchoolStation, elevatorA, userA, communitiesAbc,
        LocalDateTime.of(2023, 12, 1, 8, 0),
        LocalDateTime.of(2023, 12, 1, 10, 0)));

    persistenceSystem.startManaging(this.incidentClosed(
        entityA, hospitalStation, toiletB, userB, communitiesA,
        LocalDateTime.of(2023, 12, 1, 9, 0),
        LocalDateTime.of(2023, 12, 1, 9, 30)));

    persistenceSystem.startManaging(this.incidentClosed(
        entityA, lawSchoolStation, escalatorA, userC, communitiesA,
        LocalDateTime.of(2023, 12, 1, 11, 0),
        LocalDateTime.of(2023, 12, 3, 12, 10)));

    persistenceSystem.startManaging(this.incidentClosed(
        entityA, lawSchoolStation, toiletA, userA, communitiesAbc,
        LocalDateTime.of(2023, 12, 3, 8, 0),
        LocalDateTime.of(2023, 12, 3, 10, 0)));

    persistenceSystem.startManaging(this.incidentOpened(
        entityA, lawSchoolStation, toiletA, userA, communitiesAbc,
        LocalDateTime.of(2023, 12, 4, 5, 30),
        null));

    persistenceSystem.startManaging(this.incidentOpened(
        entityA, hospitalStation, toiletB, userB, communitiesA,
        LocalDateTime.of(2023, 12, 4, 9, 45),
        null));

    persistenceSystem.startManaging(this.incidentClosed(
        entityB, italySquareStation, elevatorB, userE, communitiesB,
        LocalDateTime.of(2023, 12, 1, 6, 0),
        LocalDateTime.of(2023, 12, 3, 10, 0)));

    persistenceSystem.startManaging(this.incidentClosed(
        entityB, puerreydonStation, escalatorB, userF, communitiesB,
        LocalDateTime.of(2023, 12, 1, 5, 10),
        LocalDateTime.of(2023, 12, 1, 6, 0)));

    persistenceSystem.startManaging(this.incidentClosed(
        entityB, puerreydonStation, toiletB, userL, communitiesBc,
        LocalDateTime.of(2023, 12, 2, 20, 20),
        LocalDateTime.of(2023, 12, 2, 22, 30)));

    persistenceSystem.startManaging(this.incidentClosed(
        entityB, puerreydonStation, elevatorB, userG, communitiesBc,
        LocalDateTime.of(2023, 12, 3, 14, 25),
        LocalDateTime.of(2023, 12, 3, 17, 55)));

    persistenceSystem.startManaging(this.incidentClosed(
        entityB, puerreydonStation, toiletB, userL, communitiesB,
        LocalDateTime.of(2023, 12, 3, 8, 5),
        LocalDateTime.of(2023, 12, 3, 11, 45)));

    persistenceSystem.startManaging(this.incidentClosed(
        entityB, puerreydonStation, toiletB, userL, communitiesB,
        LocalDateTime.of(2023, 12, 3, 18, 5),
        LocalDateTime.of(2023, 12, 3, 22, 45)));

    persistenceSystem.startManaging(this.incidentOpened(
        entityB, italySquareStation, elevatorB, userI, communitiesB,
        LocalDateTime.of(2023, 12, 4, 10, 30),
        null));

    persistenceSystem.startManaging(this.incidentOpened(
        entityB, puerreydonStation, elevatorB, userI, communitiesB,
        LocalDateTime.of(2023, 12, 4, 10, 37),
        null));

    persistenceSystem.startManaging(this.incidentOpened(
        entityB, puerreydonStation, escalatorB, userJ, communitiesB,
        LocalDateTime.of(2023, 12, 4, 10, 0),
        null));

    persistenceSystem.startManaging(this.incidentClosed(
        entityC, headquarterBranch, toiletA, userK, communitiesC,
        LocalDateTime.of(2023, 12, 2, 10, 15),
        LocalDateTime.of(2023, 12, 4, 17, 25)));

    persistenceSystem.startManaging(this.incidentClosed(
        entityC, headquarterBranch, toiletB, userK, communitiesC,
        LocalDateTime.of(2023, 12, 3, 12, 25),
        LocalDateTime.of(2023, 12, 3, 13, 0)));

    persistenceSystem.startManaging(this.incidentClosed(
        entityC, alsinaBranch, elevatorA, userK, communitiesC,
        LocalDateTime.of(2023, 12, 1, 10, 0),
        LocalDateTime.of(2023, 12, 3, 18, 0)));

    persistenceSystem.startManaging(this.incidentOpened(
        entityC, headquarterBranch, toiletB, userK, communitiesC,
        LocalDateTime.of(2023, 12, 4, 10, 3),
        null));

    persistenceSystem.startManaging(this.incidentClosed(
        entityD, headquarterBranch, toiletA, userK, communitiesC,
        LocalDateTime.of(2023, 12, 4, 8, 30),
        LocalDateTime.of(2023, 12, 4, 14, 50)));

  }

}