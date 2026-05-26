package presentation;

import business.MovieManager;
import model.Movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final MovieManager<Movie> movieManager = new MovieManager<>();

    public static void main(String[] args) {
        int choice;

        do {
            showMenu();
            choice = inputInteger("");

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    deleteMovie();
                    break;
                case 3:
                    updateMovie();
                    break;
                case 4:
                    displayMovies();
                    break;
                case 5:
                    searchMovie();
                    break;
                case 6:
                    filterMoviesByRating();
                    break;
                case 7:
                    System.out.println("Thoát chương trình!");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        } while (choice != 7);
    }

    private static void showMenu() {
        System.out.println("Chọn chức năng:");
        System.out.println("1. Thêm phim");
        System.out.println("2. Xóa phim");
        System.out.println("3. Sửa phim");
        System.out.println("4. Hiển thị phim");
        System.out.println("5. Tìm kiếm phim theo tên");
        System.out.println("6. Lọc phim theo rating");
        System.out.println("7. Thoát");
    }

    private static void addMovie() {
        try {
            Movie movie = inputMovie();
            movieManager.addMovie(movie);
            System.out.println("Phim đã được thêm thành công.");
        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println("Dữ liệu phim không hợp lệ!");
        }
    }

    private static void deleteMovie() {
        int id = inputInteger("Nhập ID phim cần xóa: ");

        if (movieManager.removeMovie(id)) {
            System.out.println("Phim đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy phim muốn xóa !");
        }
    }

    private static void updateMovie() {
        int id = inputInteger("Mời nhập id phim muốn sửa : ");

        if (movieManager.findById(id) == null) {
            System.out.println("Không tìm thấy phim với id = " + id);
            return;
        }

        try {
            Movie movie = inputMovie();
            movieManager.updateMovie(id, movie);
            System.out.println("Cập nhật phim thành công !");
        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println("Dữ liệu phim không hợp lệ!");
        }
    }

    private static void displayMovies() {
        System.out.println("Danh sách phim:");

        if (movieManager.getMovies().isEmpty()) {
            System.out.println("Danh sách phim trống!");
            return;
        }

        for (Movie movie : movieManager.getMovies()) {
            System.out.println(movie);
        }
    }

    private static void searchMovie() {
        System.out.print("Nhập tiêu đề phim để tìm kiếm: ");
        String title = scanner.nextLine();
        List<Movie> result = movieManager.searchByTitle(title);

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy phim");
            return;
        }

        for (Movie movie : result) {
            System.out.println("Phim tìm thấy: " + movie);
        }
    }

    private static void filterMoviesByRating() {
        double rating = inputDouble("Nhập rating tối thiểu để lọc: ");
        List<Movie> result = movieManager.filterByRating(rating);

        System.out.println("Phim có rating lớn hơn " + rating + ":");
        if (result.isEmpty()) {
            System.out.println("Không có phim phù hợp!");
            return;
        }

        for (Movie movie : result) {
            System.out.println(movie);
        }
    }

    private static Movie inputMovie() {
        int id = inputInteger("Nhập ID phim: ");
        System.out.print("Nhập tiêu đề phim: ");
        String title = scanner.nextLine();
        System.out.print("Nhập đạo diễn: ");
        String director = scanner.nextLine();
        System.out.print("Nhập ngày phát hành (dd-MM-yyyy): ");
        LocalDate releaseDate = LocalDate.parse(scanner.nextLine(), FORMATTER);
        double rating = inputDouble("Nhập rating: ");

        return new Movie(id, title, director, releaseDate, rating);
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

    private static double inputDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
}
