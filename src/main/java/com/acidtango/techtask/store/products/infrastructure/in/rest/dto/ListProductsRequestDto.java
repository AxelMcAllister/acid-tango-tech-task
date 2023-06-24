package com.acidtango.techtask.store.products.infrastructure.in.rest.dto;

import java.util.Optional;

public record ListProductsRequestDto(
        Optional<Integer> stockRelativeWeight,
        Optional<Integer> soldUnitsRelativeWeight
) {
}
