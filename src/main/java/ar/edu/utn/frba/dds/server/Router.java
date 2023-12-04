package ar.edu.utn.frba.dds.server;

import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.ADMINISTRADOR;
import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.ENTIDAD;
import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.USUARIO;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.controller.view.AdministrationViewController;
import ar.edu.utn.frba.dds.controller.view.CommunityViewController;
import ar.edu.utn.frba.dds.controller.view.DepartmentViewController;
import ar.edu.utn.frba.dds.controller.view.EntityNameViewController;
import ar.edu.utn.frba.dds.controller.view.EntityTypeViewController;
import ar.edu.utn.frba.dds.controller.view.EntityViewController;
import ar.edu.utn.frba.dds.controller.view.EstablishmentTypeViewController;
import ar.edu.utn.frba.dds.controller.view.EstablishmentViewController;
import ar.edu.utn.frba.dds.controller.view.HomeViewController;
import ar.edu.utn.frba.dds.controller.view.IncidentViewController;
import ar.edu.utn.frba.dds.controller.view.LocationViewController;
import ar.edu.utn.frba.dds.controller.view.LoginViewController;
import ar.edu.utn.frba.dds.controller.view.LogoutViewController;
import ar.edu.utn.frba.dds.controller.view.MunicipalityViewController;
import ar.edu.utn.frba.dds.controller.view.ProvinceViewController;
import ar.edu.utn.frba.dds.controller.view.RankingViewController;
import ar.edu.utn.frba.dds.controller.view.ServiceHolderViewController;
import ar.edu.utn.frba.dds.controller.view.ServiceViewController;
import ar.edu.utn.frba.dds.controller.view.UserViewController;
import io.javalin.Javalin;

public class Router {

  private static Javalin app() {
    return Server.app();
  }

  //--------------------------------------------------------------------------------------------
  //PRINCIPALES
  //--------------------------------------------------------------------------------------------
  private static void initializeHomeEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/", new HomeViewController(applicationContext)::index);
    app.get("/home", new HomeViewController(applicationContext)::index);
  }

  private static void initializeAdministrationEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/administration",
        new AdministrationViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
  }

  private static void initializeLoginEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/login", new LoginViewController(applicationContext)::index);
    app.post("/login", new LoginViewController(applicationContext)::save);
  }

  private static void initializeLogoutEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/logout", new LogoutViewController(applicationContext)::index);
  }

  //--------------------------------------------------------------------------------------------
  //MAESTROS
  //--------------------------------------------------------------------------------------------
  private static void initializeUserEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/users",
        new UserViewController(applicationContext)::index, ADMINISTRADOR);
    app.get("/users/register",
        new UserViewController(applicationContext)::create, ADMINISTRADOR);
    app.get("/users/{id}/edit",
        new UserViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/users/{id}",
        new UserViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/users",
        new UserViewController(applicationContext)::save, ADMINISTRADOR);
    app.get("/users/{id}/delete",
        new UserViewController(applicationContext)::delete, ADMINISTRADOR);
  }

  private static void initializeServiceEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/services",
        new ServiceViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/services/register",
        new ServiceViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/services/{id}/edit",
        new ServiceViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/services/{id}",
        new ServiceViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/services",
        new ServiceViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/services/{id}/delete",
        new ServiceViewController(applicationContext)::delete, ADMINISTRADOR);
  }

  private static void initializeEstablishmentEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/establishments",
        new EstablishmentViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/establishments/register",
        new EstablishmentViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/establishments/{id}/edit",
        new EstablishmentViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/establishments/{id}",
        new EstablishmentViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/establishments",
        new EstablishmentViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/establishments/{id}/delete",
        new EstablishmentViewController(applicationContext)::delete, ADMINISTRADOR);
    app.get("/establishments/{id}/add-service",
        new EstablishmentViewController(applicationContext)::addService, ADMINISTRADOR);
    app.post("/establishments/{id}/save-service",
        new EstablishmentViewController(applicationContext)::saveService, ADMINISTRADOR);
  }

  private static void initializeEntitiesEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/entities",
        new EntityViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/entities/register",
        new EntityViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/entities/{id}/edit",
        new EntityViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/entities/{id}",
        new EntityViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/entities",
        new EntityViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/entities/{id}/delete",
        new EntityViewController(applicationContext)::delete, ADMINISTRADOR);
    app.get("/entities/{id}/add-establishment",
        new EntityViewController(applicationContext)::addEstablishment, ADMINISTRADOR);
    app.post("/entities/{id}/save-establishment",
        new EntityViewController(applicationContext)::saveEstablishment, ADMINISTRADOR);
  }

  private static void initializeCommunityEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/communities",
        new CommunityViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/communities/register",
        new CommunityViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/communities/{id}/edit",
        new CommunityViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/communities/{id}",
        new CommunityViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/communities",
        new CommunityViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/communities/{id}/delete",
        new CommunityViewController(applicationContext)::delete, ADMINISTRADOR);
    app.get("/communities/{id}/add-member",
        new CommunityViewController(applicationContext)::addMember, ADMINISTRADOR);
    app.post("/communities/{id}/save-member",
        new CommunityViewController(applicationContext)::saveMember, ADMINISTRADOR);
  }

  //--------------------------------------------------------------------------------------------
  //PARAMETRIA
  //--------------------------------------------------------------------------------------------
  private static void initializeEstablishmentTypeEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/establishment-type",
        new EstablishmentTypeViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/establishment-type/register",
        new EstablishmentTypeViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/establishment-type/{id}/edit",
        new EstablishmentTypeViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/establishment-type/{id}",
        new EstablishmentTypeViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/establishment-type",
        new EstablishmentTypeViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/establishment-type/{id}/delete",
        new EstablishmentTypeViewController(applicationContext)::delete, ADMINISTRADOR);
  }

  private static void initializeEntityTypeEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/entity-type",
        new EntityTypeViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/entity-type/register",
        new EntityTypeViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/entity-type/{id}/edit",
        new EntityTypeViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/entity-type/{id}",
        new EntityTypeViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/entity-type",
        new EntityTypeViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/entity-type/{id}/delete",
        new EntityTypeViewController(applicationContext)::delete, ADMINISTRADOR);
  }

  private static void initializeEntityNameEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/entity-name",
        new EntityNameViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/entity-name/register",
        new EntityNameViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/entity-name/{id}/edit",
        new EntityNameViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/entity-name/{id}",
        new EntityNameViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/entity-name",
        new EntityNameViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/entity-name/{id}/delete",
        new EntityNameViewController(applicationContext)::delete, ADMINISTRADOR);
  }

  private static void initializeProvinceEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/province",
        new ProvinceViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/province/register",
        new ProvinceViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/province/{id}/edit",
        new ProvinceViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/province/{id}",
        new ProvinceViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/province",
        new ProvinceViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/province/{id}/delete",
        new ProvinceViewController(applicationContext)::delete, ADMINISTRADOR);
  }

  private static void initializeDepartmentEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/department",
        new DepartmentViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/department/register",
        new DepartmentViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/department/{id}/edit",
        new DepartmentViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/department/{id}",
        new DepartmentViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/department",
        new DepartmentViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/department/{id}/delete",
        new DepartmentViewController(applicationContext)::delete, ADMINISTRADOR);
  }

  private static void initializeMunicipalityEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/municipality",
        new MunicipalityViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/municipality/register",
        new MunicipalityViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/municipality/{id}/edit",
        new MunicipalityViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/municipality/{id}",
        new MunicipalityViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/municipality",
        new MunicipalityViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/municipality/{id}/delete",
        new MunicipalityViewController(applicationContext)::delete, ADMINISTRADOR);
  }

  //--------------------------------------------------------------------------------------------
  //INCIDENTES
  //--------------------------------------------------------------------------------------------
  private static void initializeIncidentEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/incidents", new IncidentViewController(applicationContext)::index,
        ADMINISTRADOR, ENTIDAD, USUARIO);
    app.get("/incidents/register", new IncidentViewController(applicationContext)::create,
        ADMINISTRADOR, ENTIDAD, USUARIO);
    app.post("/incidents",
        new IncidentViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD, USUARIO);
    /*
    app.get("/incidents/{id}/review", new IncidentViewController(applicationContext)::review,
            ADMINISTRADOR, ENTIDAD, USUARIO);
    app.post("/incidents/{id}/review", new IncidentViewController(applicationContext)::close,
            ADMINISTRADOR, ENTIDAD, USUARIO);
    app.post("/incidents/filter", new IncidentViewController(applicationContext)::filter,
            ADMINISTRADOR, ENTIDAD, USUARIO);
    app.get("/incidents/{id}/close", new IncidentViewController(applicationContext)::close,
            ADMINISTRADOR, ENTIDAD, USUARIO);
     */
  }

  private static void initializeLocationEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/location",
        new LocationViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/location/register",
        new LocationViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/location/{id}/edit",
        new LocationViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/location/{id}",
        new LocationViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/location",
        new LocationViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/location/{id}/delete",
        new LocationViewController(applicationContext)::delete, ADMINISTRADOR);
  }

  //--------------------------------------------------------------------------------------------
  //RANKING
  //--------------------------------------------------------------------------------------------
  private static void initializeRankingEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/rankings/average-closing-time",
        new RankingViewController(applicationContext)::averageClosingTime, ADMINISTRADOR, ENTIDAD);
    app.get("/rankings/greater-amount",
        new RankingViewController(applicationContext)::greaterAmount, ADMINISTRADOR, ENTIDAD);
    app.get("/rankings/impact-degree/cfn",
        new RankingViewController(applicationContext)::cfn, ADMINISTRADOR, ENTIDAD);
    app.post("/rankings/impact-degree",
        new RankingViewController(applicationContext)::impactDegree, ADMINISTRADOR, ENTIDAD);
  }

  //--------------------------------------------------------------------------------------------
  //--------------------------------------------------------------------------------------------

  private static void initializeServiceHolderEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/service-holders", new ServiceHolderViewController(applicationContext)::index,
        ADMINISTRADOR, ENTIDAD);
    app.get("/service-holders/register",
        new ServiceHolderViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.post("/service-holders",
        new ServiceHolderViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);

  }

  private static void initializeEndpoints() throws Exception {
    Javalin app = app();
    ApplicationContext applicationContext = new ApplicationContext();

    //Principales
    initializeHomeEndpointsOn(app, applicationContext);
    initializeAdministrationEndpointsOn(app, applicationContext);
    initializeLoginEndpointsOn(app, applicationContext);
    initializeLogoutEndpointsOn(app, applicationContext);
    //Usuarios
    initializeUserEndpointsOn(app, applicationContext);
    //Servicios y Estados
    initializeServiceEndpointsOn(app, applicationContext);
    //Establecimientos y Tipos
    initializeEstablishmentTypeEndpointsOn(app, applicationContext);
    initializeEstablishmentEndpointsOn(app, applicationContext);
    //Parametricas
    initializeProvinceEndpointsOn(app, applicationContext);
    initializeDepartmentEndpointsOn(app, applicationContext);
    initializeMunicipalityEndpointsOn(app, applicationContext);
    initializeLocationEndpointsOn(app, applicationContext);
    //Entidades y Tipos,Nombres
    initializeEntityTypeEndpointsOn(app, applicationContext);
    initializeEntityNameEndpointsOn(app, applicationContext);
    initializeEntitiesEndpointsOn(app, applicationContext);
    //Comunidades
    initializeCommunityEndpointsOn(app, applicationContext);
    //Incidentes
    initializeIncidentEndpointsOn(app, applicationContext);
    //Rankings
    initializeRankingEndpointsOn(app, applicationContext);

    /*
   initializeServiceEndpointsOn(app, applicationContext);
    initializeServiceHolderEndpointsOn(app, applicationContext);
    */
  }

  public static void initialize() throws Exception {

    initializeEndpoints();

  }
}
