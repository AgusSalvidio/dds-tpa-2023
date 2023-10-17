package ar.edu.utn.frba.dds.helper;

import com.github.jknack.handlebars.Handlebars;
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


}
