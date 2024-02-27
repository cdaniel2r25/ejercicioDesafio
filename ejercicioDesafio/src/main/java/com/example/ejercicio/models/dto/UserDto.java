package com.example.ejercicio.models.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.example.ejercicio.config.annotations.PasswordValid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	@Email(message = "Email Formato Incorrecto. Ej: aaaaaaa@dominio.cl")
	@NotEmpty(message = "Email no puede estar vacio")
	private String email;

	@NotEmpty(message = "Name no puede estar vacio")
	private String name;

	@PasswordValid
	private String password;

	private List<UserPhonesDto> phones;

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", phones="
				+ phones + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(phones, other.phones);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, password, phones);
	}

}
