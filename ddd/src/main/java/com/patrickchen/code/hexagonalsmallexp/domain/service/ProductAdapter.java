package com.patrickchen.code.hexagonalsmallexp.domain.service;

import com.patrickchen.code.hexagonalsmallexp.domain.Product;
import com.patrickchen.code.hexagonalsmallexp.infrastructure.LocalCache;
import com.patrickchen.code.hexagonalsmallexp.infrastructure.ProductRepostiory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductAdapter implements ProductPort {

    private ProductRepostiory productRepostiory;

    private LocalCache cache;

    public void setProductRepostiory(ProductRepostiory productRepostiory) {
        this.productRepostiory = productRepostiory;
    }

    public void setCache(LocalCache cache) {
        this.cache = cache;
    }

    @Override
    public void save(Product product) {
        productRepostiory.save(product);
        cache.save(product);
    }

    @Override
    public Product findByProductId(UUID productId) {
        Product product = cache.findByProductId(productId);
        if (product == null) {
            return productRepostiory.findById(productId);
        }
        return product;
    }
}
