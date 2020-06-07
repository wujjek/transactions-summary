package pl.pwu.transactionssummary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.pwu.transactionssummary.adapter.http.HttpConfiguration;

@SpringBootApplication
@Import({
        HttpConfiguration.class,
})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}

