package ru.kviak.geocodingapi;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;



@SpringBootApplication
@EnableJpaAuditing
@EnableKafka
public class GeocodingApiApplication {
	@Value("${tomtom.api.url}")
	private static final String BASE_URL = "https://api.tomtom.com/search/2/";
	public static final int TIMEOUT = 1000;
	/// Kafka test

	@KafkaListener(topics="msg")
	public void msgListener(String msg){
		System.out.println(msg);
	}


	/// Kafka test
	public static void main(String[] args) {
		SpringApplication.run(GeocodingApiApplication.class, args);
	}
	@Bean
	public WebClient webClientWithTimeout() {
		final var tcpClient = TcpClient
				.create()
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
				.doOnConnected(connection -> {
					connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
					connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
				});

		return WebClient.builder()
				.baseUrl(BASE_URL)
				.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
				.build();
	}
}
