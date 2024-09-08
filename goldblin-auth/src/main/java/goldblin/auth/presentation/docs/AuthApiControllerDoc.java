package goldblin.auth.presentation.docs;

import goldblin.auth.dto.request.LoginReq;
import goldblin.auth.dto.request.SignUpReq;
import goldblin.auth.dto.response.LoginRes;
import goldblin.common.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "회원 인증 API")
public interface AuthApiControllerDoc {

	@Operation(summary = "회원가입")
	ApiResponse<Void> signup(SignUpReq request);

	@Operation(summary = "로그인")
	ApiResponse<LoginRes> login(LoginReq request);
}
