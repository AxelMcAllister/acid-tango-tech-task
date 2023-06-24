package com.acidtango.techtask.store.products.application.usecases;

import com.acidtango.techtask.common.application.UseCase;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase extends UseCase {
    final ProductRepository productRepository;

    public ObjectId execute(String name) {
        Product product = Product.create(name);
        return productRepository.save(product);
    }
}
