package business;

import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    public boolean addBook(Book book) throws SQLException {
        if (isDuplicate(book.getTitle(), book.getAuthor())) {
            return false;
        }
        String sql = "INSERT INTO books(title, author, published_year, price) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            fillBookStatement(statement, book);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateBook(int id, Book book) throws SQLException {
        if (!existsById(id)) {
            return false;
        }
        String sql = "UPDATE books SET title = ?, author = ?, published_year = ?, price = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            fillBookStatement(statement, book);
            statement.setInt(5, id);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteBook(int id) throws SQLException {
        if (!existsById(id)) {
            return false;
        }
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public List<Book> findBooksByAuthor(String author) throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE author LIKE ?")) {
            statement.setString(1, "%" + author + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(readBook(resultSet));
                }
            }
        }
        return books;
    }

    public List<Book> listAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {
            while (resultSet.next()) {
                books.add(readBook(resultSet));
            }
        }
        return books;
    }

    private boolean isDuplicate(String title, String author) throws SQLException {
        String sql = "SELECT id FROM books WHERE title = ? AND author = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, author);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private boolean existsById(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM books WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private void fillBookStatement(PreparedStatement statement, Book book) throws SQLException {
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setInt(3, book.getPublishedYear());
        statement.setBigDecimal(4, book.getPrice());
    }

    private Book readBook(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getInt("published_year"),
                resultSet.getBigDecimal("price")
        );
    }
}
