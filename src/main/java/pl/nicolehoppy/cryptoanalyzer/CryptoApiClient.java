package pl.nicolehoppy.cryptoanalyzer;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class CryptoApiClient {

    private final WebClient webClient;

    public CryptoApiClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.coingecko.com/api/v3").build();
    }

    public Map<String, Map<String, Double>> getPrices(List<String> coins) {
        String ids = String.join(",", coins);

        return webClient.get().uri("/simple/price?ids={ids}&vs_currencies=usd", ids)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Map<String, Double>>>() {})
                .block();
    }
}
