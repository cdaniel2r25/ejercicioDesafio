package com.example.ejercicio.services;

import com.example.ejercicio.models.User;
import com.example.ejercicio.models.dto.UserDto;

public interface UserService {
	User createUser(UserDto userRequest) throws Exception;
}
