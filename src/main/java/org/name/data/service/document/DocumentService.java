package org.name.data.service.document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.name.data.service.Service;
import org.name.exceptions.AppException;

import java.util.ArrayList;
import java.util.List;

public abstract class DocumentService<T> implements Service<T> {

  private MongoDatabase database;

  DocumentService(MongoDatabase database) {
    this.database = database;
  }

  @Override
  public T read(String id) throws AppException {
    MongoCollection mongoCollection = database.getCollection(getDomainObjectName());
    Document query = new Document("_id", new ObjectId(id));
    return (T) mongoCollection.find(query, getEntityType()).first();
  }

  @Override
  public void delete(String id) throws AppException {
    MongoCollection mongoCollection = database.getCollection(getDomainObjectName());
    Document query = new Document("_id", new ObjectId(id));
    mongoCollection.deleteOne(query);
  }

  @Override
  public void createOrUpdate(T object) throws AppException {
    MongoCollection mongoCollection =
        database.getCollection(getDomainObjectName(), getEntityType());
    mongoCollection.insertOne(object);
  }

  @Override
  public void createMany(List<T> objects) throws AppException {
    MongoCollection mongoCollection =
        database.getCollection(getDomainObjectName(), getEntityType());
    mongoCollection.insertMany(objects);
  }

  @Override
  public Iterable<T> readAll() throws AppException {
    MongoCollection mongoCollection = database.getCollection(getDomainObjectName());

    Document query = new Document();
    return mongoCollection.find(query, getEntityType()).into(new ArrayList<>());
  }

  abstract String getDomainObjectName();

  @Override
  public boolean ping() throws AppException {

    Document ping = new Document("ping", 1);
    try {
      Document answer = database.runCommand(ping);
      String foo = "";
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
