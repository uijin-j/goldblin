package goldblin.auth.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthMessages {
	/**
	 * 200 번대 성공 메시지
	 */
	public static final String SIGNUP_SUCCESS = "회원가입이 완료되었습니다.";

	/**
	 * 400 번대 에러 메시지
	 */
	public static final String SHORT_PASSWORD = "비밀번호는 8자 이상이어야 합니다.";
	public static final String INVALID_PASSWORD_FORMAT = "비밀번호는 영문, 숫자, 특수문자를 모두 포함해야 합니다.";
	public static final String BLANK_USERNAME = "아이디는 필수값입니다.";
	public static final String TOO_LONG_USERNAME = "아이디는 50자 이하여야 합니다.";
	public static final String BLANK_PASSWORD = "비밀번호는 필수값입니다.";
	public static final String DUPLICATE_USERNAME = "이미 사용중인 아이디입니다.";
	public static final String INVALID_USERNAME = "해당 아이디를 가진 사용자가 존재하지 않습니다.";
	public static final String INVALID_PASSWORD = "비밀번호가 올바르지 않습니다.";
	public static final String EXPIRED_TOKEN = "만료된 토큰입니다.";
	public static final String INVALID_TOKEN = "유효하지 않은 토큰입니다.";
}
