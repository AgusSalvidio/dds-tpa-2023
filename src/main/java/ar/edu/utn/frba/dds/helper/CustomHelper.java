package ar.edu.utn.frba.dds.helper;

import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.ADMINISTRADOR;
import static ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole.ENTIDAD;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import java.io.IOException;


public class CustomHelper {

  Handlebars handlebars;

  public static CustomHelper workingWith(Handlebars handlebars) {
    return new CustomHelper(handlebars);
  }

  public CustomHelper(Handlebars handlebars) {
    this.handlebars = handlebars;
  }

  public void initializeHelpers() {
    registerHeadMetaTo(handlebars);
    registerCustomHomeNavbarTo(handlebars);
    registerCustomAdministrationNavbarTo(handlebars);
    registerScriptsTo(handlebars);
    registerHasAdminAccessTo(handlebars);
    registerHasEntityAccessTo(handlebars);
    registerIsOpenTo(handlebars);
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

  private static void registerRoleAccessTo(
      Handlebars handlebars,
      String helperName,
      AuthorizationRole role) {
    handlebars.registerHelper(helperName, new Helper<Object>() {
      @Override
      public Object apply(Object context, Options options) throws IOException {
        return role.equals(context);
      }
    });
  }


  private static void registerHasAdminAccessTo(Handlebars handlebars) {
    registerRoleAccessTo(handlebars, "hasAdminAccess", ADMINISTRADOR);

  }

  private static void registerHasEntityAccessTo(Handlebars handlebars) {
    registerRoleAccessTo(handlebars, "hasEntityAccess", ENTIDAD);

  }

  private static void registerIsOpenTo(Handlebars handlebars) {
    handlebars.registerHelper("isOpen", new Helper<Object>() {
      @Override
      public Object apply(Object context, Options options) throws IOException {
        return "OPEN".equals(context);
      }
    });
  }

}

