class Exercise1 {
    static class Animal {
        protected String name;

        void makeSound() {
            System.out.println("Some animal sound");
        }
    }

    static class Dog extends Animal {
        Dog(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");
        dog.makeSound();
    }
}
