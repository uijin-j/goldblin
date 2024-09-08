package goldblin.auth.domain;

import goldblin.auth.domain.enums.AuthType;
import goldblin.auth.domain.service.PasswordManager;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	public static final AuthType DEFAULT_AUTH_TYPE = AuthType.ROLE_USER;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "authority", nullable = false, columnDefinition = "VARCHAR(20)")
	private AuthType authorityType;

	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;

	@Column(name = "password", nullable = false)
	private String encryptedPassword;

	public static Member signup(String username, String password, PasswordManager passwordManager) {
		return Member.builder()
			.authorityType(DEFAULT_AUTH_TYPE)
			.username(username)
			.encryptedPassword(passwordManager.encrypt(password))
			.build();
	}
}
