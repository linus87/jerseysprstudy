package com.linus.jersey.spring.jerseysprstudy.requests;

import javax.validation.constraints.NotBlank;

public class SampleRequest {
  private @NotBlank(message = "{validation.firstName.NotBlank.message}") String firstName;

  private @NotBlank(message = "{validation.lastName.NotBlank.message}") String lastName;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
