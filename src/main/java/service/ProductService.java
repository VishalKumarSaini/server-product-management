package service;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Long countAllProducts();

    Optional<Product> findProduct(Long productId);

    Product addProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);
}
