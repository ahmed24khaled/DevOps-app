package tn.devops.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .apiInfo(
        		  new ApiInfo(
        		  "Bookd app RESTful Web Service documentation", 
        		  "This pages documents Books app RESTful Web Service endpoints", "1.0","",
        		  new Contact(
        			         "Ahmed KHALED",
        			         "https://github.com/ahmed24khaled", 
        			         "ahmed24khaled@gmail.com"
        			 ), 
        		  "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0")
        		  )
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("My API").version("1.0.0").build();
    }
}