package com.kodo.formbuilder;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Kodo Form Builder Application",
				description = "API documentation for the Kodo Form Builder Application",
				version = "v1",
				contact = @Contact(
						name = "Harshad Panmand",
						email = "panmandharshadcareer@gmail.com",
						url = "https://www.kodo.com/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.kodo.com/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Kodo Form Builder API Documentation",
				url = "http://localhost:8080/swagger-ui/index.html"
				// below value can be replaced
//				url = "https://www.example.com/swagger-ui.html"
		)
)
public class KodoFormBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(KodoFormBuilderApplication.class, args);
	}

}
