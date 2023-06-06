package com.acidtango.techtask.store.products.infrastructure.in.rest.controllers;

import com.acidtango.techtask.store.products.infrastructure.in.rest.dto.ListProductsProductDto;
import com.acidtango.techtask.store.products.infrastructure.in.rest.dto.ListProductsProductVariantDto;
import com.acidtango.techtask.store.products.infrastructure.in.rest.dto.ListProductsRequestDto;
import com.acidtango.techtask.store.products.infrastructure.in.rest.dto.ListProductsResponseDto;
import com.acidtango.techtask.store.products.application.usecases.ListProductsUseCase;
import com.acidtango.techtask.store.products.domain.criteria.ListProductsSortingCriteria;
import com.acidtango.techtask.store.products.domain.criteria.RelativeWeight;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ListProductsController {
    private final ListProductsUseCase listProductsUseCase;

    @GetMapping
    ListProductsResponseDto listSorted(
            @RequestParam(required = false) Integer stockRelativeWeight,
            @RequestParam(required = false) Integer soldUnitsRelativeWeight
    ) {
        var request = new ListProductsRequestDto(Optional.ofNullable(stockRelativeWeight), Optional.ofNullable(soldUnitsRelativeWeight));
        if (request.stockRelativeWeight().map(srw -> srw == 0).orElse(true)
                &&
                request.soldUnitsRelativeWeight().map(surw -> surw == 0).orElse(true)
        ) {
            // Bypass sorting criteria when both weights are zero or null.
            return toResponseDto(listProductsUseCase.execute());
        }
        return toResponseDto(
                listProductsUseCase.execute(
                        new ListProductsSortingCriteria(
                                request.stockRelativeWeight().map(RelativeWeight::new).orElse(RelativeWeight.zero()),
                                request.soldUnitsRelativeWeight().map(RelativeWeight::new).orElse(RelativeWeight.zero())
                        )
                )
        );
    }

    private static ListProductsResponseDto toResponseDto(List<Product> productsList) {
        return new ListProductsResponseDto(
                productsList.stream().map(product -> ListProductsProductDto.builder()
                        .id(product.getId().toString())
                        .name(product.getName())
                        .variants(product.getVariants().stream().map(variant -> ListProductsProductVariantDto.builder()
                                .size(variant.getSize())
                                .stock(variant.getStock())
                                .soldUnits(variant.getSoldUnits())
                                .build()).toList()
                        )
                        .build()
                ).toList());
    }
}
