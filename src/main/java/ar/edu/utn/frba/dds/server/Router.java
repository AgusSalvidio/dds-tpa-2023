package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.controller.action.GetAllUsersActionController;
import ar.edu.utn.frba.dds.controller.action.LogoutActionController;
import ar.edu.utn.frba.dds.controller.action.RegisterLoginActionController;
import ar.edu.utn.frba.dds.controller.action.RegisterServiceActionController;
import ar.edu.utn.frba.dds.controller.action.RegisterUserActionController;
import ar.edu.utn.frba.dds.controller.view.AdministrationViewController;
import ar.edu.utn.frba.dds.controller.view.HomeViewController;
import ar.edu.utn.frba.dds.controller.view.LoginViewController;
import ar.edu.utn.frba.dds.controller.view.ServiceRegistrationViewController;
import ar.edu.utn.frba.dds.controller.view.ServiceViewController;
import ar.edu.utn.frba.dds.controller.view.UserRegistrationViewController;
import ar.edu.utn.frba.dds.controller.view.UserViewController;
import io.javalin.Javalin;

import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.ADMINISTRADOR;
import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.ENTIDAD;

public class Router {

  private static Javalin app() {
    return Server.app();
  }

  private static void initializeHomeEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {
    app.get("/", new HomeViewController(applicationContext));
    app.get("/home", new HomeViewController(applicationContext));
  }

  private static void initializeAdministrationEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {
    app.get("/administration",
        new AdministrationViewController(applicationContext), ADMINISTRADOR, ENTIDAD);
  }

  private static void initializeLoginEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {
    app.get("/login", new LoginViewController(applicationContext));
    app.post("/register-login", new RegisterLoginActionController(applicationContext));
  }

  private static void initializeLogoutEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {
    app.get("/logout", new LogoutActionController(applicationContext));
  }

  private static void initializeUserEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {
    app.get("/users", new UserViewController(applicationContext), ADMINISTRADOR);
    app.get("/all-users", new GetAllUsersActionController(applicationContext), ADMINISTRADOR);
    app.get("/user-registration",
        new UserRegistrationViewController(applicationContext), ADMINISTRADOR);
    app.post("/register-user", new RegisterUserActionController(applicationContext), ADMINISTRADOR);

  }

  private static void initializeServiceEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/services", new ServiceViewController(applicationContext), ADMINISTRADOR, ENTIDAD);
    app.get("/service-registration", new ServiceRegistrationViewController(applicationContext),
        ADMINISTRADOR, ENTIDAD);
    app.post("/register-service",
        new RegisterServiceActionController(applicationContext), ADMINISTRADOR, ENTIDAD);
  }

  private static void initializeEndpoints() {

    Javalin app = app();

    ApplicationContext applicationContext = new ApplicationContext();

    initializeHomeEndpointsOn(app, applicationContext);
    initializeAdministrationEndpointsOn(app, applicationContext);
    initializeLoginEndpointsOn(app, applicationContext);
    initializeLogoutEndpointsOn(app, applicationContext);

    initializeUserEndpointsOn(app, applicationContext);
    initializeServiceEndpointsOn(app, applicationContext);

  }

  public static void initialize() {

    initializeEndpoints();

  }
}
