package goldblin.auth.presentation.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goldblin.auth.business.AuthService;
import goldblin.auth.dto.request.SignUpReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestBody @Valid SignUpReq request) {
		Long resourceId = authService.signup(request);
		URI location = URI.create("/api/member/" + resourceId);

		return ResponseEntity.created(location)
			.build();
	}

}
