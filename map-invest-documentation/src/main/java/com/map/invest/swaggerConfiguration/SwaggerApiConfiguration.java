package com.map.invest.swaggerConfiguration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerApiConfiguration {

//    @Value("${swagger.openapi3.yaml.path}")
//    private String openApi3YamlPath;
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        ParseOptions options = new ParseOptions();
//        options.setResolve(true);
//        OpenAPI openAPI = new OpenAPIV3Parser().readLocation(openApi3YamlPath, null, options).getOpenAPI();
//
//        openAPI.getInfo()
//                .version("1.0.0")
//                .title("Api MapInvest")
//                .description("Abaixo a documentação dos recursos para a Api de administração de ativos financeiros");
//
//        return openAPI;
//    }
}
