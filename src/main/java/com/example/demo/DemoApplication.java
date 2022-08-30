package com.example.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Hello WOrld");

		String uri = Properties.dbUrl;
        MongoClient mongoClient = MongoClients.create(uri);

		InsertMany insertMany = new InsertMany(5);

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        for(int i = 0; i < Properties.concurrency; i++) {
            executor.submit(() -> {
                insertMany.insertManyToDb(mongoClient);
                return null;
            });
        }

		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
