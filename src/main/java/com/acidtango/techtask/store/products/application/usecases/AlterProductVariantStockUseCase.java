package com.acidtango.techtask.store.products.application.usecases;

import com.acidtango.techtask.common.application.UseCase;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.services.CreateProductService;
import com.acidtango.techtask.store.products.domain.services.ListProductService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class AlterProductVariantStockUseCase extends UseCase {

    private final ListProductService listProductService;
    private final CreateProductService createProductService;

    public AlterProductVariantStockUseCase(ListProductService listProductService, CreateProductService createProductService) {
        this.listProductService = listProductService;
        this.createProductService = createProductService;
    }

    public void execute(ObjectId productId, ObjectId variantId, Integer amount) {
        Product product = listProductService.findById(productId);
        product.getVariantById(variantId).alterStock(amount);
        createProductService.save(product);
    }
}
