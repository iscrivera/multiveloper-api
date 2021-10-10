package mx.multivelopers.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.dekorate.kubernetes.annotation.Port;
import io.dekorate.openshift.annotation.OpenshiftApplication;

@SpringBootApplication
@OpenshiftApplication(name = "multiveloper-api", ports = @Port(name = "web", containerPort = 8080), expose = true)
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}
}
