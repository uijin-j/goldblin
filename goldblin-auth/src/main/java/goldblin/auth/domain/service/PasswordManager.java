package goldblin.auth.domain.service;

import static goldblin.auth.constants.AuthMessages.*;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordManager {
	private static final int MIN_PASSWORD_LENGTH = 8;
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(
		"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&^])[A-Za-z\\d$@!%*#?&^]+$");

	private final PasswordEncoder passwordEncoder;

	public String encrypt(String rawPassword) {
		validate(rawPassword);
		return passwordEncoder.encode(rawPassword);
	}

	public boolean matches(String rawPassword, String encryptedPassword) {
		return passwordEncoder.matches(rawPassword, encryptedPassword);
	}

	private void validate(String rawPassword) {
		validateLength(rawPassword);
		validateFormat(rawPassword);
	}

	private void validateLength(String rawPassword) {
		// 조건 1. 비밀번호는 8자 이상이어야 합니다.
		if (isShort(rawPassword)) {
			throw new IllegalArgumentException(SHORT_PASSWORD);
		}
	}

	private void validateFormat(String rawPassword) {
		// 조건 2. 비밀번호는 영문, 숫자, 특수문자를 모두 포함해야 합니다.
		if (isNotMatched(rawPassword)) {
			throw new IllegalArgumentException(INVALID_PASSWORD_FORMAT);
		}
	}

	private boolean isShort(String plainPassword) {
		return plainPassword.length() < MIN_PASSWORD_LENGTH;
	}

	private boolean isNotMatched(String plainPassword) {
		return !PASSWORD_PATTERN.matcher(plainPassword).matches();
	}
}
