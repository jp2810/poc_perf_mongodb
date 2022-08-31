package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);

		System.out.printf("Running with args %s", Arrays.toString(args));

		String fileName = args.length > 0 ? args[0] : "properties.json";
        Properties properties = getConfig(fileName);
		System.out.println("Application running with following configuration - ");
		properties.print();

        MongoClient mongoClient = MongoClients.create(properties.dbUrl);

		InsertMany insertMany = new InsertMany();

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(properties.concurrency);

        for(int i = 0; i < properties.concurrency; i++) {
            executor.submit(() -> {
                insertMany.insertManyToDb(properties, mongoClient);
                return null;
            });
        }
	}

	@SneakyThrows
	private static Properties getConfig(String fileName) {
		File configFile = new File(fileName);

        ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(configFile, Properties.class);
    }

}
