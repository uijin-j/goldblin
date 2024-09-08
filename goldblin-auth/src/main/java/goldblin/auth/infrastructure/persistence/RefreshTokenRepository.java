package goldblin.auth.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import goldblin.auth.domain.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
