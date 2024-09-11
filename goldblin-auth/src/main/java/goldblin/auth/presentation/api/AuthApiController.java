package goldblin.auth.presentation.api;

import static goldblin.auth.constants.AuthMessages.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import goldblin.auth.business.AuthService;
import goldblin.auth.dto.request.LoginReq;
import goldblin.auth.dto.request.SignUpReq;
import goldblin.auth.dto.response.LoginRes;
import goldblin.auth.presentation.docs.AuthApiControllerDoc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController implements AuthApiControllerDoc {
	private final AuthService authService;

	@PostMapping("/signup")
	@ResponseStatus(CREATED)
	public ApiResponse<Void> signup(@RequestBody @Valid SignUpReq request) {
		authService.signup(request);
		return ApiResponse.of(CREATED, SIGNUP_SUCCESS, null);
	}

	@PostMapping("/lonin")
	@ResponseStatus(OK)
	public ApiResponse<LoginRes> login(@RequestBody @Valid LoginReq request) {
		LoginRes response = authService.login(request);
		return ApiResponse.ok(response);
	}

}
