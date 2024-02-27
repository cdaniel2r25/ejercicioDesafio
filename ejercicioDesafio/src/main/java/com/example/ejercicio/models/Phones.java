package com.example.ejercicio.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phones extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, unique = true)
	private String id;

	@Column(name = "number", nullable = false)
	private String number;

	@Column(name = "city_code", nullable = false)
	private String cityCode;

	@Column(name = "country_code", nullable = false)
	private String countryCode;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "phones")
	@JsonIgnore
	private Set<User> users = new HashSet<>();

	

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phones other = (Phones) obj;
		return Objects.equals(cityCode, other.cityCode) && Objects.equals(countryCode, other.countryCode)
				&& Objects.equals(id, other.id) && Objects.equals(number, other.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cityCode, countryCode, id, number);
	}

	public Phones(String number, String cityCode, String countryCode) {
		super();
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "Phones [id=" + id + ", number=" + number + ", cityCode=" + cityCode + ", countryCode=" + countryCode
				+ ", users=" + users + "]";
	}

}
