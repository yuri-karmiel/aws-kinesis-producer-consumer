package telran.aws;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import telran.aws.sensors.dto.SensorData;

@SpringBootApplication
public class SensorDataImitator {
	private static final long TIMEOUT = 3000;
	@Value("${app.imitator.sensors.amount:100}")
	int nSensors;
	@Value("${app.imitator.sensor.value.min:10}")
	double minValue;
	@Value("${app.imitator.sensor.value.max:100}")
	double maxValue;
public static void main(String[] args) throws InterruptedException {
	ConfigurableApplicationContext ctx = SpringApplication.run(SensorDataImitator.class, 
			args);
	Thread.sleep(TIMEOUT);
	ctx.close();
}
long getRandomLongNumber(int min, int max) {
	return ThreadLocalRandom.current().nextLong(min, max + 1);
}
double getRandomDoubleNumber(double min, double max) {
	return ThreadLocalRandom.current().nextDouble(min, max + 1);
}
@Bean
Supplier<SensorData> sensorDataSupplier() {
	return this::getRandomSensorData;
}
SensorData getRandomSensorData() {
	return new SensorData(getRandomLongNumber(1, nSensors),
			getRandomDoubleNumber(minValue, maxValue));
}
}
