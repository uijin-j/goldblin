package goldblin.auth.dto.request;

import static goldblin.auth.constants.AuthMessages.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "로그인 요청 정보")
public record LoginReq(
	@NotBlank(message = BLANK_USERNAME)
	@Schema(description = "아이디", example = "goldblin")
	String username,
	@NotBlank(message = BLANK_PASSWORD)
	@Schema(description = "비밀번호", example = "password12!")
	String password
) {
}
