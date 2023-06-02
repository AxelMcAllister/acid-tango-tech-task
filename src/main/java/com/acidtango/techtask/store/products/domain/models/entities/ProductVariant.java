package com.acidtango.techtask.store.products.domain.models.entities;

import com.acidtango.techtask.common.domain.Entity;
import com.acidtango.techtask.store.products.domain.exceptions.InsufficientStockException;
import com.acidtango.techtask.store.products.domain.models.valueobjects.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;


@Builder
@AllArgsConstructor
@Getter
@ToString
public class ProductVariant extends Entity {
    private final ObjectId id;
    private final ProductSize size;
    private Integer stock;
    private Integer soldUnits;

    public ProductVariant(ObjectId id, ProductSize size) {
        this.id = id;
        this.size = size;
        this.stock = 0;
        this.soldUnits = 0;
    }

    public static ProductVariant create(ProductSize productSize) {
        return new ProductVariant(new ObjectId(), productSize);
    }

    public void checkStock(int amount) {
        if (stock < amount) {
            throw new InsufficientStockException(id);
        }
    }

    public void alterStock(Integer amount) {
        checkStock(amount*(-1));
        stock += amount;
    }

    public void alterSoldUnits(Integer amount) {
        soldUnits += amount;
    }
}
