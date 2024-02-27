package com.example.ejercicio.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, unique = true)
	private String id;

	@JsonIgnore
	@Column(name = "name", nullable = false)
	private String name;

	@JsonIgnore
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "token", nullable = false)
	private String token;
	@Column(name = "type_token", nullable = false)
	private String typeToken;
	
	
	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "active", nullable = false)
	private Boolean isactive;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;


	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_phones", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "phone_id") })
	private List<Phones> phones = new ArrayList<>();

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", token=" + token + ", typeToken="
				+ typeToken + ", password=" + password + ", isactive=" + isactive + ", lastLogin=" + lastLogin
				+ ", phones=" + phones + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(isactive, other.isactive) && Objects.equals(lastLogin, other.lastLogin)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(phones, other.phones) && Objects.equals(token, other.token)
				&& Objects.equals(typeToken, other.typeToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, isactive, lastLogin, name, password, phones, token, typeToken);
	}

}
