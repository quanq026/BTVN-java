import java.util.LinkedList;
import java.util.Scanner;

class Exercise1 {
    static class Person {
        private String name;
        private String email;
        private String phone;

        Person(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Ten: " + name + ", Email: " + email + ", So dien thoai: " + phone;
        }
    }

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final LinkedList<Person> PEOPLE = new LinkedList<>();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addPerson();
                case 2 -> deletePerson();
                case 3 -> showPeople();
                case 4 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 4);
    }

    private static void showMenu() {
        System.out.println("*************** MENU QUAN LY NGUOI DUNG ***************");
        System.out.println("1. Them nguoi dung");
        System.out.println("2. Xoa nguoi dung");
        System.out.println("3. Hien thi danh sach nguoi dung");
        System.out.println("4. Thoat");
        System.out.print("Lua chon cua ban: ");
    }

    private static void addPerson() {
        String name = inputNotEmpty("Nhap ten nguoi dung: ");
        String email = inputNotEmpty("Nhap email nguoi dung: ");
        String phone = inputNotEmpty("Nhap so dien thoai nguoi dung: ");
        PEOPLE.add(new Person(name, email, phone));
        System.out.println("Nguoi dung da duoc them thanh cong.");
    }

    private static void deletePerson() {
        String email = inputNotEmpty("Nhap email nguoi dung de xoa: ");
        for (Person person : PEOPLE) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                PEOPLE.remove(person);
                System.out.println("Nguoi dung da duoc xoa thanh cong.");
                return;
            }
        }
        System.out.println("Khong tim thay nguoi dung.");
    }

    private static void showPeople() {
        System.out.println("Danh sach nguoi dung:");
        if (PEOPLE.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        for (Person person : PEOPLE) {
            System.out.println(person);
        }
    }

    private static String inputNotEmpty(String message) {
        while (true) {
            System.out.print(message);
            String value = SCANNER.nextLine();
            if (!value.isBlank()) {
                return value;
            }
            System.out.println("Vui long ko de trong !");
        }
    }
}
