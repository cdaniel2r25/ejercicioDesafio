package com.example.ejercicio.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicio.models.User;
import com.example.ejercicio.models.dto.UserDto;
import com.example.ejercicio.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully"),
		@ApiResponse(responseCode = "400", description = "Bad request"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error") })
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@Operation(summary = "Creacion de un nuevo usuario")
	@PostMapping(value = "/register", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userRequest) throws Exception {

		User user = userService.createUser(userRequest);

		return ResponseEntity.ok(user);
	}

}
