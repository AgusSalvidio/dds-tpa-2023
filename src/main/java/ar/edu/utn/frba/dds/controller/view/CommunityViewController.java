package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.community.MemberRole;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommunityViewController extends Controller {
  ApplicationContext applicationContext;
  String pageTitle = "ABM de Comunidades";
  String actionString = "/communities";
  String listPage = "communities/communities.hbs";
  String unitPage = "communities/communities-registration.hbs";
  String memberPage = "communities/communities-add-member.hbs";

  public CommunityViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("object-list", this.applicationContext.communityManagementSystem().communities());
    context.render(listPage, model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", null);
    model.put("buttonActionLabel", "Registrar");
    context.render(unitPage, model);
  }

  public void edit(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Community communityToEdit =
        this.applicationContext.communityManagementSystem().communityIdentifiedBy(id);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", communityToEdit);
    model.put("buttonActionLabel", "Editar");
    model.put("members", communityToEdit.members());
    context.render(unitPage, model);
  }

  public void update(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Community communityToUpdate =
        this.applicationContext.communityManagementSystem().communityIdentifiedBy(id);
    assignParameters(context, model);
    this.applicationContext.communityManagementSystem().updateCommunityFrom(communityToUpdate, model);
    context.redirect(actionString);
  }

  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();
    assignParameters(context, model);
    this.applicationContext.communityManagementSystem().startManagingCommunityForm(model);
    context.status(HttpStatus.CREATED);
    context.redirect(actionString);
  }

  public void delete(Context context) throws Exception {
    Integer id = Integer.parseInt(context.pathParam("id"));
    Community communityToDelete =
        this.applicationContext.communityManagementSystem().communityIdentifiedBy(id);
    this.applicationContext.communityManagementSystem().stopManagingCommunity(communityToDelete);
    context.redirect(actionString);
  }

  public void addMember(Context context) {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Community communityToEdit =
        this.applicationContext.communityManagementSystem().communityIdentifiedBy(id);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", communityToEdit);
    model.put("buttonActionLabel", "Agregar");
    model.put("members", communityToEdit.members());
    model.put("users", this.applicationContext.userManagementSystem().users());
    model.put("roles", Arrays.stream(MemberRole.values()).toList());
    context.render(memberPage, model);
  }

  public void saveMember(Context context) {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Community communityToUpdate =
        this.applicationContext.communityManagementSystem().communityIdentifiedBy(id);
    model.put("user", context.formParam("user"));
    model.put("role", context.formParam("role"));
    this.applicationContext.communityManagementSystem().updateCommunityMemberFrom(communityToUpdate, model);
    context.redirect(actionString + "/" + id + "/edit");
  }

  private void assignParameters(Context context, Map<String, Object> model) {
    model.put("name", context.formParam("name"));
    model.put("description", context.formParam("description"));
  }
}
