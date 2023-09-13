package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.controller.action.GetAllUsersActionController;
import ar.edu.utn.frba.dds.controller.action.RegisterAuthorizationRoleActionController;
import ar.edu.utn.frba.dds.controller.action.RegisterServiceActionController;
import ar.edu.utn.frba.dds.controller.action.RegisterUserActionController;
import ar.edu.utn.frba.dds.controller.view.AdministrationViewController;
import ar.edu.utn.frba.dds.controller.view.AuthorizationRoleRegistrationViewController;
import ar.edu.utn.frba.dds.controller.view.AuthorizationRoleViewController;
import ar.edu.utn.frba.dds.controller.view.HomeViewController;
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

  public static void main(String[] args) {

    ApplicationContext applicationContext = new ApplicationContext();

    initializeTemplateEngine();

    Integer port = Integer.parseInt(System.getProperty("port", "8080"));
    Javalin app = Javalin.create(config()).start(port);

    app.get("/", new HomeViewController(applicationContext));
    app.get("/users", new UserViewController(applicationContext));
    app.get("/all-users", new GetAllUsersActionController(applicationContext));
    app.get("/user-registration", new UserRegistrationViewController(applicationContext));
    app.get("/services", new ServiceViewController(applicationContext));
    app.get("/service-registration", new ServiceRegistrationViewController(applicationContext));
    app.get("/home", new HomeViewController(applicationContext));
    app.get("/administration", new AdministrationViewController(applicationContext));
    app.get("/authorization-roles", new AuthorizationRoleViewController(applicationContext));
    app.get("/authorization-role-registration",
        new AuthorizationRoleRegistrationViewController(applicationContext));

    app.post("/register-user", new RegisterUserActionController(applicationContext));
    app.post("/register-service",
        new RegisterServiceActionController(applicationContext));
    app.post("/register-authorization-role",
        new RegisterAuthorizationRoleActionController(applicationContext));

  }

  private static void initializeTemplateEngine() {
    JavalinRenderer.register(
        (path, model, context) -> { // This renders the template
          Handlebars handlebars = new Handlebars();
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
