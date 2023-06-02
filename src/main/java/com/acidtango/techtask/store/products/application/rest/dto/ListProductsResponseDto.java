package com.acidtango.techtask.store.products.application.rest.dto;

import java.util.List;

public record ListProductsResponseDto(
        List<ListProductsProductDto> products
) {
}
