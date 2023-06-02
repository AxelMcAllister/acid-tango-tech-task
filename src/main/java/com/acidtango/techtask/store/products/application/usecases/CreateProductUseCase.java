package com.acidtango.techtask.store.products.application.usecases;

import com.acidtango.techtask.common.application.UseCase;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.services.CreateProductService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase extends UseCase {
    final CreateProductService createProductService;

    public CreateProductUseCase(CreateProductService createProductService) {
        this.createProductService = createProductService;
    }

    public ObjectId execute(String name) {
        Product product = Product.create(name);
        createProductService.save(product);
        return product.getId();
    }
}
