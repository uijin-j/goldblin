package goldblin.auth.presentation.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import goldblin.common.api.ApiResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 4XX 에러 처리
	 */
	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.debug(e.getMessage(), e.fillInStackTrace());

		return ApiResponse.error(BAD_REQUEST, extractErrorMessages(e));
	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ApiResponse<Void> handleIllegalArgumentException(IllegalArgumentException e) {
		log.debug(e.getMessage(), e.fillInStackTrace());

		return ApiResponse.error(BAD_REQUEST, e.getMessage());
	}

	@ResponseStatus(NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public ApiResponse<Void> handleEntityNotFoundException(EntityNotFoundException e) {
		log.debug(e.getMessage(), e.fillInStackTrace());

		return ApiResponse.error(NOT_FOUND, e.getMessage());
	}

	@ResponseStatus(CONFLICT)
	@ExceptionHandler(EntityExistsException.class)
	public ApiResponse<Void> handleEntityExistsException(EntityExistsException e) {
		log.debug(e.getMessage(), e.fillInStackTrace());

		return ApiResponse.error(CONFLICT, e.getMessage());
	}

	/**
	 * 5XX 에러 처리
	 */
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ApiResponse<Void> handleException(Exception e) {
		log.error(e.getMessage(), e.fillInStackTrace());

		return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	private String extractErrorMessages(MethodArgumentNotValidException e) {
		return e.getBindingResult()
			.getAllErrors()
			.stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.toList()
			.toString();
	}
}
