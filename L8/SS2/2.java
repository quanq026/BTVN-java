class Exercise2 {
    static class Book {
        private final String title;
        private final String author;
        private final double price;

        Book(String title, String author, double price) {
            this.title = title;
            this.author = author;
            this.price = price;
        }

        void printInfo() {
            System.out.printf("Title: %s, Author: %s, Price: %.2f%n", title, author, price);
        }
    }

    public static void main(String[] args) {
        Book book = new Book("Clean Code", "Robert C. Martin", 45.50);
        book.printInfo();
    }
}
