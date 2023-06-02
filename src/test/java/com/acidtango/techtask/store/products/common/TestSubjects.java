package com.acidtango.techtask.store.products.common;

import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.models.entities.ProductVariant;
import com.acidtango.techtask.store.products.domain.models.valueobjects.ProductSize;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

public class TestSubjects {
    public static ProductVariant productVariant(ProductSize size) {
        return ProductVariant.builder()
                .id(new ObjectId())
                .size(size)
                .stock(5)
                .soldUnits(10)
                .build();
    }

    public static Product product(String name) {
        return Product.create(name, Arrays.asList(
                productVariant(ProductSize.SMALL),
                productVariant(ProductSize.MEDIUM),
                productVariant(ProductSize.LARGE)));
    }

    public static List<Product> products() {
        return
                Arrays.asList(
                        Product.builder()
                                .id(new ObjectId("100000000000000000000000"))
                                .name("V-NECH BASIC SHIRT")
                                .variants(Arrays.asList(
                                        ProductVariant.builder()
                                                .id(new ObjectId("110000000000000000000000"))
                                                .size(ProductSize.SMALL)
                                                .stock(4)
                                                .soldUnits(10)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("120000000000000000000000"))
                                                .size(ProductSize.MEDIUM)
                                                .stock(9)
                                                .soldUnits(60)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("130000000000000000000000"))
                                                .size(ProductSize.LARGE)
                                                .stock(0)
                                                .soldUnits(30)
                                                .build()
                                )).build(),

                        Product.builder()
                                .id(new ObjectId("200000000000000000000000"))
                                .name("CONTRASTING FABRIC T-SHIRT")
                                .variants(Arrays.asList(
                                        ProductVariant.builder()
                                                .id(new ObjectId("210000000000000000000000"))
                                                .size(ProductSize.SMALL)
                                                .stock(35)
                                                .soldUnits(15)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("220000000000000000000000"))
                                                .size(ProductSize.MEDIUM)
                                                .stock(9)
                                                .soldUnits(20)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("230000000000000000000000"))
                                                .size(ProductSize.LARGE)
                                                .stock(9)
                                                .soldUnits(15)
                                                .build()
                                )).build(),

                        Product.builder()
                                .id(new ObjectId("300000000000000000000000"))
                                .name("RAISED PRINT T-SHIRT")
                                .variants(Arrays.asList(
                                        ProductVariant.builder()
                                                .id(new ObjectId("310000000000000000000000"))
                                                .size(ProductSize.SMALL)
                                                .stock(20)
                                                .soldUnits(20)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("320000000000000000000000"))
                                                .size(ProductSize.MEDIUM)
                                                .stock(2)
                                                .soldUnits(40)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("330000000000000000000000"))
                                                .size(ProductSize.LARGE)
                                                .stock(20)
                                                .soldUnits(20)
                                                .build()
                                )).build(),

                        Product.builder()
                                .id(new ObjectId("400000000000000000000000"))
                                .name("PLEATED T-SHIRT")
                                .variants(Arrays.asList(
                                        ProductVariant.builder()
                                                .id(new ObjectId("410000000000000000000000"))
                                                .size(ProductSize.SMALL)
                                                .stock(25)
                                                .soldUnits(0)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("420000000000000000000000"))
                                                .size(ProductSize.MEDIUM)
                                                .stock(30)
                                                .soldUnits(2)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("430000000000000000000000"))
                                                .size(ProductSize.LARGE)
                                                .stock(10)
                                                .soldUnits(1)
                                                .build()
                                )).build(),

                        Product.builder()
                                .id(new ObjectId("500000000000000000000000"))
                                .name("CONTRASTING LACE T-SHIRT")
                                .variants(Arrays.asList(
                                        ProductVariant.builder()
                                                .id(new ObjectId("510000000000000000000000"))
                                                .size(ProductSize.SMALL)
                                                .stock(0)
                                                .soldUnits(50)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("520000000000000000000000"))
                                                .size(ProductSize.MEDIUM)
                                                .stock(1)
                                                .soldUnits(400)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("530000000000000000000000"))
                                                .size(ProductSize.LARGE)
                                                .stock(0)
                                                .soldUnits(200)
                                                .build()
                                )).build(),

                        Product.builder()
                                .id(new ObjectId("600000000000000000000000"))
                                .name("SLOGAN T-SHIRT")
                                .variants(Arrays.asList(
                                        ProductVariant.builder()
                                                .id(new ObjectId("610000000000000000000000"))
                                                .size(ProductSize.SMALL)
                                                .stock(9)
                                                .soldUnits(5)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("620000000000000000000000"))
                                                .size(ProductSize.MEDIUM)
                                                .stock(2)
                                                .soldUnits(10)
                                                .build(),
                                        ProductVariant.builder()
                                                .id(new ObjectId("630000000000000000000000"))
                                                .size(ProductSize.LARGE)
                                                .stock(5)
                                                .soldUnits(5)
                                                .build()
                                )).build()

                );
    }
}


