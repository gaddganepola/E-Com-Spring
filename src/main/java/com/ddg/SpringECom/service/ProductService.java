package com.ddg.SpringECom.service;

import com.ddg.SpringECom.model.Product;
import com.ddg.SpringECom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

    public void deleteProduct(int id) {
        Product product = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
        repo.deleteById(id);
    }

    public void updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        Product existingProduct = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setReleaseDate(product.getReleaseDate());
        existingProduct.setProductAvailable(product.isProductAvailable());
        existingProduct.setStockQuantity(product.getStockQuantity());
        existingProduct.setImageName(imageFile.getOriginalFilename());
        existingProduct.setImageType(imageFile.getContentType());
        existingProduct.setImageData(imageFile.getBytes());

        repo.save(existingProduct);
    }
}
