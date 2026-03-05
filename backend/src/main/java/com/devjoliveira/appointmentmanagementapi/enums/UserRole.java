package com.devjoliveira.appointmentmanagementapi.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
  ROLE_ADMIN("ADMIN"),
  ROLE_PROFESSIONAL("PROFESSIONAL"),
  ROLE_CUSTOMER("CUSTOMER");

  private String role;

  UserRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }

  @Override
  public String getAuthority() {
    return this.role;
  }

}
