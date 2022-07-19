package jy.study.place.infra.searchPlaces.provider.kakao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "infra.search.provider.kakao")
public class KakaoConfig {

    private String url;

    private Map<String, String> headers;
}
