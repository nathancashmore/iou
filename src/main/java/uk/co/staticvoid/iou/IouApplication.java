package uk.co.staticvoid.iou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("uk.co.staticvoid.iou")
public class IouApplication {
    public static void main(String[] args) {
        SpringApplication.run(IouApplication.class, args);
    }
}
