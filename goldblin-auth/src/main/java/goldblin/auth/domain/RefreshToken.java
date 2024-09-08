package goldblin.auth.domain;

import java.time.LocalDateTime;

import goldblin.auth.domain.service.JwtManager;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refresh_token")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Column(name = "value", nullable = false)
	private String token;

	@Column(name = "expired_at", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime expiredAt;

	public static RefreshToken of(Member member, String token, JwtManager jwtManager) {
		return RefreshToken.builder()
			.member(member)
			.token(token)
			.expiredAt(jwtManager.getExpiredAt(token))
			.build();
	}
}
