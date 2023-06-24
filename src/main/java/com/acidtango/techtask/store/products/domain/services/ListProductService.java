package com.acidtango.techtask.store.products.domain.services;

import com.acidtango.techtask.common.domain.DomainService;
import com.acidtango.techtask.store.products.domain.criteria.ListProductsSortingCriteria;
import com.acidtango.techtask.store.products.domain.exceptions.ProductNotFoundException;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListProductService extends DomainService {
    private final ProductRepository productRepository;

    public Product findByIdOrThrow(ObjectId productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public List<Product> findAllAndSortByCriteria(ListProductsSortingCriteria sortingCriteria) {
        return productRepository.findAllAndSortByCriteria(sortingCriteria);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
