package goldblin.auth.domain.service;

public interface PasswordEncoder {
	String encode(String rawPassword);

	boolean matches(String rawPassword, String encodedPassword);
}
