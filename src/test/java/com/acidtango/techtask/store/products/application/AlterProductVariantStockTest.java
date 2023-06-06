package com.acidtango.techtask.store.products.application;

import com.acidtango.techtask.store.products.application.usecases.AlterProductVariantStockUseCase;
import com.acidtango.techtask.store.products.common.TestSubjects;
import com.acidtango.techtask.store.products.domain.exceptions.InsufficientStockException;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.services.ListProductService;
import com.acidtango.techtask.store.products.infrastructure.out.console.repositories.ProductConsoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlterProductVariantStockTest {

    AlterProductVariantStockUseCase useCase;
    ProductConsoleRepository productRepository;
    ListProductService listProductService;
    Product testProduct;

    @BeforeEach
    void beforeEach() {
        productRepository = new ProductConsoleRepository();
        listProductService = new ListProductService(productRepository);
        useCase = new AlterProductVariantStockUseCase(listProductService, productRepository);

        productRepository.clear();
        productRepository.fill(TestSubjects.products());
        testProduct = TestSubjects.products().get(0);
    }

    @Test
    void alterProductVariantStock_add100() {

        Integer productVariantStockDelta = 100;

        Integer stockBefore = testProduct.getVariants().get(0).getStock();

        useCase.execute(testProduct.getId(), testProduct.getVariants().get(0).getId(), productVariantStockDelta);

        Integer stockAfter = listProductService.findByIdOrThrow(testProduct.getId()).getVariants().get(0).getStock();

        assertEquals(productVariantStockDelta, stockAfter - stockBefore);
    }

    @Test
    void alterProductVariantStock_remove2() {
        Integer productVariantStockDelta = -2;

        Integer stockBefore = testProduct.getVariants().get(0).getStock();

        useCase.execute(testProduct.getId(), testProduct.getVariants().get(0).getId(), productVariantStockDelta);

        Integer stockAfter = listProductService.findByIdOrThrow(testProduct.getId()).getVariants().get(0).getStock();

        assertEquals(productVariantStockDelta, stockAfter - stockBefore);
    }

    @Test
    void alterProductVariantStock_remove1000_throwsInsufficientStockException() {
        Integer productVariantStockDelta = -1000;

        Integer stockBefore = testProduct.getVariants().get(0).getStock();

        try {
            useCase.execute(testProduct.getId(), testProduct.getVariants().get(0).getId(), productVariantStockDelta);
        } catch (Exception e) {
            assertTrue(e instanceof InsufficientStockException);
        }

        Integer stockAfter = listProductService.findByIdOrThrow(testProduct.getId()).getVariants().get(0).getStock();

        assertEquals(stockBefore, stockAfter);
    }
}
