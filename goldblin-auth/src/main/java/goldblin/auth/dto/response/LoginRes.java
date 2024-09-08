package goldblin.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "로그인 응답 정보")
public record LoginRes(
	@Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJnb2xkYmxpbiIsImlhdCI6MTcyNTgxMzEzMCwiZXhwIjoxNzI1ODE2NzMwLCJtZW1iZXJJZCI6MSwicm9sZSI6IlJPTEVfVVNFUiJ9.FHJZGZfvGWGILKl0IiI3kuD_djyxRGKgG5c38SkzuNiocty8H0jZ4Noww_Eji0HmCkVtq0R7UG8mrzHYCKaUOQ")
	String accessToken,
	@Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJnb2xkYmxpbiIsImlhdCI6MTcyNTgxMzEzMCwiZXhwIjoxNzI1ODE2NzMwLCJtZW1iZXJJZCI6MSwicm9sZSI6IlJPTEVfVVNFUiJ9.FHJZGZfvGWGILKl0IiI3kuD_djyxRGKgG5c38SkzuNiocty8H0jZ4Noww_Eji0HmCkVtq0R7UG8mrzHYCKaUOQ")
	String refreshToken
) {
	public static LoginRes of(String accessToken, String refreshToken) {
		return new LoginRes(accessToken, refreshToken);
	}
}
