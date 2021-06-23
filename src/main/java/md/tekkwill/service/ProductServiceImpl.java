package md.tekkwill.service;

import md.tekkwill.dao.ProductRepository;
import md.tekkwill.product.Drink;
import md.tekkwill.product.Food;
import md.tekkwill.product.FoodCategory;
import md.tekkwill.product.Product;
import md.tekkwill.exceptions.ProductExistsException;
import md.tekkwill.exceptions.ProductNotFoundException;
import md.tekkwill.exceptions.ProductUpdateUnknownPropertyException;
import md.tekkwill.service.ProductService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void create(String name, double price, LocalDate bestBefore, double volume) {
        productRepository.save(new Drink(name, price, bestBefore, volume));
    }

    @Override
    public void create(String name, double price, LocalDate bestBefore, FoodCategory category) {
        if (productRepository.findByName(name) != null) {
            throw new ProductExistsException("Product with name " + name + " already exists!");
        }
        productRepository.save(new Food(name, price, bestBefore, category));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllNonExpired() {
        List<Product> products = productRepository.findAll();
        Predicate<Product> nonExpiredPredicate = p -> LocalDate.now().isBefore(p.getBestBefore()) || LocalDate.now().isEqual(p.getBestBefore());
        return filter(products, nonExpiredPredicate);
    }

    @Override
    public List<Product> getAllExpired() {
        List<Product> products = productRepository.findAll();
        Predicate<Product> expiredPredicate = p -> LocalDate.now().isAfter(p.getBestBefore());
        return filter(products, expiredPredicate);
    }

    private List<Product> filter(List<Product> products, Predicate<Product> predicate) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (predicate.test(product)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    @Override
    public Product getById(int id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found!");
        }
        return product;
    }

    @Override
    public void update(int id, double volume) throws ProductUpdateUnknownPropertyException {
        Product product = getById(id);
        if (product instanceof Drink) {
            productRepository.update(id, volume);
            return;
        }
        throw new ProductUpdateUnknownPropertyException("Product with id " + id + " is not drink!");
    }

    @Override
    public void update(int id, FoodCategory category) throws ProductUpdateUnknownPropertyException {
        Product product = getById(id);
        if (product instanceof Food) {
            productRepository.update(id, category);
            return;
        }
        throw new ProductUpdateUnknownPropertyException("Product with id " + id + " is not food!");
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }
}
