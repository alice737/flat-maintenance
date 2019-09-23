package com.flat.flatmaintenance.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRespository;

    public List<Product> findAll() {
        return productRespository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRespository.findById(id);
    }

    public Product save(Product stock) {
        return productRespository.save(stock);
    }

    public void deleteById(Long id) {
        productRespository.deleteById(id);
    }
}

