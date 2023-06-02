package com.acidtango.techtask.store.products;

import com.acidtango.techtask.store.products.common.TestSubjects;
import com.acidtango.techtask.store.products.application.rest.dto.ListProductsResponseDto;
import com.acidtango.techtask.store.products.infrastructure.mongo.models.entities.ProductMongoEntity;
import com.acidtango.techtask.store.products.infrastructure.mongo.repositories.ProductSpringDataMongoRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListProductsUseCaseTest {

    private static final String STOCK_RELATIVE_WEIGHT = "stockRelativeWeight";
    private static final String SOLD_UNITS_RELATIVE_WEIGHT = "soldUnitsRelativeWeight";
    private static final String LIST_PRODUCTS_PATH = "/products";

    @LocalServerPort
    int port;

    @Autowired
    ProductSpringDataMongoRepository productSpringDataMongoRepository;

    @BeforeAll
    public void insertMockProducts() {
        productSpringDataMongoRepository.deleteAll();
        productSpringDataMongoRepository.saveAll(TestSubjects.products().stream().map(ProductMongoEntity::fromDomain).toList());
    }

    @AfterAll
    void resetTestCollection() {
        productSpringDataMongoRepository.deleteAll();
    }

    @Test
    void listAllProduct() {

        var response =
                given()
                        .port(port)
                        .get(LIST_PRODUCTS_PATH);
        var products =
                response.getBody()
                        .as(ListProductsResponseDto.class)
                        .products();

        assertEquals(response.statusCode(), HttpStatus.OK.value());
        assertThat(products).hasSize(6);
        assertThat(products.get(0).name()).isEqualTo(TestSubjects.products().get(0).getName());
        assertThat(products.get(1).name()).isEqualTo(TestSubjects.products().get(1).getName());
        assertThat(products.get(2).name()).isEqualTo(TestSubjects.products().get(2).getName());
        assertThat(products.get(3).name()).isEqualTo(TestSubjects.products().get(3).getName());
        assertThat(products.get(4).name()).isEqualTo(TestSubjects.products().get(4).getName());
        assertThat(products.get(5).name()).isEqualTo(TestSubjects.products().get(5).getName());
    }

    @Test
    void listAllProductSortedByCriteria_stockRelativeWeight100_soldUnitsRelativeWeight0() {

        var response =
                given()
                        .port(port)
                        .queryParam(STOCK_RELATIVE_WEIGHT, 100)
                        .queryParam(SOLD_UNITS_RELATIVE_WEIGHT, 0)
                        .get(LIST_PRODUCTS_PATH);
        var products =
                response.getBody()
                        .as(ListProductsResponseDto.class)
                        .products();

        assertEquals(response.statusCode(), HttpStatus.OK.value());
        assertThat(products).hasSize(6);
        assertEquals(TestSubjects.products().get(3).getName(), products.get(0).name());
        assertEquals(TestSubjects.products().get(1).getName(), products.get(1).name());
        assertEquals(TestSubjects.products().get(2).getName(), products.get(2).name());
        assertEquals(TestSubjects.products().get(5).getName(), products.get(3).name());
        assertEquals(TestSubjects.products().get(0).getName(), products.get(4).name());
        assertEquals(TestSubjects.products().get(4).getName(), products.get(5).name());
    }

    @Test
    void listAllProductSortedByCriteria_stockRelativeWeightNull_soldUnitsRelativeWeight100() {

        var response =
                given()
                        .port(port)
                        .queryParam(STOCK_RELATIVE_WEIGHT, (Integer) null)
                        .queryParam(SOLD_UNITS_RELATIVE_WEIGHT, 100)
                        .get(LIST_PRODUCTS_PATH);
        var products =
                response.getBody()
                        .as(ListProductsResponseDto.class)
                        .products();

        assertEquals(response.statusCode(), HttpStatus.OK.value());
        assertThat(products).hasSize(6);
        assertEquals(TestSubjects.products().get(4).getName(), products.get(0).name());
        assertEquals(TestSubjects.products().get(0).getName(), products.get(1).name());
        assertEquals(TestSubjects.products().get(2).getName(), products.get(2).name());
        assertEquals(TestSubjects.products().get(1).getName(), products.get(3).name());
        assertEquals(TestSubjects.products().get(5).getName(), products.get(4).name());
        assertEquals(TestSubjects.products().get(3).getName(), products.get(5).name());
    }

    @Test
    void listAllProductSortedByCriteria_stockRelativeWeight50_soldUnitsRelativeWeight50() {

        var response =
                given()
                        .port(port)
                        .queryParam(STOCK_RELATIVE_WEIGHT, 50)
                        .queryParam(SOLD_UNITS_RELATIVE_WEIGHT, 50)
                        .get(LIST_PRODUCTS_PATH);
        var products =
                response.getBody()
                        .as(ListProductsResponseDto.class)
                        .products();

        assertEquals(response.statusCode(), HttpStatus.OK.value());
        assertThat(products).hasSize(6);
        assertEquals(TestSubjects.products().get(4).getName(), products.get(0).name());
        assertEquals(TestSubjects.products().get(2).getName(), products.get(1).name());
        assertEquals(TestSubjects.products().get(0).getName(), products.get(2).name());
        assertEquals(TestSubjects.products().get(1).getName(), products.get(3).name());
        assertEquals(TestSubjects.products().get(3).getName(), products.get(4).name());
        assertEquals(TestSubjects.products().get(5).getName(), products.get(5).name());
    }

    @Test
    void listAllProductSortedByCriteria_whenInvalidParams_getHttpStatusBAD_REQUEST() {

        var response =
                given()
                        .port(port)
                        .queryParam(STOCK_RELATIVE_WEIGHT, "INVALID PARAM")
                        .queryParam(SOLD_UNITS_RELATIVE_WEIGHT, "INVALID PARAM")
                        .get(LIST_PRODUCTS_PATH);

        assertEquals(HttpStatus.BAD_REQUEST.value(),response.statusCode());
    }
}