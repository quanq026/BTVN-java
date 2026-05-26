package business;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private final List<Product> products = new ArrayList<>();

    public ProductCatalog() {
        products.add(new Product("P01", "Laptop", 15000000));
        products.add(new Product("P02", "Chuột", 150000));
        products.add(new Product("P03", "Bàn phím", 350000));
        products.add(new Product("P04", "Tai nghe", 500000));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findById(String productId) {
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(productId)) {
                return product;
            }
        }

        return null;
    }
}
