package org.name;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class APIInfoConfiguration {

  @Bean
  public Docket orgApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  private List<VendorExtension> vendorExtensions() {
    List<VendorExtension> vendorExtensions = new ArrayList<>();
    ObjectVendorExtension logo = new ObjectVendorExtension("x-logo");

    StringVendorExtension logoPath =
        new StringVendorExtension(
            "url",
            "https://cdn3.iconfinder.com/data/icons/animals-105/150/icon_animal_leao-512.png");
    logo.addProperty(logoPath);
    vendorExtensions.add(logo);

    return vendorExtensions;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("org api")
        .description(
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum "
                + "has been the industry's standard dummy text ever since the 1500s, when an unknown printer "
                + "took a "
                + "galley of type and scrambled it to make a type specimen book. It has survived not only five "
                + "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "
                + "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum "
                + "passages, and more recently with desktop publishing software like Aldus PageMaker including "
                + "versions of Lorem Ipsum.")
        .termsOfServiceUrl("some url here")
        .contact(new Contact("Your name here", "your home page", "your email"))
        .license("Apache License Version 2.0")
        .licenseUrl("license url here")
        .version("2.0")
        .extensions(vendorExtensions())
        .build();
  }
}
