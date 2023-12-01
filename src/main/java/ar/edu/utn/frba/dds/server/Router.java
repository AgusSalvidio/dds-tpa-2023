package ar.edu.utn.frba.dds.server;

import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.ADMINISTRADOR;
import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.ENTIDAD;
import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.USUARIO;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.controller.view.AdministrationViewController;
import ar.edu.utn.frba.dds.controller.view.EntityTypeViewController;
import ar.edu.utn.frba.dds.controller.view.EntityNameViewController;
import ar.edu.utn.frba.dds.controller.view.EntityViewController;
import ar.edu.utn.frba.dds.controller.view.EstablishmentTypeViewController;
import ar.edu.utn.frba.dds.controller.view.EstablishmentViewController;
import ar.edu.utn.frba.dds.controller.view.HomeViewController;
import ar.edu.utn.frba.dds.controller.view.IncidentViewController;
import ar.edu.utn.frba.dds.controller.view.LoginViewController;
import ar.edu.utn.frba.dds.controller.view.LogoutViewController;
import ar.edu.utn.frba.dds.controller.view.OrganizationViewController;
import ar.edu.utn.frba.dds.controller.view.RankingViewController;
import ar.edu.utn.frba.dds.controller.view.ServiceHolderViewController;
import ar.edu.utn.frba.dds.controller.view.ServiceViewController;
import ar.edu.utn.frba.dds.controller.view.TransportLineViewController;
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

  //--------------------------------------------------------------------------------------------
  //--------------------------------------------------------------------------------------------



  //--------------------------------------------------------------------------------------------
  //ENTIDADES
  //--------------------------------------------------------------------------------------------
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
  }

  private static void initializeOrganizationEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/organizations",
        new OrganizationViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/organizations/register",
        new OrganizationViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/organizations/{id}/edit",
        new OrganizationViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/organizations/{id}",
        new OrganizationViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/organizations",
        new OrganizationViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/organizations/{id}/delete",
        new OrganizationViewController(applicationContext)::delete, ADMINISTRADOR);
  }

  private static void initializeTransportLinesEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {
    app.get("/transport-lines",
        new TransportLineViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/transport-lines/register",
        new TransportLineViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.get("/transport-lines/{id}/edit",
        new TransportLineViewController(applicationContext)::edit, ADMINISTRADOR);
    app.post("/transport-lines/{id}",
        new TransportLineViewController(applicationContext)::update, ADMINISTRADOR);
    app.post("/transport-lines",
        new TransportLineViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD);
    app.get("/transport-lines/{id}/delete",
        new TransportLineViewController(applicationContext)::delete, ADMINISTRADOR);
  }


  //--------------------------------------------------------------------------------------------
  //RANKING
  //--------------------------------------------------------------------------------------------
  private static void initializeRankingEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/rankings",
        new RankingViewController(applicationContext)::index, ADMINISTRADOR, ENTIDAD);
    app.get("/rankings/impact-degree/cfn",
        new RankingViewController(applicationContext)::cfn, ADMINISTRADOR, ENTIDAD);
    app.post("/rankings/impact-degree",
        new RankingViewController(applicationContext)::impactDegree, ADMINISTRADOR, ENTIDAD);
  }




  private static void initializeIncidentEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/incidents", new IncidentViewController(applicationContext)::index,
        ADMINISTRADOR, ENTIDAD, USUARIO);
    app.get("/incidents/register", new IncidentViewController(applicationContext)::create,
        ADMINISTRADOR, ENTIDAD, USUARIO);
    app.post("/incidents",
        new IncidentViewController(applicationContext)::save, ADMINISTRADOR, ENTIDAD, USUARIO);
    app.get("/incidents/{id}/review", new IncidentViewController(applicationContext)::review,
        ADMINISTRADOR, ENTIDAD, USUARIO);
    app.post("/incidents/{id}/review", new IncidentViewController(applicationContext)::close,
        ADMINISTRADOR, ENTIDAD, USUARIO);
    app.post("/incidents/filter", new IncidentViewController(applicationContext)::filter,
        ADMINISTRADOR, ENTIDAD, USUARIO);
    app.get("/incidents/{id}/close", new IncidentViewController(applicationContext)::close,
        ADMINISTRADOR, ENTIDAD, USUARIO);

  }

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
    //Entidades y Tipos,Nombres
    initializeEntityTypeEndpointsOn(app, applicationContext);
    initializeEntityNameEndpointsOn(app, applicationContext);
    /*
    //Parametricas
    initializeEntityTypeEndpointsOn(app, applicationContext);
    initializeEstablishmentTypeEndpointsOn(app, applicationContext);
    initializeTransportationTypeEndpointsOn(app, applicationContext);
    //Entidades
    initializeEntitiesEndpointsOn(app, applicationContext);
    initializeOrganizationEndpointsOn(app, applicationContext);
    initializeTransportLinesEndpointsOn(app, applicationContext);
    //Rankings
    initializeRankingEndpointsOn(app, applicationContext);


    initializeServiceEndpointsOn(app, applicationContext);
    initializeServiceHolderEndpointsOn(app, applicationContext);
    */
  }

  public static void initialize() throws Exception {

    initializeEndpoints();

  }
}
