package goldblin.auth.dto.request;

import static goldblin.auth.constants.AuthMessages.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "회원가입 요청 정보")
public record SignUpReq(
	@NotBlank(message = BLANK_USERNAME)
	@Size(max = 50, message = TOO_LONG_USERNAME)
	@Schema(description = "아이디", example = "goldblin")
	String username,
	@NotBlank(message = BLANK_PASSWORD)
	@Schema(description = "비밀번호 (숫자, 영문자, 특수문자를 포함한 8자리 이상의 문자열)", example = "password12!")
	String password
) {
}
