package com.acidtango.techtask.store.products.application.usecases;

import com.acidtango.techtask.common.application.UseCase;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.repositories.ProductRepository;
import com.acidtango.techtask.store.products.domain.services.ListProductService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlterProductVariantStockUseCase extends UseCase {
    private final ListProductService listProductService;
    private final ProductRepository productRepository;

    public void execute(ObjectId productId, ObjectId variantId, Integer amount) {
        Product product = listProductService.findByIdOrThrow(productId);
        product.getVariantByIdOrThrow(variantId).alterStock(amount);
        productRepository.save(product);
    }
}
