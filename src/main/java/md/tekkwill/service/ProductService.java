package md.tekkwill.service;

import md.tekkwill.product.FoodCategory;
import md.tekkwill.product.Product;
import md.tekkwill.exceptions.ProductUpdateUnknownPropertyException;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    void create(String name, double price, LocalDate bestBefore, double volume);

    void create(String name, double price, LocalDate bestBefore, FoodCategory category);

    List<Product> getAll();

    List<Product> getAllNonExpired();

    List<Product> getAllExpired();

    Product getById(int id);

    void update(int id, double volume) throws ProductUpdateUnknownPropertyException;

    void update(int id, FoodCategory category) throws ProductUpdateUnknownPropertyException;

    void delete(int id);
}
