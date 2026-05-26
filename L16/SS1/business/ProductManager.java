package business;

import model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductManager {
    private final Map<Integer, Product> products = new HashMap<>();

    public ProductManager() {
        products.put(2, new Product(2, "Khoai tây chiên", 20000));
        products.put(3, new Product(3, "Kẹo cốm", 50));
    }

    public boolean addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            return false;
        }

        products.put(product.getId(), product);
        return true;
    }

    public boolean updateProduct(int id, String name, double price) {
        Product product = products.get(id);

        if (product == null) {
            return false;
        }

        product.setName(name);
        product.setPrice(price);
        return true;
    }

    public boolean deleteProduct(int id) {
        return products.remove(id) != null;
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public List<Product> filterProductsGreaterThan100() {
        return products.values()
                .stream()
                .filter(product -> product.getPrice() > 100)
                .collect(Collectors.toList());
    }

    public double calculateTotalValue() {
        return products.values()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
