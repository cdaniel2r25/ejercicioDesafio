package com.example.ejercicio.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ejercicio.config.jwt.JwtUtil;
import com.example.ejercicio.exceptions.BadRequestException;
import com.example.ejercicio.models.Phones;
import com.example.ejercicio.models.User;
import com.example.ejercicio.models.dto.UserDto;
import com.example.ejercicio.models.dto.UserPhonesDto;
import com.example.ejercicio.repository.UserRepository;
import com.example.ejercicio.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PasswordEncoder encoder;

	final UserRepository userRepository;
	final JwtUtil jwtUtils;

	UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtils) {
		this.userRepository = userRepository;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public User createUser(UserDto userRequest) throws Exception {
		User user = new User();
		try {

			if (Boolean.TRUE.equals(userRepository.existsByEmail(userRequest.getEmail()))) {
				throw new BadRequestException("El correo ya esta registrado");
			}

			user.setIsactive(Boolean.TRUE);
			user.setEmail(userRequest.getEmail());
			user.setLastLogin(LocalDateTime.now());
			user.setName(userRequest.getName());
			user.setPassword(encoder.encode(userRequest.getPassword()));
			user.setToken("");
			user.setTypeToken("Bearer");

			List<Phones> phones = new ArrayList<>();
			for (UserPhonesDto phone : userRequest.getPhones()) {
				phones.add(new Phones(phone.getNumber(), phone.getCitycode(), phone.getContrycode()));
			}
			user.setPhones(phones);
			user = userRepository.save(user);
			Map<String, Object> claims = new HashMap<>();
			String jwt = jwtUtils.generateToken(claims, user.getEmail());

			user.setToken(jwt);
			user = userRepository.save(user);
		} catch (BadRequestException e) {
			e.printStackTrace();
			throw new BadRequestException(e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return user;
	}

}
