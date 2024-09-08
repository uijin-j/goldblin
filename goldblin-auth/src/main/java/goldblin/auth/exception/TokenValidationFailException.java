package goldblin.auth.exception;

public class TokenValidationFailException extends RuntimeException {

	public TokenValidationFailException(String message) {
		super(message);
	}

}
