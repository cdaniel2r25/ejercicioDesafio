package com.example.ejercicio.config.annotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordValid, String> {

	@Override
	public void initialize(PasswordValid arg0) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {

		// Dígitos, minúsculas y mayúsculas a partir de 3 caracteres
		Pattern p = Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{3,16}$");

		if (password == null) {

			return false;
		}

		Matcher m = p.matcher(password);
		return m.matches();

	}

}
