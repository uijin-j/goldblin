package goldblin.auth.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import goldblin.auth.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	boolean existsByUsername(String username);

	Optional<Member> findByUsername(String username);
}
