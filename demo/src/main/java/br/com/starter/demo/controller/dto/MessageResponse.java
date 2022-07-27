package br.com.starter.demo.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageResponse {

    private final int code;

    private final String message;

}
