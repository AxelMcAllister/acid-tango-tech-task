package com.acidtango.techtask.store.products.application;

import com.acidtango.techtask.store.products.application.usecases.CreateProductUseCase;
import com.acidtango.techtask.store.products.common.TestSubjects;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.repositories.ProductRepository;
import com.acidtango.techtask.store.products.domain.services.CreateProductService;
import com.acidtango.techtask.store.products.domain.services.ListProductService;
import com.acidtango.techtask.store.products.infrastructure.console.repositories.ProductConsoleRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateProductTest {

    @Test
    void createAProduct() {
        ProductRepository productRepository = new ProductConsoleRepository();

        CreateProductService createProductService = new CreateProductService(productRepository);
        ListProductService listProductService = new ListProductService(productRepository);

        CreateProductUseCase createProductUseCase = new CreateProductUseCase(createProductService);

        Product testProduct = TestSubjects.products().get(0);

        ObjectId productId = createProductUseCase.execute(testProduct.getName());

        assertEquals(testProduct.getName(), listProductService.findById(productId).getName());
        assertEquals(1, listProductService.findAll().size());
    }
}
