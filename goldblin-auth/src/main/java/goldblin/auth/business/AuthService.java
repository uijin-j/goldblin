package goldblin.auth.business;

import static goldblin.auth.constants.AuthMessages.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goldblin.auth.domain.Member;
import goldblin.auth.domain.service.PasswordManager;
import goldblin.auth.dto.request.SignUpReq;
import goldblin.auth.infrastructure.persistence.MemberRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
	private final MemberRepository memberRepository;
	private final PasswordManager passwordManager;

	@Transactional
	public void signup(SignUpReq request) {
		validateUsernameUnique(request.username());

		Member member = Member.signup(request.username(), request.password(), passwordManager);

		memberRepository.save(member);
	}

	private void validateUsernameUnique(String username) {
		if (memberRepository.existsByUsername(username)) {
			throw new EntityExistsException(DUPLICATE_USERNAME);
		}
	}
}
