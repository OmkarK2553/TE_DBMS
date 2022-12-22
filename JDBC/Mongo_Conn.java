import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

//	SCHEMA - {rollno int,name string,class string}

public class Mongo_Conn {
	static Scanner sc = new Scanner(System.in);
	
	public static void InsertOne(MongoCollection<Document> collection) {
		int sroll;
		String sname,sclass;
		Document document = new Document();
		
		System.out.println("Enter rollno: ");
		sroll = sc.nextInt();
		System.out.println("Enter name: ");
		sname = sc.next();
		System.out.println("Enter class: ");
		sclass = sc.next();
		
		document.append("rollno", sroll).append("name",sname).append("class",sclass);
		
		collection.insertOne(document);
		System.out.println("Data inserted successfully!");
	}
	
	public static void InsertMany(MongoCollection<Document> collection) {
		int no;
		System.out.println("Enter the number of docs:");
		no = sc.nextInt();
		
		List<Document> documents = new ArrayList<>();
		
		for(int i=0;i<no;i++) {
			int sroll;
			String sname,sclass;
			
			System.out.println("Enter rollno: ");
			sroll = sc.nextInt();
			System.out.println("Enter name: ");
			sname = sc.next();
			System.out.println("Enter class: ");
			sclass = sc.next();
			Document doc = new Document();
			doc.append("rollno", sroll).append("name", sname).append("class", sclass);
			documents.add(doc);
			
		}
		collection.insertMany(documents);
		System.out.println("Data inserted successfully!");
	}
	
	public static void Display(MongoCollection<Document> collection) {
		FindIterable<Document> documents = collection.find();
		
		for(Document doc : documents) {
			System.out.println(doc);
		}
	}
	
	public static void Update(MongoCollection<Document> collection) {
		int uroll;
		System.out.println("Enter roll no to update data:");
		uroll = sc.nextInt();
		
		System.out.println("What do you want to update: ");
		System.out.println("1) Rollno");
		System.out.println("2) Name");
		System.out.println("3) Class");
		int ch;
		ch = sc.nextInt();
		
		switch(ch) {
		case 1:
			int touroll;
			System.out.println("Enter new roll: ");
			touroll = sc.nextInt();
//			collection.updateMany(Filters.eq("rollno",uroll), Updates.set("rollno", touroll));
			collection.updateMany(Filters.eq("rollno",uroll), Updates.set("rollno", touroll));
			break;
			
		case 2:
			String touname;
			System.out.println("Enter new name: ");
			touname = sc.next();
//			collection.updateMany(Filters.eq("rollno",uroll), Updates.set("name", touname));
			collection.updateMany(Filters.eq("rollno",uroll), Updates.set("name", touname));
			break;
			
		case 3:
			String touclass;
			System.out.println("Enter new class: ");
			touclass = sc.next();
			collection.updateMany(Filters.eq("rollno",uroll), Updates.set("class",touclass));
			break;
		}
		System.out.println("Data updated successfully!");
	}
	
	public static void Delete(MongoCollection<Document> collection) {
		int droll;
		System.out.println("Enter roll no whose data is to be deleted");
		droll = sc.nextInt();
		
//		Bson deleteQuery = new Document("rollno",droll);
		collection.deleteMany(new Document("rollno",droll));
		
		System.out.println("Data deleted successfully!");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		MongoClient mongoClient = null;
		
		try {
			MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
//			MongoClient mongoClient = new MongoClient("localhost",27017);
			System.out.println("Connected to the database!");
			
			MongoDatabase database = mongoClient.getDatabase("Mongo_Conn");
			System.out.println("Database created");
            
            //todo creating a collection/ or getting an existing one:
			
			String coll;
			System.out.println("Enter collection: ");
			coll = sc.next();
			
            database.createCollection(coll);
            System.out.println("collection created!");
            
			MongoCollection<Document> collection = database.getCollection(coll);
			Document document = new Document();
			
			
			
			InsertOne(collection);
			Display(collection);
			InsertMany(collection);
			Display(collection);
			Update(collection);
			Display(collection);
			Delete(collection);
			Display(collection);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
