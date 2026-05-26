package presentation;

import business.EventManager;
import model.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final EventManager eventManager = new EventManager();

    public static void main(String[] args) {
        while (true) {
            System.out.print("Nhập tên sự kiện (hoặc 'exit' để thoát): ");
            String name = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(name)) {
                break;
            }

            if (name.isEmpty()) {
                System.out.println("Can not enter empty string");
                continue;
            }

            LocalDateTime startDate = inputDateTime("Nhập thời gian bắt đầu (dd-MM-yyyy HH:mm): ");
            LocalDateTime endDate = inputDateTime("Nhập thời gian kết thúc (dd-MM-yyyy HH:mm): ");

            if (endDate.isBefore(startDate)) {
                System.out.println("Thời gian kết thúc phải sau thời gian bắt đầu");
                continue;
            }

            Event event = new Event(name, startDate, endDate);
            eventManager.addEvent(event);
            System.out.println("Trạng thái sự kiện: " + eventManager.getEventStatus(event));
        }

        System.out.println("Danh sách sự kiện:");
        for (Event event : eventManager.getEvents()) {
            System.out.println(event);
        }
    }

    private static LocalDateTime inputDateTime(String message) {
        while (true) {
            try {
                System.out.print(message);
                return LocalDateTime.parse(scanner.nextLine().trim(), FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Enter not valid date");
            }
        }
    }
}
