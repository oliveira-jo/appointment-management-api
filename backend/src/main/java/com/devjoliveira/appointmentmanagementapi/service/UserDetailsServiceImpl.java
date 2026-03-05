package com.devjoliveira.appointmentmanagementapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devjoliveira.appointmentmanagementapi.domain.User;
import com.devjoliveira.appointmentmanagementapi.dto.UserDTO;
import com.devjoliveira.appointmentmanagementapi.security.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserService userService;

  private final Logger logger;

  public UserDetailsServiceImpl(UserService userService) {
    this.userService = userService;
    this.logger = LoggerFactory.getLogger(this.getClass());

  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    logger.info("Getting information for user {}", email);

    UserDTO dto = userService.findByEmail(email);
    User user = new User(dto);

    if (user != null) {

      logger.warn("information for user {} found", email);
      logger.warn("EMAIL {} found", user.getEmail());
      logger.warn("NAME {} found", user.getName());
      logger.warn("ROLE {} found", user.getRole());
      logger.warn("PASSWORD {} found", user.getPassword());

      CustomUserDetails userDetails = new CustomUserDetails(user);

      return userDetails;
    }

    logger.error("Could not find the user {}", email);

    throw new UsernameNotFoundException("Coul not find user");

  }

}