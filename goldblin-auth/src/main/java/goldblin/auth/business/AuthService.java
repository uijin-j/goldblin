package goldblin.auth.business;

import static goldblin.auth.constants.AuthMessages.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goldblin.auth.domain.Member;
import goldblin.auth.domain.RefreshToken;
import goldblin.auth.domain.service.JwtManager;
import goldblin.auth.domain.service.PasswordManager;
import goldblin.auth.dto.request.LoginReq;
import goldblin.auth.dto.request.SignUpReq;
import goldblin.auth.dto.response.LoginRes;
import goldblin.auth.infrastructure.persistence.MemberRepository;
import goldblin.auth.infrastructure.persistence.RefreshTokenRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
	private final MemberRepository memberRepository;
	private final RefreshTokenRepository refreshTokenRepository;
	private final PasswordManager passwordManager;
	private final JwtManager jwtManager;

	@Transactional
	public void signup(SignUpReq request) {
		validateUsernameUnique(request.username());

		Member member = Member.signup(request.username(), request.password(), passwordManager);

		memberRepository.save(member);
	}

	@Transactional
	public LoginRes login(LoginReq request) {
		Member member = memberRepository.findByUsername(request.username())
			.orElseThrow(() -> new EntityNotFoundException(INVALID_USERNAME));

		validateCredential(member, request.password());

		String accessToken = jwtManager.generateAccessToken(member);
		String refreshToken = jwtManager.generateRefreshToken(member);

		saveRefreshToken(member, refreshToken);

		return LoginRes.of(accessToken, refreshToken);
	}

	private void saveRefreshToken(Member member, String refreshToken) {
		RefreshToken token = RefreshToken.of(member, refreshToken, jwtManager);
		refreshTokenRepository.save(token);
	}

	private void validateUsernameUnique(String username) {
		if (memberRepository.existsByUsername(username)) {
			throw new EntityExistsException(DUPLICATE_USERNAME);
		}
	}

	private void validateCredential(Member member, String password) {
		if (!passwordManager.matches(password, member.getEncryptedPassword())) {
			throw new IllegalArgumentException(INVALID_PASSWORD);
		}
	}
}
