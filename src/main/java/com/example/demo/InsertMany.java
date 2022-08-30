package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.result.InsertManyResult;

public class InsertMany {

    MongoClient mongoClient;

    public InsertMany(int concurrency) {
        
    }

    public static void main(String[] args) {
        // TODO: create mongoDB connection threadpool
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        for(int i = 0; i < 5; i++) {
            executor.submit(() -> {
                // insertManyToDb();
                return null;
            });
        }

        // ExecutorService pool = Executors.newFixedThreadPool(4);
    }

    public void insertManyToDb(MongoClient mongoClient) {
        System.out.println("Inserting data to DB Started...");

        try  {
            MongoCollection<Document> collection = mongoClient.getDatabase(Properties.databaseName).getCollection(Properties.collectionName);
            List<Document> movieList = new ArrayList<Document>();

            for(int i = 0; i < Properties.batchSize; i++) {
                movieList.add(Document.parse(Data.data));
            }

            try {
                collection.insertMany(movieList, new InsertManyOptions().ordered(false));
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        System.out.println("Inserting data to DB Completed");
    }
    
}
