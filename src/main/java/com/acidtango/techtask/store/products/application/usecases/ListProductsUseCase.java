package com.acidtango.techtask.store.products.application.usecases;

import com.acidtango.techtask.common.application.UseCase;
import com.acidtango.techtask.store.products.domain.criteria.ListProductsSortingCriteria;
import com.acidtango.techtask.store.products.domain.models.entities.Product;
import com.acidtango.techtask.store.products.domain.services.ListProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProductsUseCase extends UseCase {
    final ListProductService listProductService;

    public ListProductsUseCase(ListProductService listProductService) {
        this.listProductService = listProductService;
    }

    public List<Product> execute(ListProductsSortingCriteria sortingCriteria) {
        return listProductService.findAllAndSortByCriteria(sortingCriteria);
    }

    public List<Product> execute() {
        return listProductService.findAll();
    }
}
