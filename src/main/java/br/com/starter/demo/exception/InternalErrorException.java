package br.com.starter.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends BaseRestExeception {

	private final static String DETAIL = "An internal server error occurred. Please submit a bug report";

	public InternalErrorException(String message) {
		super(message);
	}

	public InternalErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	@Override
	public String getDetail() {
		return DETAIL;
	}
}
