package com.acidtango.techtask.store.products.domain.criteria;

import com.acidtango.techtask.common.domain.ValueObject;
import jakarta.validation.constraints.NotNull;

public class RelativeWeight extends ValueObject implements Comparable<RelativeWeight> {

    public final Integer weight;

    public RelativeWeight(Integer weight) {
        this.weight = weight;
    }

    public static RelativeWeight zero(){
        return new RelativeWeight(0);
    }

    public RelativeWeight plus(RelativeWeight other) {
        return new RelativeWeight(weight + other.weight);
    }

    public RelativeWeight multiply(RelativeWeight other) {
        return new RelativeWeight(weight * other.weight);
    }

    @Override
    public int compareTo(@NotNull RelativeWeight other) {
        return weight - other.weight;
    }
}
