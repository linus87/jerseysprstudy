package com.linus.jersey.spring.jerseysprstudy.resources;

import com.linus.jersey.spring.jerseysprstudy.requests.SampleRequest;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
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

  @POST
  @Path("/json")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response validateEmail(@Valid SampleRequest requestBody) {
    return Response.ok(requestBody).build();
  }
}
