package com.acidtango.techtask.store.products.infrastructure.mongo.models.entities;

import com.acidtango.techtask.store.products.domain.models.entities.Product;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@Document("products")
public class ProductMongoEntity {

    @Id
    ObjectId id;
    String name;
    List<ProductVariantMongoEntity> variants;

    public static ProductMongoEntity fromDomain(Product product) {
        return ProductMongoEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .variants(product.getVariants().stream().map(ProductVariantMongoEntity::fromDomain).toList())
                .build();
    }

    public static Optional<Product> toDomain(ProductMongoEntity productMongoEntity) {
        Product product = new Product(productMongoEntity.getId(), productMongoEntity.getName(), productMongoEntity.getVariants().stream().map(ProductVariantMongoEntity::toDomain).toList());
        return Optional.of(product);
    }
}
