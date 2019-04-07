package pl.mdados.ehealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@SpringBootApplication
@EnableReactiveMongoRepositories
public class EhealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhealthApplication.class, args);
    }
}
