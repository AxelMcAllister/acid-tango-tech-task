package com.acidtango.techtask.store.products.infrastructure.in.rest.dto;

import java.util.List;

public record ListProductsResponseDto(
        List<ListProductsProductDto> products
) {
}
