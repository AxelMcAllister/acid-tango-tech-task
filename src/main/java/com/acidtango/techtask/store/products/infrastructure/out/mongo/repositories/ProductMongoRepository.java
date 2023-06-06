package com.acidtango.techtask.store.products.infrastructure.out.mongo.repositories;

import com.acidtango.techtask.store.products.domain.criteria.ListProductsSortingCriteria;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.models.entities.ProductVariant;
import com.acidtango.techtask.store.products.domain.repositories.ProductRepository;
import com.acidtango.techtask.store.products.infrastructure.out.mongo.models.entities.ProductMongoEntity;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class ProductMongoRepository implements ProductRepository {

    private final ProductSpringDataMongoRepository productSpringDataMongoRepository;

    public ProductMongoRepository(ProductSpringDataMongoRepository productSpringDataMongoRepository) {
        this.productSpringDataMongoRepository = productSpringDataMongoRepository;
    }

    @Override
    public ObjectId save(Product product) {
        return productSpringDataMongoRepository.save(ProductMongoEntity.fromDomain(product)).getId();
    }

    @Override
    public Optional<Product> findById(ObjectId productId) {
        return productSpringDataMongoRepository.findById(productId).flatMap(ProductMongoEntity::toDomain);
    }

    @Override
    public List<Product> findAllAndSortByCriteria(ListProductsSortingCriteria sortingCriteria) {
        return productSpringDataMongoRepository.findAll().stream()
                .map(ProductMongoEntity::toDomain)
                .flatMap(Optional::stream)
                .sorted((p1, p2) -> sortingCriteria.getWeight(p2).compareTo(sortingCriteria.getWeight(p1)))
                .toList();
    }

    @Override
    public List<Product> findAll() {
        return productSpringDataMongoRepository.findAll().stream()
                .map(ProductMongoEntity::toDomain)
                .flatMap(Optional::stream)
                .toList();
    }

    @Override
    public Optional<ProductVariant> findByVariantId(ObjectId productId, ObjectId variantId) {
        return findById(productId).map(product -> product.getVariantByIdOrThrow(variantId));

    }
}
