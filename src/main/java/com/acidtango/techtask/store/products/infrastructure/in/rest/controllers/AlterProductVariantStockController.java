package com.acidtango.techtask.store.products.infrastructure.in.rest.controllers;

import com.acidtango.techtask.store.products.application.usecases.AlterProductVariantStockUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class AlterProductVariantStockController {
    final AlterProductVariantStockUseCase alterProductVariantStockUseCase;

    @Operation(description = "Changes the stock of the given product variant")
    @PostMapping("/{productId}/variants/{variantId}/stock/{amount}")
    @ResponseStatus(HttpStatus.OK)
    void crate(@PathVariable String productId, @PathVariable String variantId, @PathVariable Integer amount) {
        alterProductVariantStockUseCase.execute(new ObjectId(productId), new ObjectId(variantId), amount);
    }

}
