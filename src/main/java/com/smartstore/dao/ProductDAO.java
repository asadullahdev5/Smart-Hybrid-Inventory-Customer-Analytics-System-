package com.smartstore.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.smartstore.model.Product;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final MongoCollection< Document > collection;

    public ProductDAO () {
        MongoDatabase db = MongoConnection.getDatabase ( );
        // Agar collection nahi bani hui toh MongoDB khud hi bana dega
        this.collection = db.getCollection ( "products" );
    }

    // 1. Product Add Karne Ka Method
    public void addProduct ( Product product ) {
        Document doc = new Document ( "name" , product.getName ( ) )
                .append ( "price" , product.getPrice ( ) )
                .append ( "stock" , product.getStock ( ) )
                .append ( "attributes" , product.getAttributes ( ) );
        collection.insertOne ( doc );
    }

    // 2. Saare Products Get Karne Ka Method (JTable me dikhane k liye)
    public List< Product > getAllProducts () {

        List< Product > list = new ArrayList<> ( );
        FindIterable< Document > docs = collection.find ( );

        for (Document doc : docs) {

            // ✨ SAFE PRICE HANDLING (Integer / Double dono support)
            Number priceNum = doc.get ( "price" , Number.class );
            double price = (priceNum != null) ? priceNum.doubleValue ( ) : 0.0;

            Product p = new Product (
                    doc.getString ( "name" ) ,
                    price ,
                    doc.getInteger ( "stock" ) ,
                    (Document) doc.get ( "attributes" )
            );

            p.setId ( doc.getObjectId ( "_id" ).toString ( ) );
            list.add ( p );
        }

        return list;
    }
}