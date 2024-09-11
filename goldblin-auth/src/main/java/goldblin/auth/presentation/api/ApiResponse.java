package goldblin.auth.presentation.api;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "API 응답")
public class ApiResponse<T> {
	@Schema(description = "상태 코드", example = "200")
	private int code;

	@Schema(description = "상태", example = "OK")
	private HttpStatus status;

	@Schema(description = "메시지", example = "요청하신 작업을 완료했습니다.")
	private String message;

	@Schema(description = "응답 데이터")
	private T data;

	private ApiResponse(HttpStatus status, String message, T data) {
		this.code = status.value();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> of(HttpStatus httpStatus, String message, T data) {
		return new ApiResponse<>(httpStatus, message, data);
	}

	public static <T> ApiResponse<T> of(HttpStatus httpStatus, T data) {
		return of(httpStatus, httpStatus.name(), data);
	}

	public static <T> ApiResponse<T> ok(T data) {
		return of(OK, data);
	}

	public static <T> ApiResponse<T> error(HttpStatus httpStatus, String message) {
		return new ApiResponse<>(httpStatus, message, null);
	}
}
