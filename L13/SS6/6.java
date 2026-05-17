import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

class Exercise6 {
    static class Contact {
        private static int nextId = 1;

        private int id;
        private String name;
        private String phoneNumber;

        Contact(String name, String phoneNumber) {
            this.id = nextId++;
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        String getPhoneNumber() {
            return phoneNumber;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof Contact contact)) {
                return false;
            }
            return Objects.equals(phoneNumber, contact.phoneNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(phoneNumber);
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Ten: " + name + ", So dien thoai: " + phoneNumber;
        }
    }

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Set<Contact> CONTACTS = new LinkedHashSet<>();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addContact();
                case 2 -> deleteContact();
                case 3 -> searchContact();
                case 4 -> showContacts();
                case 0 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("================ MENU ================");
        System.out.println("1. Them lien lac");
        System.out.println("2. Xoa lien lac theo so dien thoai");
        System.out.println("3. Tim kiem lien lac");
        System.out.println("4. Hien thi danh ba");
        System.out.println("0. Thoat");
        System.out.println("======================================");
        System.out.print("Lua chon cua ban: ");
    }

    private static void addContact() {
        System.out.print("Nhap ten lien lac: ");
        String name = SCANNER.nextLine();
        System.out.print("Nhap so dien thoai: ");
        String phoneNumber = SCANNER.nextLine();
        Contact contact = new Contact(name, phoneNumber);
        if (CONTACTS.add(contact)) {
            System.out.println("Them lien lac thanh cong.");
        } else {
            System.out.println("So dien thoai da ton tai");
        }
    }

    private static void deleteContact() {
        System.out.print("Nhap so dien thoai can xoa: ");
        String phoneNumber = SCANNER.nextLine();
        Contact contact = findContact(phoneNumber);
        if (contact == null) {
            System.out.println("Khong tim thay lien lac.");
            return;
        }
        CONTACTS.remove(contact);
        System.out.println("Xoa thanh cong.");
    }

    private static void searchContact() {
        System.out.print("Nhap so dien thoai can tim: ");
        String phoneNumber = SCANNER.nextLine();
        Contact contact = findContact(phoneNumber);
        if (contact == null) {
            System.out.println("Khong ton tai lien lac.");
        } else {
            System.out.println("Co ton tai lien lac:");
            System.out.println(contact);
        }
    }

    private static void showContacts() {
        if (CONTACTS.isEmpty()) {
            System.out.println("Danh ba rong.");
            return;
        }
        for (Contact contact : CONTACTS) {
            System.out.println(contact);
        }
    }

    private static Contact findContact(String phoneNumber) {
        for (Contact contact : CONTACTS) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        return null;
    }
}
