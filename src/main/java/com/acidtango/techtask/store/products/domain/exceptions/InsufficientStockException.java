package com.acidtango.techtask.store.products.domain.exceptions;

import com.acidtango.techtask.common.domain.DomainException;
import org.bson.types.ObjectId;

public class InsufficientStockException extends DomainException {
    private final ObjectId productVariantId;
    public InsufficientStockException(ObjectId productVariantId) {
        this.productVariantId = productVariantId;
    }
}
