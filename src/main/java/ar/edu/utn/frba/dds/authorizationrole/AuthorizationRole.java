package ar.edu.utn.frba.dds.authorizationrole;

import io.javalin.security.RouteRole;


public enum AuthorizationRole implements RouteRole {
  ADMINISTRADOR, ENTIDAD, USUARIO,
}