package br.com.starter.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundBusinessException extends BaseRestExeception {

	private final static String DETAIL = "Resource with specified ID was not found";

	public NotFoundBusinessException(String message) {
		super(message);
	}

	public NotFoundBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}

	@Override
	public String getDetail() {
		return DETAIL;
	}
}
