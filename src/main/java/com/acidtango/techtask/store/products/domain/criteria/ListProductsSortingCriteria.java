package com.acidtango.techtask.store.products.domain.criteria;

import com.acidtango.techtask.store.products.domain.models.entities.Product;

public class ListProductsSortingCriteria implements SortingCriteria {
    private final SortingCriteriaStock sortingCriteriaStock = new SortingCriteriaStock();
    private final SortingCriteriaSoldUnits sortingCriteriaSoldUnits = new SortingCriteriaSoldUnits();
    private final RelativeWeight stockRelativeWeight;
    private final RelativeWeight soldUnitsRelativeWeight;

    public ListProductsSortingCriteria(RelativeWeight stockRelativeWeight, RelativeWeight soldUnitsRelativeWeight) {
        this.stockRelativeWeight = stockRelativeWeight;
        this.soldUnitsRelativeWeight = soldUnitsRelativeWeight;
    }

    @Override
    public RelativeWeight getWeight(Product product) {
        return new RelativeWeight(0)
                .plus(sortingCriteriaStock.getWeight(product)).multiply(stockRelativeWeight)
                .plus(sortingCriteriaSoldUnits.getWeight(product).multiply(soldUnitsRelativeWeight));
    }
}
