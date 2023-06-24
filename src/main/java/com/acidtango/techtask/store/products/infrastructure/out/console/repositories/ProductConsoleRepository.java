package com.acidtango.techtask.store.products.infrastructure.out.console.repositories;

import com.acidtango.techtask.store.products.domain.criteria.ListProductsSortingCriteria;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.models.entities.ProductVariant;
import com.acidtango.techtask.store.products.domain.repositories.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
// Anotate with @Primary to use this repository instead of ProductMongoRepository
public class ProductConsoleRepository implements ProductRepository {
    private final List<Product> products = new ArrayList<>();

    @Override
    public ObjectId save(Product product) {
        products.add(product);
        print(product);
        return product.getId();
    }

    @Override
    public Optional<Product> findById(ObjectId productId) {
        Optional<Product> product = products.stream().filter(p -> p.getId().equals(productId)).findFirst();
        product.ifPresentOrElse(this::print, () -> print("Product " + productId + " not found"));
        return product;
    }

    @Override
    public List<Product> findAllAndSortByCriteria(ListProductsSortingCriteria sortingCriteria) {
        var sortedProducts = products.stream()
                .sorted((p1, p2) -> sortingCriteria.getWeight(p2).compareTo(sortingCriteria.getWeight(p1)))
                .toList();
        sortedProducts.forEach(this::print);
        return sortedProducts;
    }

    @Override
    public List<Product> findAll() {
        products.forEach(this::print);
        return products;
    }

    @Override
    public Optional<ProductVariant> findByVariantId(ObjectId productId, ObjectId variantId) {
        Optional<ProductVariant> variant = findById(productId).map(product -> product.getVariantByIdOrThrow(variantId));
        variant.ifPresentOrElse(this::print, () -> print("Variant " + variantId + " not found"));
        return variant;
    }

    public void fill(List<Product> products) {
        this.products.addAll(products);
    }

    public void clear() {
        this.products.clear();
    }

    private void print(Object o) {
        System.out.println(o.toString());
    }
}
