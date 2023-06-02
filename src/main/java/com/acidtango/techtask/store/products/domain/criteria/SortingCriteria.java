package com.acidtango.techtask.store.products.domain.criteria;

import com.acidtango.techtask.store.products.domain.models.entities.Product;

public interface SortingCriteria {
    RelativeWeight getWeight(Product product);
}
