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

    public void insertManyToDb(Properties properties, MongoClient mongoClient) {
        System.out.println("Inserting data to DB Started...");

        try  {
            MongoCollection<Document> collection = mongoClient.getDatabase(properties.databaseName).getCollection(properties.collectionName);
            List<Document> movieList = new ArrayList<Document>();

            for(int i = 0; i < properties.batchSize; i++) {
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
