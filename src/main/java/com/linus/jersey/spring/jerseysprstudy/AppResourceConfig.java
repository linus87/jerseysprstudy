package com.linus.jersey.spring.jerseysprstudy;

import com.linus.jersey.spring.jerseysprstudy.resources.EmplyeeResource;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
@ApplicationPath("/jerseysprstudy/v1")
public class AppResourceConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> providers = new LinkedHashSet<Class<?>>();

    providers.add(EmplyeeResource.class);

    return providers;
  }
}
