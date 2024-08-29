package org.example.dao;

import io.quarkus.agroal.DataSource;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.model.Product;

import java.util.List;

@ApplicationScoped
@DataSource("demo")
public class ProductRepository implements PanacheRepository<Product> {
    public List<Product> findByUserId(Long userId) {
        return find("SELECT p FROM Product p WHERE p.user.id = ?1", userId).list();
    }
}
