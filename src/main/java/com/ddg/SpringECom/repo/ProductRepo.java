package com.ddg.SpringECom.repo;

import com.ddg.SpringECom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% OR p.description LIKE %?1% OR p.brand LIKE %?1% OR p.category LIKE %?1%")
    List<Product> searchProducts(String keyword);
}
