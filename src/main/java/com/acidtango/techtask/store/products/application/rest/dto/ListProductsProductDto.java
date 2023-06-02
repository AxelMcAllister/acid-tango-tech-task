package com.acidtango.techtask.store.products.application.rest.dto;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.util.List;

@Builder
public record ListProductsProductDto(
        String id,
        String name,
        List<ListProductsProductVariantDto> variants
) {
}
