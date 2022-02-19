package service.serviceImpl;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ProductRepository;
import service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public Long countAllProducts(){
        return productRepository.count();
    }

    @Override
    public Optional<Product> findProduct(Long productId){
        return  productRepository.findById(productId);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct( Product product) {
        Product existProduct=productRepository.getById(product.getId());
        existProduct.setProductName(product.getProductName());
        existProduct.setExplanation(product.getExplanation());
        existProduct.setPrice(product.getPrice());
        return productRepository.save(existProduct);
    }

    @Override
    public void deleteProduct( Product product) {
         productRepository.deleteById(product.getId());
    }

}
