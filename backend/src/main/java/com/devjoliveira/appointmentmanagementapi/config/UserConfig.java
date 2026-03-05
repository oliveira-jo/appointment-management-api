package com.devjoliveira.appointmentmanagementapi.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.devjoliveira.appointmentmanagementapi.domain.User;
import com.devjoliveira.appointmentmanagementapi.enums.UserRole;
import com.devjoliveira.appointmentmanagementapi.repository.UserRepository;

@Component
public class UserConfig {

  UserRepository repository;

  UserConfig(UserRepository repository) {
    this.repository = repository;
    this.veryfyAdminBd();
  }

  public void veryfyAdminBd() {

    // CONFIG ABOUT ADMINISTRATOR
    var userAdmin = this.repository.findByEmail("admin@admin.com");
    if (!userAdmin.isPresent()) {

      User entity = new User();
      entity.setPassword(new BCryptPasswordEncoder().encode("password"));

      entity.setRole(UserRole.ROLE_ADMIN);
      entity.setEmail("admin@admin.com");
      entity.setName("administrador");

      this.repository.save(entity);
      userAdmin = this.repository.findByEmail("admin@admin.com");

      System.out.println("------------------------- ADMINISTRATOR CREATE AND SAVE IN BD ------------------------- "
          + userAdmin.isPresent());
    } else {
      System.out.println("------------------------- ADMINISTRATOR ALREADY EXIST IN BD -------------------------");

    }

  }

}