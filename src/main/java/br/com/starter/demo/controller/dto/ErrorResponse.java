package br.com.starter.demo.controller.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Class that represents RFC7807 and RFC7231 error description
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timestamp;

	@Schema(description = " A human-readable description of the specific error.",
			required = true, example = "Need password")
	String detail;

	@Schema(description = " A URI reference [RFC3986] that identifies the " +
			" problem type. This specification encourages that, when " +
			" dereferenced, it provide human-readable documentation for the " +
			" problem type (e.g., using HTML [W3C.REC-html5-20141028]). When " +
			" this member is not present, its value is assumed to be " +
			" \"about:blank\". ",
			required = true, example = "http://dev.dasa.com.br/api/validation_error")
	String type;

	@Schema(description = " A short, human-readable summary of the problem " +
			" type. It SHOULD NOT change from occurrence to occurrence of the " +
			" problem, except for purposes of localization (e.g., using " +
			" proactive content negotiation; see [RFC7231], Section 3.4). ",
			required = true, example = "User must be authenticated")
	String title;


	@Schema(description = "The HTTP status code ([RFC7231], Section 6) " +
			"      generated by the origin server for this occurrence of the problem.",
			 example = "500")
	private Integer status;

	@Schema(description = " A URI reference that identifies the specific " +
			" occurrence of the problem. It may or may not yield further " +
			" information if dereferenced.", example = "http://dev.dasa.com.br/api/err-12435")
	private String instance ;


}
