class Exercise4 {
    interface Connectable {
        void connectWifi();
    }

    interface Chargeable {
        void charge();
    }

    static abstract class Device {
        protected int id;
        protected String name;

        Device(int id, String name) {
            this.id = id;
            this.name = name;
        }

        abstract void turnOn();

        abstract void turnOff();

        void showInfo() {
            System.out.println("ID: " + id + ", Name: " + name);
        }
    }

    static class SmartPhone extends Device implements Connectable, Chargeable {
        SmartPhone(int id, String name) {
            super(id, name);
        }

        @Override
        void turnOn() {
            System.out.println(name + " dang bat nguon");
        }

        @Override
        void turnOff() {
            System.out.println(name + " dang tat nguon");
        }

        @Override
        public void connectWifi() {
            System.out.println(name + " ket noi wifi");
        }

        @Override
        public void charge() {
            System.out.println(name + " dang sac pin");
        }
    }

    static class Laptop extends Device implements Connectable, Chargeable {
        Laptop(int id, String name) {
            super(id, name);
        }

        @Override
        void turnOn() {
            System.out.println(name + " dang khoi dong");
        }

        @Override
        void turnOff() {
            System.out.println(name + " dang tat may");
        }

        @Override
        public void connectWifi() {
            System.out.println(name + " ket noi wifi");
        }

        @Override
        public void charge() {
            System.out.println(name + " dang sac pin");
        }
    }

    static class Television extends Device implements Connectable {
        Television(int id, String name) {
            super(id, name);
        }

        @Override
        void turnOn() {
            System.out.println(name + " dang bat");
        }

        @Override
        void turnOff() {
            System.out.println(name + " dang tat");
        }

        @Override
        public void connectWifi() {
            System.out.println(name + " ket noi wifi");
        }
    }

    public static void main(String[] args) {
        Device[] devices = {
                new SmartPhone(1, "iPhone 15"),
                new Laptop(2, "Dell XPS"),
                new Television(3, "Samsung TV")
        };

        for (Device device : devices) {
            device.showInfo();
            device.turnOn();
            if (device instanceof Connectable connectable) {
                connectable.connectWifi();
            }
            if (device instanceof Chargeable chargeable) {
                chargeable.charge();
            }
            device.turnOff();
            System.out.println();
        }
    }
}
