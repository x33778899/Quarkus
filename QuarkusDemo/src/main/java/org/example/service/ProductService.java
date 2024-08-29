package org.example.service;

import org.example.model.Product;

import java.util.List;

public interface ProductService {

    boolean createProduct(Product product);

    Product getProductById(Long id);

    List<Product> getProductByUserId(Long userId);

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
