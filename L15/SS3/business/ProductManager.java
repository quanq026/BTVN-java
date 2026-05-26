package business;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) throws StoreException {
        if (product.getPrice() <= 0) {
            throw new StoreException("Lỗi: Giá sản phẩm phải lớn hơn 0!");
        }

        products.add(product);
    }

    public void removeProduct(int id) throws StoreException {
        Product product = findById(id);

        if (product == null) {
            throw new StoreException("Lỗi: Không tìm thấy sản phẩm cần xóa!");
        }

        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }
}
