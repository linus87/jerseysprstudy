package com.linus.jersey.spring.jerseysprstudy.validation;

public class ResourceValidationError {

  protected final String message;

  protected final String invalidValue;

  /**
   * Create a {@code ValidationError} instance.
   *
   * @param message interpolated error message.
   * @param invalidValue value that failed to pass constraints.
   */
  /* package */
  public ResourceValidationError(final String message, final String invalidValue) {
    this.message = message;
    this.invalidValue = invalidValue;
  }

  public String getMessage() {
    return message;
  }

  public String getInvalidValue() {
    return invalidValue;
  }

}
