package md.tekkwill;

import md.tekkwill.app.ProductManagement;
import md.tekkwill.app.ProductManagementImpl;
import md.tekkwill.dao.InMemoryProductRepository;
import md.tekkwill.dao.JDBCProductRepository;
import md.tekkwill.dao.ProductRepository;
import md.tekkwill.service.ProductService;
import md.tekkwill.service.ProductServiceImpl;

import java.util.Scanner;

public class ProductManagementApp {
    public static void main(String[] args) {
        ProductRepository productRepository = new InMemoryProductRepository();
        JDBCProductRepository productRepository1= new JDBCProductRepository();
        ProductService productService = new ProductServiceImpl(productRepository1);
        ProductManagement productManagement = new ProductManagementImpl(productService,
                new ShoppingCart(),
                new Scanner(System.in));
        productManagement.run();
    }
}
