package com.linus.jersey.spring.jerseysprstudy.validation;

import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.ValidationErrorData;
import org.glassfish.jersey.server.validation.internal.LocalizationMessages;

import javax.inject.Provider;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomValidationExceptionMapper implements ExceptionMapper<ValidationException>  {
  private static final Logger LOGGER = Logger.getLogger(CustomValidationExceptionMapper.class.getName());

  @Context
  private Configuration config;
  @Context
  private Provider<Request> request;

  @Override
  public Response toResponse(final ValidationException exception) {
    if (exception instanceof ConstraintViolationException) {
      LOGGER.log(Level.FINER, LocalizationMessages.CONSTRAINT_VIOLATIONS_ENCOUNTERED(), exception);

      final ConstraintViolationException cve = (ConstraintViolationException) exception;
      final Response.ResponseBuilder response = Response.status(CustomValidationHelper.getResponseStatus(cve));

      // Entity.
      final Object property = config.getProperty(ServerProperties.BV_SEND_ERROR_IN_RESPONSE);
      if (property != null && Boolean.valueOf(property.toString())) {
        final List<Variant> variants = Variant.mediaTypes(
                MediaType.TEXT_PLAIN_TYPE,
                MediaType.TEXT_HTML_TYPE,
                MediaType.APPLICATION_XML_TYPE,
                MediaType.APPLICATION_JSON_TYPE).build();
        final Variant variant = request.get().selectVariant(variants);
        if (variant != null) {
          response.type(variant.getMediaType());
        } else {

          // default media type which will be used only when none media type from {@value variants} is in accept
          // header of original request.
          // could be settable by configuration property.
          response.type(MediaType.TEXT_PLAIN_TYPE);
        }
        response.entity(
                new GenericEntity<>(
                        CustomValidationHelper.constraintViolationToValidationErrors(cve),
                        new GenericType<List<ValidationErrorData>>() {}.getType()
                )
        );
      }

      return response.build();
    } else {
      LOGGER.log(Level.WARNING, LocalizationMessages.VALIDATION_EXCEPTION_RAISED(), exception);

      return Response.serverError().entity(exception.getMessage()).build();
    }
  }
}
