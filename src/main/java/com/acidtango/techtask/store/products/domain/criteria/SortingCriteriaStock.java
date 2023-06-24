package com.acidtango.techtask.store.products.domain.criteria;

import com.acidtango.techtask.store.products.domain.models.entities.Product;

public class SortingCriteriaStock implements SortingCriteria {
    @Override
    public RelativeWeight getWeight(Product product) {
        return new RelativeWeight(product.getStockOrThrow());
    }
}
