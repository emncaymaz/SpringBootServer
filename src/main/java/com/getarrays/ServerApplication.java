package com.getarrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.getarrays.enumeration.Status;
import com.getarrays.model.Server;
import com.getarrays.repository.ServerRepository;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		return args -> {
			serverRepository.save(new Server(null,"192.168.1.23","linux","16 gb","personal pc",
					"http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepository.save(new Server(null,"192.168.1.24","windows","16 gb","Dell pc",
					"http://localhost:8080/server/image/server1.png", Status.SERVER_DOWN));
			serverRepository.save(new Server(null,"192.168.1.25","linux","16 gb","Acer pc",
					"http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
		};
	}


}
