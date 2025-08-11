package pl.nicolehoppy.cryptoanalyzer;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CryptoAnalyzerApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		SpringApplication.run(CryptoAnalyzerApplication.class, args);
	}

	@Bean
	public CommandLineRunner testApi(CryptoApiClient cryptoApiClient) {
		return args -> {
			List<String> coins = List.of("bitcoin", "ethereum");
			Map<String, Map<String, Double>> prices = cryptoApiClient.getPrices(coins);
			prices.forEach((coin, priceMap) -> {
				System.out.println("Coin: " + coin + ", Price USD: " + priceMap.get("usd"));
			});
		};
	}

}
