package org.example.dao;

import io.quarkus.agroal.DataSource;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.model.User;

@ApplicationScoped
@DataSource("demo")
public class UserRepository implements PanacheRepository<User> {

    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

}


