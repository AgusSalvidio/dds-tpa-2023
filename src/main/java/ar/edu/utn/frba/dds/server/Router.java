package ar.edu.utn.frba.dds.server;

import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.ADMINISTRADOR;
import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.ENTIDAD;
import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.USUARIO;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.controller.view.AdministrationViewController;
import ar.edu.utn.frba.dds.controller.view.HomeViewController;
import ar.edu.utn.frba.dds.controller.view.IncidentViewController;
import ar.edu.utn.frba.dds.controller.view.LoginViewController;
import ar.edu.utn.frba.dds.controller.view.LogoutViewController;
import ar.edu.utn.frba.dds.controller.view.ServiceViewController;
import ar.edu.utn.frba.dds.controller.view.UserViewController;
import io.javalin.Javalin;

public class Router {

  private static Javalin app() {
    return Server.app();
  }

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

  private static void initializeUserEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {
    app.get("/users", new UserViewController(applicationContext)::index, ADMINISTRADOR);
    app.get("/users/register", new UserViewController(applicationContext)::create, ADMINISTRADOR);
    app.post("/users",
        new UserViewController(applicationContext)::save, ADMINISTRADOR);
  }

  private static void initializeServiceEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/services", new ServiceViewController(applicationContext)::index,
        ADMINISTRADOR, ENTIDAD);
    app.get("/services/register",
        new ServiceViewController(applicationContext)::create, ADMINISTRADOR, ENTIDAD);
    app.post("/services", new ServiceViewController(applicationContext)::save,
        ADMINISTRADOR, ENTIDAD);
  }

  private static void initializeIncidentEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/incidents", new IncidentViewController(applicationContext)::index,
        ADMINISTRADOR, ENTIDAD, USUARIO);

  }

  private static void initializeEndpoints() throws Exception {

    Javalin app = app();

    ApplicationContext applicationContext = new ApplicationContext();

    initializeHomeEndpointsOn(app, applicationContext);
    initializeAdministrationEndpointsOn(app, applicationContext);
    initializeLoginEndpointsOn(app, applicationContext);
    initializeLogoutEndpointsOn(app, applicationContext);

    initializeUserEndpointsOn(app, applicationContext);
    initializeServiceEndpointsOn(app, applicationContext);
    initializeIncidentEndpointsOn(app, applicationContext);

  }

  public static void initialize() throws Exception {

    initializeEndpoints();

  }
}
