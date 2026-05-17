import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Exercise2 {
    interface Manage<T> {
        void add(T item);

        void update(int index, T item);

        void delete(int index);

        void display();
    }

    static class Student {
        private String id;
        private String name;

        Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "ID : " + id + ", Ten sinh vien: " + name;
        }
    }

    static class AttendanceManager implements Manage<Student> {
        private final List<Student> students = new ArrayList<>();

        @Override
        public void add(Student item) {
            students.add(item);
        }

        @Override
        public void update(int index, Student item) {
            students.set(index, item);
        }

        @Override
        public void delete(int index) {
            students.remove(index);
        }

        @Override
        public void display() {
            if (students.isEmpty()) {
                System.out.println("Danh sach rong.");
                return;
            }
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i));
            }
        }

        int size() {
            return students.size();
        }
    }

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final AttendanceManager MANAGER = new AttendanceManager();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> MANAGER.display();
                case 5 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("*************** MENU QUAN LY DIEM DANH ***************");
        System.out.println("1. Them sinh vien");
        System.out.println("2. Sua sinh vien");
        System.out.println("3. Xoa sinh vien");
        System.out.println("4. Hien thi danh sach sinh vien");
        System.out.println("5. Thoat");
        System.out.print("Lua chon cua ban: ");
    }

    private static Student inputStudent() {
        System.out.print("Nhap id sinh vien : ");
        String id = SCANNER.nextLine();
        System.out.print("Nhap ten sinh vien: ");
        String name = SCANNER.nextLine();
        return new Student(id, name);
    }

    private static void addStudent() {
        MANAGER.add(inputStudent());
        System.out.println("Sinh vien da duoc them thanh cong.");
    }

    private static void updateStudent() {
        MANAGER.display();
        int index = inputIndex("Nhap id sinh vien can sua: ");
        if (index == -1) {
            return;
        }
        System.out.println("Nhap thong tin moi:");
        MANAGER.update(index, inputStudent());
        System.out.println("Sinh vien da duoc sua thanh cong.");
    }

    private static void deleteStudent() {
        MANAGER.display();
        int index = inputIndex("Nhap id sinh vien can xoa: ");
        if (index == -1) {
            return;
        }
        MANAGER.delete(index);
        System.out.println("Da xoa thanh cong sinh vien !");
    }

    private static int inputIndex(String message) {
        System.out.print(message);
        int index = Integer.parseInt(SCANNER.nextLine()) - 1;
        if (index < 0 || index >= MANAGER.size()) {
            System.out.println("Vi tri khong hop le.");
            return -1;
        }
        return index;
    }
}
