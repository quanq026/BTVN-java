package business;

import model.CartItem;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addToCart(Product product, int quantity) throws CartException {
        if (product == null) {
            throw new CartException("Lỗi: Không tìm thấy sản phẩm!");
        }

        if (quantity <= 0) {
            throw new CartException("Lỗi: Số lượng không hợp lệ!");
        }

        CartItem existingItem = findItem(product.getId());
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            cartItems.add(new CartItem(product, quantity));
        }
    }

    public void removeFromCart(String productId) throws CartException {
        CartItem existingItem = findItem(productId);

        if (existingItem == null) {
            throw new CartException("Lỗi: Không tìm thấy sản phẩm trong giỏ hàng!");
        }

        cartItems.remove(existingItem);
    }

    public double checkout() {
        double total = 0;

        for (CartItem cartItem : cartItems) {
            total += cartItem.getTotalPrice();
        }

        return total;
    }

    private CartItem findItem(String productId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equalsIgnoreCase(productId)) {
                return cartItem;
            }
        }

        return null;
    }
}
