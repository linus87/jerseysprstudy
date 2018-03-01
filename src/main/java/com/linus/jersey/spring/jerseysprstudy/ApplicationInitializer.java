package com.linus.jersey.spring.jerseysprstudy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * The annotation @Order(Ordered.HIGHEST_PRECEDENCE) is used to ensure that our
 * initializer is executed before the Jersey-Spring provided default
 * initializer.
 * 
 * @author lyan2
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.setInitParameter("contextConfigLocation",
				"com.baeldung.server");
	}

}
