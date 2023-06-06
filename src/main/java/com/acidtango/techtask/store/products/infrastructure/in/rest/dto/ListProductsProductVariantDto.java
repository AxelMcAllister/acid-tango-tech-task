package com.acidtango.techtask.store.products.infrastructure.in.rest.dto;

import com.acidtango.techtask.store.products.domain.models.valueobjects.ProductSize;
import lombok.Builder;

@Builder
public record ListProductsProductVariantDto(
        ProductSize size,
        Integer stock,
        Integer soldUnits
) {
}
