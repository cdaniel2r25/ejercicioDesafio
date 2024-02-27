package com.example.ejercicio.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.ejercicio.models.User;
import com.example.ejercicio.models.dto.UserDto;
import com.example.ejercicio.models.dto.UserPhonesDto;
import com.example.ejercicio.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

class UserControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	private UserController lookupController;

	@Mock
	UserService userService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(lookupController).build();
	}

	@Test
	@Order(1)
	void whenCreateUser_thenUserIsOK() throws Exception {
		UserDto newUser = new UserDto();
		newUser.setEmail("juan@rodriguez.org");
		newUser.setName("Juan Rodriguez");
		newUser.setPassword("Hunter01");

		List<UserPhonesDto> phones = List.of(new UserPhonesDto("1234567", "1", "57"));

		newUser.setPhones(phones);

		User result = new User();
		result.setName("Prueba");
		UUID id = UUID.randomUUID();
		result.setId(id.toString());

		// se indica al contexto que al invocar createUser Devolvera un usuario con Id
		Mockito.when(userService.createUser(Mockito.any(UserDto.class))).thenReturn(result);

		this.mockMvc
				.perform(post("/api/user/register").content(asJsonString(newUser))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(notNullValue()))).andReturn();
	}

	@Test
	@Order(2)
	void whenCreateUser_thenUserValidErrorFormatEmail() throws Exception {
		UserDto newUser = new UserDto();
		newUser.setEmail("juan");
		newUser.setName("Juan Rodriguez");
		newUser.setPassword("Hunter01");

		List<UserPhonesDto> phones = List.of(new UserPhonesDto("1234567", "1", "57"));

		newUser.setPhones(phones);

		User result = new User();
		result.setName("Prueba");
		UUID id = UUID.randomUUID();
		result.setId(id.toString());

		// se indica al contexto que al invocar createUser Devolvera un usuario con Id
		Mockito.when(userService.createUser(Mockito.any(UserDto.class))).thenReturn(result);

		this.mockMvc
				.perform(post("/api/user/register").content(asJsonString(newUser))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();
	}

	@Test
	@Order(2)
	void whenCreateUser_thenUserValidFormatPassword() throws Exception {
		UserDto newUser = new UserDto();
		newUser.setEmail("juan@rodriguez.org");
		newUser.setName("Juan Rodriguez");
		newUser.setPassword("hunter01");

		List<UserPhonesDto> phones = List.of(new UserPhonesDto("1234567", "1", "57"));

		newUser.setPhones(phones);

		User result = new User();
		result.setName("Prueba");
		UUID id = UUID.randomUUID();
		result.setId(id.toString());

		// se indica al contexto que al invocar createUser Devolvera un usuario con Id
		Mockito.when(userService.createUser(Mockito.any(UserDto.class))).thenReturn(result);

		this.mockMvc
				.perform(post("/api/user/register").content(asJsonString(newUser))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();
	}

	private static String asJsonString(final Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
