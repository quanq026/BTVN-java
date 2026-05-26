package presentation;

import business.ProductProcessor;
import business.ProductProcessorImpl;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1500));
        products.add(new Product("Mouse", 25));
        products.add(new Product("Keyboard", 120));
        products.add(new Product("Notebook", 20));

        ProductProcessor productProcessor = new ProductProcessorImpl();
        Predicate<Product> expensivePredicate = product -> product.getPrice() > 100;

        System.out.println("Danh sách sản phẩm:");
        ProductProcessor.printProductList(products);

        if (productProcessor.hasExpensiveProduct(products)) {
            System.out.println("Sản phẩm đắt tiền:");
            products.stream()
                    .filter(expensivePredicate)
                    .forEach(System.out::println);
        } else {
            System.out.println("Không có sản phẩm đắt tiền");
        }

        System.out.println("Tổng giá trị sản phẩm: " + productProcessor.calculateTotalValue(products));
    }
}
