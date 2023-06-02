package com.acidtango.techtask.store.products.application;

import com.acidtango.techtask.store.products.application.usecases.AlterProductVariantStockUseCase;
import com.acidtango.techtask.store.products.common.TestSubjects;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.exceptions.InsufficientStockException;
import com.acidtango.techtask.store.products.domain.services.CreateProductService;
import com.acidtango.techtask.store.products.domain.services.ListProductService;
import com.acidtango.techtask.store.products.infrastructure.console.repositories.ProductConsoleRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlterProductVariantStockTest {

    @Test
    void alterProductVariantStock_add100() {
        ProductConsoleRepository productRepository = new ProductConsoleRepository();
        CreateProductService createProductService = new CreateProductService(productRepository);
        ListProductService listProductService = new ListProductService(productRepository);
        AlterProductVariantStockUseCase useCase = new AlterProductVariantStockUseCase(listProductService, createProductService);

        productRepository.clear();
        productRepository.fill(TestSubjects.products());
        Product testProduct = TestSubjects.products().get(0);
        Integer productVariantStockDelta = 100;

        Integer stockBefore = testProduct.getVariants().get(0).getStock();

        useCase.execute(testProduct.getId(), testProduct.getVariants().get(0).getId(), productVariantStockDelta);

        Integer stockAfter = listProductService.findById(testProduct.getId()).getVariants().get(0).getStock();

        assertEquals(productVariantStockDelta, stockAfter - stockBefore);
    }

    @Test
    void alterProductVariantStock_remove2() {
        ProductConsoleRepository productRepository = new ProductConsoleRepository();
        CreateProductService createProductService = new CreateProductService(productRepository);
        ListProductService listProductService = new ListProductService(productRepository);
        AlterProductVariantStockUseCase useCase = new AlterProductVariantStockUseCase(listProductService, createProductService);

        productRepository.clear();
        productRepository.fill(TestSubjects.products());
        Product testProduct = TestSubjects.products().get(0);
        Integer productVariantStockDelta = -2;

        Integer stockBefore = testProduct.getVariants().get(0).getStock();

        useCase.execute(testProduct.getId(), testProduct.getVariants().get(0).getId(), productVariantStockDelta);

        Integer stockAfter = listProductService.findById(testProduct.getId()).getVariants().get(0).getStock();

        assertEquals(productVariantStockDelta, stockAfter - stockBefore);
    }

    @Test
    void alterProductVariantStock_remove1000_throwsInsufficientStockException() {
        ProductConsoleRepository productRepository = new ProductConsoleRepository();
        CreateProductService createProductService = new CreateProductService(productRepository);
        ListProductService listProductService = new ListProductService(productRepository);
        AlterProductVariantStockUseCase useCase = new AlterProductVariantStockUseCase(listProductService, createProductService);

        productRepository.clear();
        productRepository.fill(TestSubjects.products());
        Product testProduct = TestSubjects.products().get(0);
        Integer productVariantStockDelta = -1000;

        Integer stockBefore = testProduct.getVariants().get(0).getStock();

        try {
            useCase.execute(testProduct.getId(), testProduct.getVariants().get(0).getId(), productVariantStockDelta);
        } catch (Exception e) {
            assertTrue(e instanceof InsufficientStockException);
        }

        Integer stockAfter = listProductService.findById(testProduct.getId()).getVariants().get(0).getStock();

        assertEquals(stockBefore, stockAfter);
    }
}
