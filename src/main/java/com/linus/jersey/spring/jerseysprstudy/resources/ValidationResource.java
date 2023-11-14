package com.linus.jersey.spring.jerseysprstudy.resources;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/validation")
public class ValidationResource {

  @GET
  @Path("/email")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response validateEmail(@Email @NotBlank @QueryParam("email") String email) {
    return Response.ok(email).build();
  }
}
