package se.anas.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoOj01Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoOj01Application.class, args);
    }
// this means that when we run the program this method invoke also, which return Faker object( it's bean object also)
    // and that's why it has many methods inside
    @Bean
    public Faker faker(){
        return new Faker();
    }
}
