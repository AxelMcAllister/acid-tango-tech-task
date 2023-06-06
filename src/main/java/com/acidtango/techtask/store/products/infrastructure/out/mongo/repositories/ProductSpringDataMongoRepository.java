package com.acidtango.techtask.store.products.infrastructure.out.mongo.repositories;

import com.acidtango.techtask.store.products.infrastructure.out.mongo.models.entities.ProductMongoEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductSpringDataMongoRepository extends MongoRepository<ProductMongoEntity, ObjectId> {
}
