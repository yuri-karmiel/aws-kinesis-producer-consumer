package telran.aws;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import telran.aws.sensors.dto.SensorData;
@Slf4j
@SpringBootApplication
public class SensorsConsumerAppl {

	public static void main(String[] args) {
		SpringApplication.run(SensorsConsumerAppl.class, args);

	}
	@Bean
	Consumer<SensorData> sensorDataConsumer() {
		return this::sensorDataProcessing;
	}
	void sensorDataProcessing(SensorData sensor) {
		log.debug("received sensor data: {}", sensor);
	}

}
