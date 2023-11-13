package com.linus.jersey.spring.jerseysprstudy;

import com.linus.jersey.spring.jerseysprstudy.resources.EmployeeResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * It must extend from ResourceConfig instead of Application, because ServletContainer only supports ResourceConfig.
 */
@Configuration
@ApplicationPath("/jerseysprstudy/v1")
public class AppResourceConfig extends ResourceConfig {

  public AppResourceConfig() {
    register(EmployeeResource.class);
  }

}
