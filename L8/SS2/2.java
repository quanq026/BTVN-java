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

        @Override
        public String toString() {
            return "Book{title='" + title + "', author='" + author + "', price=" + price + "}";
        }
    }

    public static void main(String[] args) {
        Book book = new Book("Clean Code", "Robert C. Martin", 45.5);
        System.out.println(book);
    }
}
