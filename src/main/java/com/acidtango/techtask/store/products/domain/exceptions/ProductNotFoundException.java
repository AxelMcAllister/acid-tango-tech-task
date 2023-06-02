package com.acidtango.techtask.store.products.domain.exceptions;


import com.acidtango.techtask.common.domain.DomainException;
import org.bson.types.ObjectId;

public class ProductNotFoundException extends DomainException {
    private final ObjectId productId;

    public ProductNotFoundException(ObjectId productId) {
        this.productId = productId;
    }
}
