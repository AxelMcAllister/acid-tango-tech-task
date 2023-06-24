package com.acidtango.techtask.store.products.infrastructure.out.mongo.models.entities;

import com.acidtango.techtask.store.products.domain.models.valueobjects.ProductSize;
import com.acidtango.techtask.store.products.domain.models.entities.ProductVariant;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

@Builder
@Getter
public class ProductVariantMongoEntity {

    ObjectId id;
    ProductSize size;
    Integer stock;
    Integer soldUnits;

    public static ProductVariantMongoEntity fromDomain(ProductVariant variant) {
        return ProductVariantMongoEntity.builder()
                .id(variant.getId())
                .size(variant.getSize())
                .stock(variant.getStock())
                .soldUnits(variant.getSoldUnits())
                .build();
    }

    public static ProductVariant toDomain(ProductVariantMongoEntity variant) {
        return ProductVariant.builder()
                .id(variant.getId())
                .size(variant.getSize())
                .stock(variant.getStock())
                .soldUnits(variant.getSoldUnits())
                .build();
    }
}
