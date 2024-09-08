package goldblin.auth.dto.request;

import static goldblin.auth.constants.AuthMessages.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpReq(
	@NotBlank(message = BLANK_USERNAME)
	@Size(max = 50, message = TOO_LONG_USERNAME)
	String username,
	@NotBlank(message = BLANK_PASSWORD)
	String password
) {
}
