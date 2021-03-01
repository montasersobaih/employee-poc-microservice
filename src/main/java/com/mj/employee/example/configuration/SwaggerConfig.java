package com.mj.employee.example.configuration;

import com.mj.employee.example.BootstrapApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Primary
    @Bean
    public LinkDiscoverers discoverers() {
        return new LinkDiscoverers(SimplePluginRegistry.of(new CollectionJsonLinkDiscoverer()));
    }

    @Bean
    public Docket apis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BootstrapApplication.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Employee Micro-service APIs Documentation",
                "An employee sample microservice documentation",
                "1.0.0",
                "https://github.com/montasersobaih",
                new Contact("Montaser Sobaih", "https://github.com/montasersobaih", "montaser.jjs@gmail.com"),
                "No licence",
                "https://github.com/montasersobaih",
                Collections.emptyList()
        );
    }
}
