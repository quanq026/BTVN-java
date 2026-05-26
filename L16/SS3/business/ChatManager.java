package business;

import model.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChatManager {
    private final List<Message> messages = new ArrayList<>();

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getHistory() {
        return messages;
    }

    public List<Message> filterBySender(String sender) {
        return messages.stream()
                .filter(message -> message.getSender().equalsIgnoreCase(sender))
                .collect(Collectors.toList());
    }

    public List<Message> filterByDate(LocalDate date) {
        return messages.stream()
                .filter(message -> message.getTimestamp().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }
}
