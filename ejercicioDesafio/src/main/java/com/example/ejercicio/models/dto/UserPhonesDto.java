package com.example.ejercicio.models.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPhonesDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String number;

	@NotEmpty
	private String citycode;

	@NotEmpty
	private String contrycode;

	@Override
	public String toString() {
		return "UserPhonesDto [number=" + number + ", citycode=" + citycode + ", contrycode=" + contrycode + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPhonesDto other = (UserPhonesDto) obj;
		return Objects.equals(citycode, other.citycode) && Objects.equals(contrycode, other.contrycode)
				&& Objects.equals(number, other.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(citycode, contrycode, number);
	}

}
