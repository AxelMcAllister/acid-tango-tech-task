package com.acidtango.techtask.store.products.domain.models.entities;

import com.acidtango.techtask.common.domain.AggregateRoot;
import com.acidtango.techtask.store.products.domain.models.valueobjects.ProductSize;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString
@Builder
public class Product extends AggregateRoot {
    final ObjectId id;
    final String name;
    final List<ProductVariant> variants;

    public Product(ObjectId id, String name, List<ProductVariant> variants) {
        this.id = id;
        this.name = name;
        this.variants = variants;
    }

    public static Product create(String name, List<ProductVariant> variants) {
        return new Product(new ObjectId(), name, variants);
    }

    public static Product create(String name) {
        List<ProductVariant> variants = new ArrayList<>();
        Arrays.stream(ProductSize.values()).forEach(productSize -> variants.add(ProductVariant.create(productSize)));
        return new Product(new ObjectId(), name, variants);
    }

    public ProductVariant getVariantById(ObjectId variantId){
        return variants.stream()
                .filter(productVariant -> productVariant.getId().equals(variantId))
                .findFirst().orElseThrow(() -> new RuntimeException("Invalid product variant"));
    }

    public Integer getStock() {
        return variants.stream()
                .map(ProductVariant::getStock)
                .reduce(Integer::sum)
                .orElseThrow(() -> new IllegalStateException("Invalid product stock"));
    }

    public Integer getSoldUnits() {
        return variants.stream()
                .map(ProductVariant::getSoldUnits)
                .reduce(Integer::sum)
                .orElseThrow(() -> new IllegalStateException("Invalid product sold units"));
    }
}
