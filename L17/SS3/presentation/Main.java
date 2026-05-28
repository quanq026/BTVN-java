package presentation;

import business.BookManager;
import model.Book;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookManager bookManager = new BookManager();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = inputInteger("Lựa chọn của bạn: ");
            try {
                switch (choice) {
                    case 1: addBook(); break;
                    case 2: updateBook(); break;
                    case 3: deleteBook(); break;
                    case 4: searchByAuthor(); break;
                    case 5: displayBooks(bookManager.listAllBooks()); break;
                    case 0: System.out.println("Thoát chương trình!"); break;
                    default: System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (SQLException e) {
                System.out.println("Lỗi thao tác cơ sở dữ liệu: " + e.getMessage());
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("====== MENU QUẢN LÝ SÁCH ======");
        System.out.println("1. Thêm sách");
        System.out.println("2. Cập nhật sách");
        System.out.println("3. Xóa sách");
        System.out.println("4. Tìm kiếm theo tác giả");
        System.out.println("5. Hiển thị tất cả sách");
        System.out.println("0. Thoát");
    }

    private static void addBook() throws SQLException {
        boolean result = bookManager.addBook(inputBook(0));
        System.out.println(result ? "Thêm sách thành công!" : "Sách đã tồn tại!");
    }

    private static void updateBook() throws SQLException {
        int id = inputInteger("Nhập ID sách cần cập nhật: ");
        boolean result = bookManager.updateBook(id, inputBook(id));
        System.out.println(result ? "Cập nhật sách thành công!" : "Không tìm thấy sách!");
    }

    private static void deleteBook() throws SQLException {
        int id = inputInteger("Nhập ID sách cần xóa: ");
        boolean result = bookManager.deleteBook(id);
        System.out.println(result ? "Xóa sách thành công!" : "Không tìm thấy sách!");
    }

    private static void searchByAuthor() throws SQLException {
        String author = inputRequiredString("Nhập tác giả cần tìm: ");
        displayBooks(bookManager.findBooksByAuthor(author));
    }

    private static Book inputBook(int id) {
        String title = inputRequiredString("Nhập tên sách: ");
        String author = inputRequiredString("Nhập tác giả: ");
        int publishedYear = inputInteger("Nhập năm xuất bản: ");
        BigDecimal price = inputBigDecimal("Nhập giá sách: ");
        return new Book(id, title, author, publishedYear, price);
    }

    private static void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Không có sách phù hợp!");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static String inputRequiredString(String message) {
        System.out.print(message);
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Không được nhập trống!");
        }
        return value;
    }

    private static int inputInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }

    private static BigDecimal inputBigDecimal(String message) {
        while (true) {
            try {
                System.out.print(message);
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
}
