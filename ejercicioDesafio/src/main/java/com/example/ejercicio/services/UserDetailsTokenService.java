package com.example.ejercicio.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsTokenService {
	 UserDetails loadUserByToken(String token) throws UsernameNotFoundException;
}
