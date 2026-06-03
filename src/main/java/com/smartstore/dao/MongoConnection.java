package com.smartstore.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static final String URI = "mongodb://localhost:27017";
    private static final MongoClient mongoClient = MongoClients.create(URI);

    public static MongoDatabase getDatabase() {
        return mongoClient.getDatabase("smart_store_db");
    }
}