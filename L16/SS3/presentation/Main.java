package presentation;

import business.ChatManager;
import model.Message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final ChatManager chatManager = new ChatManager();

    public static void main(String[] args) {
        while (true) {
            System.out.print("Nhập tên người gửi (hoặc 'exit' để thoát): ");
            String sender = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(sender)) {
                break;
            }

            System.out.print("Nhập nội dung tin nhắn: ");
            String content = scanner.nextLine();
            chatManager.sendMessage(new Message(sender, content, LocalDateTime.now()));

            handleCommand();
        }
    }

    private static void handleCommand() {
        System.out.print("Nhập 'history' để xem lịch sử, hoặc 'filter' để lọc tin nhắn theo người gửi, hoặc 'date' để lọc theo ngày: ");
        String command = scanner.nextLine().trim();

        switch (command.toLowerCase()) {
            case "history":
                System.out.println("Lịch sử chat:");
                displayMessages(chatManager.getHistory());
                break;
            case "filter":
                filterBySender();
                break;
            case "date":
                filterByDate();
                break;
            default:
                break;
        }
    }

    private static void filterBySender() {
        System.out.print("Nhập tên người gửi để lọc: ");
        String sender = scanner.nextLine();
        List<Message> result = chatManager.filterBySender(sender);
        System.out.println("Tin nhắn từ " + sender + ":");
        displayMessages(result);
    }

    private static void filterByDate() {
        try {
            System.out.print("Nhập ngày (dd-MM-yyyy): ");
            LocalDate date = LocalDate.parse(scanner.nextLine().trim(), DATE_FORMATTER);
            List<Message> result = chatManager.filterByDate(date);
            System.out.println("Tin nhắn trong ngày " + date + ":");
            displayMessages(result);
        } catch (DateTimeParseException e) {
            System.out.println("Ngày không hợp lệ!");
        }
    }

    private static void displayMessages(List<Message> messages) {
        if (messages.isEmpty()) {
            System.out.println("Không có tin nhắn phù hợp!");
            return;
        }

        for (Message message : messages) {
            System.out.println(message);
        }
    }
}
