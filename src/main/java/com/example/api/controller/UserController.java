package com.example.api.controller;

import com.example.api.exception.IllegalProcessingException;
import com.example.api.model.Reservation;
import com.example.api.model.User;
import com.example.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
@IllegalProcessingException
@RequestMapping("/user")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id){
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @GetMapping
  public ResponseEntity<User> getUserPrincipal(Principal principal){
    return ResponseEntity.ok(userService.getUserByEmail(principal.getName()));
  }

  @GetMapping("/reservation")
  public ResponseEntity<List<Reservation>> getUserPrincipalReservations(Principal principal){
    return ResponseEntity.ok(userService.getUserReservations(principal.getName()));
  }
}
