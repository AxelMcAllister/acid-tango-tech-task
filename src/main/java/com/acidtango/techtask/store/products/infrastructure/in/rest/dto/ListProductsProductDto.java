package com.acidtango.techtask.store.products.infrastructure.in.rest.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ListProductsProductDto(
        String id,
        String name,
        List<ListProductsProductVariantDto> variants
) {
}
