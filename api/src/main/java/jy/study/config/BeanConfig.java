package jy.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.concurrent.Executors;

@Configuration
public class BeanConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(20))
                .executor(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3))
                .build();
    }

}
