package com.ddg.SpringECom.service;

import com.ddg.SpringECom.model.Product;
import com.ddg.SpringECom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        Product product = repo.findById(id).orElse(null);
        if (product == null) {
            return new Product(-1);
        }
        return product;
    }
}
