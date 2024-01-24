package com.bezkoder.spring.datajpa;

import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SpringBootDataJpaApplication {

	public static void main(String[] args) {
		
		// Carga el archivo .env solo si no estamos en AWS
        if (System.getenv("AWS_EXECUTION_ENV") == null) {
			// Construye la ruta al archivo .env que está un nivel por encima
			String parentPath = Paths.get(System.getProperty("user.dir")).getParent().toString();
			String dotenvPath = Paths.get(parentPath, ".env").toString();
	
			
            // Carga el archivo .env utilizando la ruta construida
			Dotenv dotenv = Dotenv.configure()
					.directory(dotenvPath)
					.ignoreIfMissing()
					.load();

			dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
			});

        }
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

}
