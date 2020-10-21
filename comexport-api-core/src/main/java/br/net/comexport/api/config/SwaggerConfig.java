package br.net.comexport.api.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket orderApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Order")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.net.comexport.api"))
                .paths(regex("/order.*"))
                .build()
                .apiInfo(metaInfo());
	}
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Product")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.net.comexport.api"))
                .paths(regex("/product.*"))
                .build()
                .apiInfo(metaInfo());
	}

    private ApiInfo metaInfo() {

        @SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo(
                "Core / Pedidos API REST",
                "API REST para consulta de Pedidos e Core",
                "1.0",
                "Terms of Service",
                new Contact("Diego Almeida", "https://github.com/diegocsalmeida",
                        "diego.almeida@linuxmail.org"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}