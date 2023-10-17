package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.controller.action.GetAllUsersActionController;
import ar.edu.utn.frba.dds.controller.action.LogoutActionController;
import ar.edu.utn.frba.dds.controller.action.RegisterAuthorizationRoleActionController;
import ar.edu.utn.frba.dds.controller.action.RegisterLoginActionController;
import ar.edu.utn.frba.dds.controller.action.RegisterServiceActionController;
import ar.edu.utn.frba.dds.controller.action.RegisterUserActionController;
import ar.edu.utn.frba.dds.controller.view.AdministrationViewController;
import ar.edu.utn.frba.dds.controller.view.AuthorizationRoleRegistrationViewController;
import ar.edu.utn.frba.dds.controller.view.AuthorizationRoleViewController;
import ar.edu.utn.frba.dds.controller.view.HomeViewController;
import ar.edu.utn.frba.dds.controller.view.LoginViewController;
import ar.edu.utn.frba.dds.controller.view.ServiceRegistrationViewController;
import ar.edu.utn.frba.dds.controller.view.ServiceViewController;
import ar.edu.utn.frba.dds.controller.view.UserRegistrationViewController;
import ar.edu.utn.frba.dds.controller.view.UserViewController;
import io.javalin.Javalin;

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
    app.get("/administration", new AdministrationViewController(applicationContext));
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
    app.get("/users", new UserViewController(applicationContext));
    app.get("/all-users", new GetAllUsersActionController(applicationContext));
    app.get("/user-registration", new UserRegistrationViewController(applicationContext));
    app.post("/register-user", new RegisterUserActionController(applicationContext));

  }

  private static void initializeServiceEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {

    app.get("/services", new ServiceViewController(applicationContext));
    app.get("/service-registration", new ServiceRegistrationViewController(applicationContext));
    app.post("/register-service",
        new RegisterServiceActionController(applicationContext));
  }

  private static void initializeAuthorizationRoleEndpointsOn(
      Javalin app,
      ApplicationContext applicationContext) {
    app.get("/authorization-roles", new AuthorizationRoleViewController(applicationContext));
    app.get("/authorization-role-registration",
        new AuthorizationRoleRegistrationViewController(applicationContext));

    app.post("/register-authorization-role",
        new RegisterAuthorizationRoleActionController(applicationContext));
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
    initializeAuthorizationRoleEndpointsOn(app, applicationContext);

  }

  public static void initialize() {

    initializeEndpoints();

  }
}
