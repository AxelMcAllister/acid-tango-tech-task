package com.acidtango.techtask.store.products;

import com.acidtango.techtask.store.products.domain.exceptions.InsufficientStockException;
import com.acidtango.techtask.store.products.infrastructure.mongo.models.entities.ProductMongoEntity;
import com.acidtango.techtask.store.products.infrastructure.mongo.repositories.ProductSpringDataMongoRepository;
import io.restassured.http.ContentType;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static com.acidtango.techtask.store.products.common.TestSubjects.products;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class AlterProductVariantStockUseCaseTest {

    public static final String PRODUCT_ID = products().get(0).getId().toString();
    public static final String PRODUCT_VARIANT_ID = products().get(0).getVariants().get(0).getId().toString();
    public static final String PRODUCT_STOCK_PATH = "/products/" + PRODUCT_ID + "/variants/" + PRODUCT_VARIANT_ID + "/stock/";

    @LocalServerPort
    private int port;

    @Autowired
    ProductSpringDataMongoRepository productSpringDataMongoRepository;

    @BeforeEach
    public void insertMockProducts() {
        productSpringDataMongoRepository.deleteAll();
        productSpringDataMongoRepository.saveAll(products().stream().map(ProductMongoEntity::fromDomain).toList());
    }

    @AfterEach
    void resetTestCollection() {
        productSpringDataMongoRepository.deleteAll();
    }

    @Test
    void alterProductVariantStock_add100() {

        final Integer productVariantStockDelta = 100;

        Integer stockBefore = productSpringDataMongoRepository.findById(new ObjectId(PRODUCT_ID)).get().getVariants().get(0).getStock();

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .post(PRODUCT_STOCK_PATH + productVariantStockDelta);

        Integer stockAfter = productSpringDataMongoRepository.findById(new ObjectId(PRODUCT_ID)).get().getVariants().get(0).getStock();

        assertEquals(productVariantStockDelta, stockAfter - stockBefore);
    }

    @Test
    void alterProductVariantStock_remove2() {

        final Integer productVariantStockDelta = -2;

        Integer stockBefore = productSpringDataMongoRepository.findById(new ObjectId(PRODUCT_ID)).get().getVariants().get(0).getStock();

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .post(PRODUCT_STOCK_PATH + productVariantStockDelta);

        Integer stockAfter = productSpringDataMongoRepository.findById(new ObjectId(PRODUCT_ID)).get().getVariants().get(0).getStock();

        assertEquals(productVariantStockDelta, stockAfter - stockBefore);
    }

    @Test
    void alterProductVariantStock_remove1000_throwsInsufficientStockException() {

        final int productVariantStockDelta = -1000;

        Integer stockBefore = productSpringDataMongoRepository.findById(new ObjectId(PRODUCT_ID)).get().getVariants().get(0).getStock();

        try {
            given()
                    .port(port)
                    .contentType(ContentType.JSON)
                    .post(PRODUCT_STOCK_PATH + productVariantStockDelta);

        } catch (Exception e) {
            assertTrue(e instanceof InsufficientStockException);
        }

        Integer stockAfter = productSpringDataMongoRepository.findById(new ObjectId(PRODUCT_ID)).get().getVariants().get(0).getStock();

        assertEquals(stockAfter, stockBefore);

        productSpringDataMongoRepository.deleteAll();

    }
}