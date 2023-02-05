package api.covid19.stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Covid19ApiStatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Covid19ApiStatsApplication.class, args);
    }
}
