package com.linus.jersey.spring.jerseysprstudy.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linus.jersey.spring.jerseysprstudy.validation.ResourceValidationError;
import org.glassfish.jersey.message.MessageUtils;
import org.glassfish.jersey.server.validation.ValidationErrorData;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;

@Provider
public class ValidationErrorMessageBodyWriter implements MessageBodyWriter<Object> {
  private final ObjectMapper objectMapper = new ObjectMapper();
  @Override
  public boolean isWriteable(final Class<?> type,
                             final Type genericType,
                             final Annotation[] annotations,
                             final MediaType mediaType) {
    return isSupportedMediaType(mediaType) && isSupportedType(type, genericType);
  }

  private boolean isSupportedType(final Class<?> type, final Type genericType) {
    if (ValidationErrorData.class.isAssignableFrom(type)) {
      return true;
    } else if (Collection.class.isAssignableFrom(type) && (genericType instanceof ParameterizedType)) {
      return ValidationErrorData.class
              .isAssignableFrom((Class) ((ParameterizedType) genericType).getActualTypeArguments()[0]);
    }
    return false;
  }

  private boolean isSupportedMediaType(final MediaType mediaType) {
    return MediaType.APPLICATION_JSON_TYPE.equals(mediaType) || MediaType.APPLICATION_XML_TYPE.equals(mediaType);
  }

  @Override
  public void writeTo(final Object entity,
                      final Class<?> type,
                      final Type genericType,
                      final Annotation[] annotations,
                      final MediaType mediaType,
                      final MultivaluedMap<String, Object> httpHeaders,
                      final OutputStream entityStream) throws IOException, WebApplicationException {
    final Collection<ValidationErrorData> errors;

    if (entity instanceof ValidationErrorData) {
      errors = Collections.singleton((ValidationErrorData) entity);
    } else {
      //noinspection unchecked
      errors = (Collection<ValidationErrorData>) entity;
    }

    final Collection<ResourceValidationError> finalErrors;
    if (errors != null) {
      finalErrors = errors.stream()
              .map(error -> new ResourceValidationError(error.getMessage(), error.getInvalidValue()))
              .collect(java.util.stream.Collectors.toList());
    } else {
      finalErrors = Collections.emptyList();
    }

    entityStream.write(objectMapper.writeValueAsString(finalErrors).getBytes(MessageUtils.getCharset(mediaType)));
    entityStream.flush();
  }
}
