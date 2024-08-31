package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@RestController
public class DemoApplication {

    private final String htmlFileHeader = "<!DOCTYPE html>\n <html lang=\"de\">\n<head>\n<meta charset=\"utf-8\">\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n<title>";
    private final String htmlFileHeaderEnd = "</title>\n</head>\n<body>";
    private final String htmlFileEnd = "\n</body>\n</html>";

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String shop() {
        String siteTitle = "Shopübersicht";

        RestClient defaultClient = RestClient.create();
        String productsREST = defaultClient.get().uri("http://localhost:8082").retrieve().body(String.class);
        String paymentsREST = defaultClient.get().uri("http://localhost:8081/methods").retrieve().body(String.class);

        return String.format(htmlFileHeader + siteTitle + htmlFileHeaderEnd + productsREST + paymentsREST + htmlFileEnd);
    }
}
