package br.com.starter.demo.config;

import java.util.HashMap;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.starter.demo.controller.dto.ErrorResponse;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class OpenApiConfig {

	@Value("${app.title:null}")
	public String appTitle;

	@Value("${app.description:null}")
	public String appDescription;

	@Value("${app.title:null}")
	public String appVersion;
 
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title(appTitle).description(
                        appDescription).version(appVersion));
    }

	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		// add default responses
		// Ref to Error-Object (added in step above)
		Schema errorResponseSchema = new Schema();
		errorResponseSchema.setName("ErrorResponse");
		errorResponseSchema.set$ref("#/components/schemas/ErrorResponse");


		return openApi -> {
			if(openApi.getComponents().getSchemas() == null){
				openApi.getComponents().setSchemas(new HashMap<>());
			}
			// add Error and ErrorItem to schema
			openApi.getComponents().getSchemas().putAll(ModelConverters.getInstance().read(Error.class));
			openApi.getComponents().getSchemas().putAll(ModelConverters.getInstance().read(ErrorResponse.class));

			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				if(operation == null){
					operation = new Operation();
				}
				ApiResponses apiResponses = operation.getResponses();
				apiResponses.addApiResponse("400", createApiResponse("BadRequest", errorResponseSchema));
				apiResponses.addApiResponse("403", createApiResponse("Forbidden", null));
				apiResponses.addApiResponse("500", createApiResponse("Server Error", null));
			}));
		};
	}

	private ApiResponse createApiResponse(String message, Schema schema) {

		ApiResponse apiResponse = new ApiResponse().description(message);
		if(schema != null) {

			MediaType mediaType = new MediaType();
			mediaType.schema(schema);
			apiResponse.content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, mediaType));
		}
		return apiResponse;
	}

}