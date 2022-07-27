package br.com.starter.demo.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL) // To avoid a compiletime warning
public abstract class BaseRestExeception extends RuntimeException {

	public BaseRestExeception(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BaseRestExeception(final String message) {
		super(message);
	}

	public abstract HttpStatus getHttpStatus();

	public abstract String getDetail();
}
