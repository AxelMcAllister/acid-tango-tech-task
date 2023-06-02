package com.acidtango.techtask.store.products.application.rest.controllers;

import com.acidtango.techtask.store.products.application.rest.dto.CreateProductRequestDto;
import com.acidtango.techtask.store.products.application.rest.dto.CreateProductResponseDto;
import com.acidtango.techtask.store.products.application.usecases.CreateProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class CreateProductController {

    final CreateProductUseCase createProductUseCase;

    public CreateProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @Operation(description = "Creates a new product and its three variants")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateProductResponseDto create(@RequestBody CreateProductRequestDto request) {
        return new CreateProductResponseDto(createProductUseCase.execute(request.name()).toString());
    }

}
