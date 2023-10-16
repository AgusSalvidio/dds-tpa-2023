package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.controller.action.GetAllUsersActionController;
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
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.rendering.JavalinRenderer;
import java.io.IOException;
import java.util.function.Consumer;


public class MainApp {


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

  private static void initializeEndpointsOn(Javalin app) {

    ApplicationContext applicationContext = new ApplicationContext();

    initializeHomeEndpointsOn(app, applicationContext);
    initializeAdministrationEndpointsOn(app, applicationContext);
    initializeLoginEndpointsOn(app, applicationContext);

    initializeUserEndpointsOn(app, applicationContext);
    initializeServiceEndpointsOn(app, applicationContext);
    initializeAuthorizationRoleEndpointsOn(app, applicationContext);


  }

  public static void main(String[] args) {

    initializeTemplateEngine();

    Integer port = Integer.parseInt(System.getProperty("port", "8080"));
    Javalin app = Javalin.create(config()).start(port);

    initializeEndpointsOn(app);
  }

  private static void registerCustomHelperTo(
      Handlebars handlebars,
      String helperName,
      String templateName) {

    handlebars.registerHelper(helperName, (model, options) -> {
      String navbarTemplate = "";
      try {
        String templateLocation = String.format("templates/%s", templateName);
        Template template = handlebars.compile(templateLocation);
        navbarTemplate = template.apply(model);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return navbarTemplate;
    });

  }

  private static void registerHeadMetaTo(Handlebars handlebars) {
    registerCustomHelperTo(handlebars, "headMeta", "head-meta");
  }

  private static void registerScriptsTo(Handlebars handlebars) {
    registerCustomHelperTo(handlebars, "scripts", "scripts");
  }

  private static void registerCustomHomeNavbarTo(Handlebars handlebars) {
    registerCustomHelperTo(handlebars, "customHomeNavbar", "home-navbar");
  }

  private static void registerCustomAdministrationNavbarTo(Handlebars handlebars) {
    registerCustomHelperTo(handlebars, "customAdministrationNavbar", "administration-navbar");
  }

  private static void registerCustomHelpersTo(Handlebars handlebars) {
    registerHeadMetaTo(handlebars);
    registerCustomHomeNavbarTo(handlebars);
    registerCustomAdministrationNavbarTo(handlebars);
    registerScriptsTo(handlebars);
  }

  private static void initializeTemplateEngine() {
    JavalinRenderer.register(
        (path, model, context) -> { // This renders the template
          Handlebars handlebars = new Handlebars();

          registerCustomHelpersTo(handlebars);

          Template template = null;
          try {
            template = handlebars.compile("templates/" + path.replace(".hbs", ""));
            return template.apply(model);
          } catch (IOException e) {
            //
            e.printStackTrace();
            context.status(HttpStatus.NOT_FOUND);
            return "No se encuentra la página indicada...";
          }
        }, ".hbs" // Template file extension
    );
  }

  private static Consumer<JavalinConfig> config() {
    return config -> {
      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/";
        staticFiles.directory = "/public";
      });
    };
  }

}
