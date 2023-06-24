package com.acidtango.techtask.store.products.infrastructure.in.rest.controllers;

import com.acidtango.techtask.store.products.infrastructure.in.rest.dto.CreateProductRequestDto;
import com.acidtango.techtask.store.products.infrastructure.in.rest.dto.CreateProductResponseDto;
import com.acidtango.techtask.store.products.application.usecases.CreateProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class CreateProductController {
    final CreateProductUseCase createProductUseCase;

    @Operation(description = "Creates a new product and its three variants")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateProductResponseDto create(@RequestBody CreateProductRequestDto request) {
        return new CreateProductResponseDto(createProductUseCase.execute(request.name()).toString());
    }

}
