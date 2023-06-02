package com.acidtango.techtask.store.products.domain.repositories;

import com.acidtango.techtask.store.products.domain.criteria.ListProductsSortingCriteria;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.models.entities.ProductVariant;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    ObjectId save(Product product);

    Optional<Product> findById(ObjectId productId);

    List<Product> findAllAndSortByCriteria(ListProductsSortingCriteria sortingCriteria);
    List<Product> findAll();

    Optional<ProductVariant> findByVariantId(ObjectId productId, ObjectId variantId);
}
