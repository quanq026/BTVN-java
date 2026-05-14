import java.util.Scanner;

class Exercise5 {
    static class Animal {
        protected String name;
        protected int age;

        Animal(String name, int age) {
            this.name = name;
            this.age = age;
        }

        void showInfo() {
            System.out.println("Name: " + name + ", Age: " + age);
        }

        void makeSound() {
            System.out.println("Animal makes a sound");
        }

        void eat() {
            System.out.println(name + " is eating.");
        }

        void eat(String food) {
            System.out.println(name + " is eating " + food + ".");
        }
    }

    static class Mammal extends Animal {
        protected boolean hasFur;

        Mammal(String name, int age, boolean hasFur) {
            super(name, age);
            this.hasFur = hasFur;
        }

        @Override
        void showInfo() {
            super.showInfo();
            System.out.println("Has fur: " + hasFur);
        }
    }

    static class Dog extends Mammal {
        Dog(String name, int age, boolean hasFur) {
            super(name, age, hasFur);
        }

        @Override
        void makeSound() {
            System.out.println(name + " says: Woof Woof!");
        }

        void fetchBall() {
            System.out.println(name + " is fetching the ball.");
        }
    }

    static class Cat extends Mammal {
        Cat(String name, int age, boolean hasFur) {
            super(name, age, hasFur);
        }

        @Override
        void makeSound() {
            System.out.println(name + " says: Meow Meow!");
        }

        void climbTree() {
            System.out.println(name + " is climbing a tree.");
        }
    }

    static class Elephant extends Mammal {
        Elephant(String name, int age, boolean hasFur) {
            super(name, age, hasFur);
        }

        @Override
        void makeSound() {
            System.out.println(name + " says: Pawooooo!");
        }

        void sprayWater() {
            System.out.println(name + " is spraying water!");
        }
    }

    static void showMenu() {
        System.out.println("===== ZOO MANAGEMENT MENU =====");
        System.out.println("1. Tao doi tuong va hien thi thong tin");
        System.out.println("2. Kiem tra Overriding: makeSound()");
        System.out.println("3. Kiem tra Overloading: eat()");
        System.out.println("4. Kiem tra da hinh runtime (Animal array)");
        System.out.println("5. Goi phuong thuc dac trung tung loai");
        System.out.println("0. Thoat");
        System.out.println("==============================");
        System.out.print("Chon chuc nang: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Dog dog = new Dog("Buddy", 3, true);
        Cat cat = new Cat("Mimi", 2, true);
        Elephant elephant = new Elephant("Dumbo", 10, false);
        Animal[] animals = {dog, cat, elephant};

        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.println();
                System.out.println("--- THONG TIN CAC DONG VAT ---");
                for (Animal animal : animals) {
                    animal.showInfo();
                }
            } else if (choice == 2) {
                System.out.println();
                System.out.println("--- OVERRIDING: makeSound() ---");
                dog.makeSound();
                cat.makeSound();
                elephant.makeSound();
            } else if (choice == 3) {
                System.out.println();
                System.out.println("--- OVERLOADING: eat() ---");
                dog.eat();
                dog.eat("meat");
                cat.eat("fish");
                elephant.eat();
            } else if (choice == 4) {
                System.out.println();
                System.out.println("--- POLYMORPHISM RUNTIME ---");
                for (Animal animal : animals) {
                    animal.makeSound();
                }
            } else if (choice == 5) {
                System.out.println();
                System.out.println("--- PHUONG THUC RIENG CUA TUNG LOAI ---");
                dog.fetchBall();
                cat.climbTree();
                elephant.sprayWater();
            } else if (choice == 0) {
                System.out.println("Thoat chuong trinh...");
            } else {
                System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }
}
