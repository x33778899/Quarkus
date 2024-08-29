package org.example.service;


import io.quarkus.agroal.DataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.example.dao.ProductRepository;
import org.example.dao.UserRepository;
import org.example.model.Product;

import java.util.List;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    @DataSource("demo")
    ProductRepository productRepository;

    @Inject
    @DataSource("demo")
    UserRepository userRepository;

    @Transactional
    @Override
    public boolean createProduct(Product product) {

        // 检查用户是否已存在
        if (userRepository.findById(product.getUser().getId()) != null) {
            productRepository.persist(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductByUserId(Long userId) {
        return productRepository.findByUserId(userId);
    }


    @Override
    public List<Product> getAllProducts() {

        return productRepository.listAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id);
        if (existingProduct != null) {

            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());

            return productRepository.getEntityManager().merge(existingProduct);
        } else {
            throw new RuntimeException("Product with id " + id + " not found");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct != null) {
            productRepository.delete(existingProduct);
        } else {
            throw new RuntimeException("Product with id " + id + " not found");
        }
    }
}