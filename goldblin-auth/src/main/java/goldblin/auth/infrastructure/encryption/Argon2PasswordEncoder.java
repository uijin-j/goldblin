package goldblin.auth.infrastructure.encryption;

import org.springframework.stereotype.Component;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import goldblin.auth.domain.service.PasswordEncoder;

@Component
public class Argon2PasswordEncoder implements PasswordEncoder {
	private static final int DEFAULT_ITERATIONS = 3; // 해싱 횟수 (브루트 포스 공격 방지)
	private static final int DEFAULT_MEMORY = 65536; // 기본 메모리 사용량 (64MB)
	private static final int DEFAULT_PARALLELISM = 1; // 기본 병렬 처리 정도

	private final Argon2 argon2;

	public Argon2PasswordEncoder() {
		this.argon2 = Argon2Factory.create();
	}

	@Override
	public String encode(String rawPassword) {
		return argon2.hash(DEFAULT_ITERATIONS, DEFAULT_MEMORY, DEFAULT_PARALLELISM, rawPassword.toCharArray());
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		return argon2.verify(encodedPassword, rawPassword.toCharArray());
	}
}
