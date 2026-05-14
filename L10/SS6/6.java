import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Exercise6 {
    static class Vehicle {
        protected String brand;
        protected int year;

        Vehicle(String brand, int year) {
            this.brand = brand;
            this.year = year;
        }

        void showInfo() {
            System.out.println("Brand: " + brand + ", Year: " + year);
        }

        void startEngine() {
            System.out.println("Vehicle engine starts");
        }

        void move() {
            System.out.println(brand + " is moving.");
        }

        void move(int speed) {
            System.out.println(brand + " is moving at " + speed + " km/h.");
        }
    }

    static class MotorVehicle extends Vehicle {
        protected String fuelType;

        MotorVehicle(String brand, int year, String fuelType) {
            super(brand, year);
            this.fuelType = fuelType;
        }

        @Override
        void showInfo() {
            super.showInfo();
            System.out.println("Fuel Type: " + fuelType);
        }
    }

    static class Car extends MotorVehicle {
        Car(String brand, int year, String fuelType) {
            super(brand, year, fuelType);
        }

        @Override
        void startEngine() {
            System.out.println(brand + " (Car) engine starts: Vroom Vroom!");
        }

        void openTrunk() {
            System.out.println(brand + " trunk is opening...");
        }
    }

    static class Motorcycle extends MotorVehicle {
        Motorcycle(String brand, int year, String fuelType) {
            super(brand, year, fuelType);
        }

        @Override
        void startEngine() {
            System.out.println(brand + " (Motorcycle) engine starts: Brum Brum!");
        }

        void doWheelie() {
            System.out.println(brand + " is doing a wheelie!");
        }
    }

    static class Truck extends MotorVehicle {
        Truck(String brand, int year, String fuelType) {
            super(brand, year, fuelType);
        }

        @Override
        void startEngine() {
            System.out.println(brand + " (Truck) engine starts: RRRRRR!");
        }

        void loadCargo() {
            System.out.println(brand + " is loading cargo...");
        }
    }

    static void showMenu() {
        System.out.println("========= VEHICLE MANAGEMENT MENU =========");
        System.out.println("1. Hien thi thong tin tat ca phuong tien");
        System.out.println("2. Kiem tra Overriding: startEngine()");
        System.out.println("3. Kiem tra Overloading: move()");
        System.out.println("4. Kiem tra da hinh runtime (Vehicle array/list)");
        System.out.println("5. Goi hanh vi dac trung cua tung loai");
        System.out.println("6. Them phuong tien moi");
        System.out.println("0. Thoat");
        System.out.println("=========================================");
        System.out.print("Chon chuc nang: ");
    }

    static void addVehicle(Scanner scanner, List<Vehicle> vehicles) {
        System.out.println();
        System.out.println("--- THEM PHUONG TIEN MOI ---");
        System.out.print("Loai (car/motorcycle/truck): ");
        String type = scanner.nextLine();
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Fuel Type: ");
        String fuelType = scanner.nextLine();

        if (type.equalsIgnoreCase("car")) {
            vehicles.add(new Car(brand, year, fuelType));
        } else if (type.equalsIgnoreCase("motorcycle")) {
            vehicles.add(new Motorcycle(brand, year, fuelType));
        } else if (type.equalsIgnoreCase("truck")) {
            vehicles.add(new Truck(brand, year, fuelType));
        } else {
            System.out.println("Loai phuong tien khong hop le.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Car car = new Car("Toyota", 2020, "Gasoline");
        Motorcycle motorcycle = new Motorcycle("Honda", 2018, "Gasoline");
        Truck truck = new Truck("Volvo", 2022, "Diesel");
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        vehicles.add(motorcycle);
        vehicles.add(truck);

        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.println();
                System.out.println("--- THONG TIN PHUONG TIEN ---");
                for (Vehicle vehicle : vehicles) {
                    vehicle.showInfo();
                    System.out.println("--------------------");
                }
            } else if (choice == 2) {
                System.out.println();
                System.out.println("--- OVERRIDING: startEngine() ---");
                car.startEngine();
                motorcycle.startEngine();
                truck.startEngine();
            } else if (choice == 3) {
                System.out.println();
                System.out.println("--- OVERLOADING: move() ---");
                car.move();
                car.move(80);
            } else if (choice == 4) {
                System.out.println();
                System.out.println("--- POLYMORPHISM RUNTIME ---");
                for (Vehicle vehicle : vehicles) {
                    vehicle.startEngine();
                }
            } else if (choice == 5) {
                System.out.println();
                System.out.println("--- HANH VI DAC TRUNG CUA TUNG LOAI ---");
                car.openTrunk();
                motorcycle.doWheelie();
                truck.loadCargo();
            } else if (choice == 6) {
                addVehicle(scanner, vehicles);
            } else if (choice == 0) {
                System.out.println("Thoat chuong trinh...");
            } else {
                System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }
}
