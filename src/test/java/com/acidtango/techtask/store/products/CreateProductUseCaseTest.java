package com.acidtango.techtask.store.products;

import com.acidtango.techtask.store.products.common.TestSubjects;
import com.acidtango.techtask.store.products.infrastructure.in.rest.dto.ListProductsResponseDto;
import com.acidtango.techtask.store.products.infrastructure.out.mongo.repositories.ProductSpringDataMongoRepository;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
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
class CreateProductUseCaseTest {

    public static final String PRODUCTS_PATH = "/products";
    public static final String PRODUCT_NAME = TestSubjects.products().get(0).getName();

    @LocalServerPort
    private int port;

    @Autowired
    ProductSpringDataMongoRepository productSpringDataMongoRepository;

    @AfterEach
    void resetTestCollection() {
        productSpringDataMongoRepository.deleteAll();
    }

    @Test
    void createAProduct() {

        given()
                .port(port)
                .queryParam("name", PRODUCT_NAME)
                .contentType(ContentType.JSON)
                .post(PRODUCTS_PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value());

        var response =
                given()
                        .port(port)
                        .get(PRODUCTS_PATH);
        var products =
                response.getBody()
                        .as(ListProductsResponseDto.class)
                        .products();

        assertEquals(response.statusCode(), HttpStatus.OK.value());
        assertThat(products).hasSize(1);
        assertThat(products.get(0).name()).isEqualTo(PRODUCT_NAME);
    }
}