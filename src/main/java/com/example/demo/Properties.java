package com.example.demo;

public class Properties {

    int concurrency;
    int batchSize;
    String dbUrl;
    String databaseName;
    String collectionName;
    long durationInSec;
    
    public long getDurationInSec() {
        return durationInSec;
    }
    public void setDurationInSec(long durationInSec) {
        this.durationInSec = durationInSec;
    }

    public int getConcurrency() {
        return concurrency;
    }
    public void setConcurrency(int concurrency) {
        this.concurrency = concurrency;
    }
    public int getBatchSize() {
        return batchSize;
    }
    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
    public String getDbUrl() {
        return dbUrl;
    }
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    public String getCollectionName() {
        return collectionName;
    }
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void print() {
        System.out.println("\nConcurrency: " + this.concurrency 
            + "\nBatch Size: " + this.batchSize 
            + "\nDB Url: " + this.dbUrl
            + "\nDatabase Name: " + this.databaseName
            + "\nCollection Name: " + this.collectionName
            + "\nDuration: " + this.durationInSec + " seconds");
    }
}
