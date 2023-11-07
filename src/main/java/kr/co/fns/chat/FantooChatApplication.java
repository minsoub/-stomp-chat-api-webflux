package kr.co.fns.chat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication(scanBasePackages = "kr.co.fns.chat", exclude = {
		MongoAutoConfiguration.class,
		MongoReactiveAutoConfiguration.class,
		MongoDataAutoConfiguration.class,
		EmbeddedMongoAutoConfiguration.class
})
@EnableWebFlux
@ConfigurationPropertiesScan("kr.co.fns.chat")
@EnableReactiveMongoRepositories("kr.co.fns.chat.api.chat.repository")
@EnableReactiveMongoAuditing
@OpenAPIDefinition(info = @Info(title = "Chat Live Management API", version = "1.0", description = "Chat Live Management APIs v1.0"))
public class FantooChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantooChatApplication.class, args);
	}

}
