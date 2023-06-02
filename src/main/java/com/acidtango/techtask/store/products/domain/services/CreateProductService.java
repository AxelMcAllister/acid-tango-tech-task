package com.acidtango.techtask.store.products.domain.services;

import com.acidtango.techtask.common.domain.DomainService;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.repositories.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService extends DomainService {

    private final ProductRepository productRepository;

    @Autowired
    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ObjectId save(Product product){
       return productRepository.save(product);
    }
}
